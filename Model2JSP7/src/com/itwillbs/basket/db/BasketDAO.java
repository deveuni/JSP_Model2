package com.itwillbs.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.admin.goods.db.GoodsDTO;

public class BasketDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// DB연결 (커넥션 풀) - 디비 연결 정보를 미리 생성해서 pool에다가 저장하고, 필요시 사용/반납 형태로 처리
	
		private Connection getCon() throws Exception{
				
				// 커넥션 풀 
				// 1. 라이브러리 설치 (WEB-INF/lib/추가)
				// 2. /META-INF/context.xml 파일 생성 (1,2 단계 내용)
				// 3. /WEB-INF/web.xml 파일 수정
				// 4. DAO 처리 
				
				//Context(인터페이스) 객체를 생성
				Context init = new InitialContext(); // 업캐스팅
				// DB 연동 정보를 불러오기 (context.xml) : java:comp/env/~
				DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/Model2DB");
				//DataSource 객체 사용해서 연결 (멤버변수에 저장)
				con = ds.getConnection(); // con(멤버변수)에다 담으면 리턴안해도 담을 수 있음.
				
				return con;
		}// DB연결 (커넥션 풀)
		
		
		// DB 자원해제
		public void closeDB(){
			
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}// DB 자원해제
		
		
		//checkGoods(bdto)
		public int checkGoods(BasketDTO bkdto){
			
			int check =0;
			
			try {
				getCon();
				
				// sql - ID, gno, size, color 모두 만족하는 대상 검색
				
				sql = "select * from itwill_basket "
						+ "where b_m_id=? and b_g_num=? and "
						+ "b_g_size=? and b_g_color=? ";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, bkdto.getB_m_id());
				pstmt.setInt(2, bkdto.getB_g_num());
				pstmt.setString(3, bkdto.getB_g_size());
				pstmt.setString(4, bkdto.getB_g_color());
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					// 테이블안에 기존의 옵션과 동일한 상품이 있다.
					check = 1;
					
					// 데이터 수정 (update) - 구매 수량 수정(기존 수량 + 장바구니에서 담은 수량)
					sql = "update itwill_basket set b_g_amount=b_g_amount+? where b_m_id=? and b_g_num=? and "
							+ "b_g_size=? and b_g_color=?";
	
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, bkdto.getB_g_amount());
					pstmt.setString(2, bkdto.getB_m_id());
					pstmt.setInt(3, bkdto.getB_g_num());
					pstmt.setString(4, bkdto.getB_g_size());
					pstmt.setString(5, bkdto.getB_g_color());
					
					pstmt.executeUpdate();
					
					System.out.println("기존의 상품에 수량 변경 완료!");
				}
				
				System.out.println("기존의 상품 확인 결과 : "+(check==1?"상품있다":"상품없다"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
			return check;
		}//checkGoods(bdto)
		
		
		//basketAdd(bkdto)
		public void basketAdd(BasketDTO bkdto){
			
			int b_num = 0;
			
			try {
				con = getCon();
				
				// 1) 장바구니 번호 계산
				sql = "select max(b_num) from itwill_basket";
				
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					b_num = rs.getInt(1) + 1;
				}
				
				System.out.println("b_num : " + b_num);
				
				// 2) 나머지 전달 정보 DB에 저장
				
				sql = "insert into itwill_basket(b_num, b_m_id, b_g_num, b_g_amount, b_g_size, b_g_color, b_date) "
						+ "values(?,?,?,?,?,?,now())";
				
				pstmt = con.prepareStatement(sql);
				
				// 컬럼명 명시하지 않을 때 반드시 테이블의 컬럼 순서대로 전부 추가 
				pstmt.setInt(1, b_num);
				pstmt.setString(2, bkdto.getB_m_id());
				pstmt.setInt(3, bkdto.getB_g_num());
				pstmt.setInt(4, bkdto.getB_g_amount());
				pstmt.setString(5, bkdto.getB_g_size());
				pstmt.setString(6, bkdto.getB_g_color());
				
				pstmt.executeUpdate();
				
				System.out.println("DAO : 장바구니 추가 완료!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
		}//basketAdd(bkdto)
		
		// List + List 함께 가져오려면 List로 묶어서/ List계열 Vector와 같아서 묶는 거는 Vector로.
		//getBasketList(id)
		public Vector getBasketList(String id){
			
			Vector totalData = new Vector();
			
			// 상품 정보
			List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
			// 장바구니 정보 
			List<BasketDTO> basketList = new ArrayList<BasketDTO>();
			
			
			try {
				getCon();

				// 1) 장바구니 정보를 검색 (ID값을 기준으로 해당 장바구니 전부를 가져오기)
				sql = "select *  from itwill_basket where b_m_id=? ";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					// ID에 해당하는 장바구니를 전부 저장
					// 장바구니 상품정보를 (BasketDTO) -> BasketList 한칸에 저장 
					BasketDTO bkdto = new BasketDTO();
					
					bkdto.setB_g_amount(rs.getInt("b_g_amount"));
					bkdto.setB_g_color(rs.getString("b_g_color"));
					bkdto.setB_g_num(rs.getInt("b_g_num"));
					bkdto.setB_g_size(rs.getString("b_g_size"));
					bkdto.setB_m_id(rs.getString("b_m_id"));
					bkdto.setB_num(rs.getInt("b_num"));
					bkdto.setDate(rs.getDate("b_date"));
					
					// 리스트에 저장
					basketList.add(bkdto);
					
					
					// 같은 아이디가 장바구니에 담는 상품들이 다를 수 있으므로 while문 안에 if문 사용하기 
					// 장바구니 저장된 상품 정보를 불러오기 
					sql = "select * from itwill_goods where gno=?";
					
					PreparedStatement pstmt2 = con.prepareStatement(sql);
					pstmt2.setInt(1, bkdto.getB_g_num());
					
					ResultSet rs2 = pstmt2.executeQuery();
					
					// while 문의 pstmt,rs 를 같이 쓰므로 객체가 덮어씌어지므로 반복이 안되고 끝남!
					// 덮어씌어질 때 select * from itwill_goods where gno=? 이 쿼리만 인식하므로 이 쿼리 조회하면 실행이 끝나게 됨!! 
					// 그래서 나머지 상품들 조회하지 못했음. 
					// 다른 객체 생성해주기 
					
					//while문이 끝나면 그 전에 했던 동작들이 지워지게 되므로 while문 안에 if문 넣기 
					
					if(rs2.next()){
						
						GoodsDTO gdto = new GoodsDTO();
						
						gdto.setImage(rs2.getString("image"));
						gdto.setName(rs2.getString("name"));
						gdto.setPrice(rs2.getInt("price"));
						// 나머지 정보는 필요에 따라 추가 가능
						
						// 상품 리스트에 저장
						goodsList.add(gdto);
					} //if문
					
					
				}//while 구문
				System.out.println("DAO 상품 정보 : " + goodsList);
				System.out.println("DAO 장바구니 정보 : " + basketList);
				
				// 장바구니 정보, 상품 정보를 모두 저장 완료 
				totalData.add(basketList); //totalData(0인덱스)
				totalData.add(goodsList); //totalData(1인덱스)
				
				System.out.println("DAO 백터 정보 확인 : "+totalData);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
			return totalData;
		}//getBasketList(id)
		
		//basketDelete()
		public void basketDelete(int b_num){
			
			try {
				con = getCon();
				
				sql = "delete from itwill_basket where b_num=?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, b_num);
				
				pstmt.executeUpdate();
				
				System.out.println("DAO 장바구니 삭제 완료");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
		}
		//basketDelete()
		
		
		//basketDelete() 
				public void basketDelete(String id){
					
					try {
						con = getCon();
						// 구매 후 장바구니 삭제(본인 정보 삭제)
						sql = "delete from itwill_basket where b_m_id=?";
						
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, id);
						
						pstmt.executeUpdate();
						
						System.out.println("DAO 구매 후 본인 장바구니 비우기(삭제)");
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						closeDB();
					}
				}
				//basketDelete()
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
