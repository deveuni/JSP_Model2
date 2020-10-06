package com.itwillbs.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminGoodsDAO {
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// DB 자원해제
		
		
		//insertGoods(gdto)
		public void insertGoods(GoodsDTO gdto){
			
			int num = 0;
			
			try {
				con = getCon();
				
				// 1) 상품 등록 번호 계산 - 게시판 글 번호 처리
				sql = "select max(gno) from itwill_goods";
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					num = rs.getInt(1) + 1;
				}
				
				System.out.println("DAO : 상품 글 번호  "+num);
				
				// 2) 상품 등록(goods 테이블)
				
				sql = "insert into itwill_goods(gno, category, name, content, size, color, "
						+ "amount, price, image, best, date) "
						+ "values(?,?,?,?,?,?,?,?,?,?,now())";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, num);
				pstmt.setString(2, gdto.getCategory());
				pstmt.setString(3, gdto.getName());
				pstmt.setString(4, gdto.getContent());
				pstmt.setString(5, gdto.getSize());
				pstmt.setString(6, gdto.getColor());
				pstmt.setInt(7, gdto.getAmount());
				pstmt.setInt(8, gdto.getPrice());
				pstmt.setString(9, gdto.getImage());
				pstmt.setInt(10, gdto.getBest());
				
				pstmt.executeUpdate();
				
				System.out.println("DAO : 상품 등록 완료");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
		}//insertGoods(gdto)
		
		
		//getGoodsList()
		public List<GoodsDTO> getGoodsList(){
			
			List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
			
			try {
				con = getCon();
				
				sql = "select * from itwill_goods";
				
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					GoodsDTO gdto = new GoodsDTO();
					
					gdto.setGno(rs.getInt("gno"));
					gdto.setCategory(rs.getString("category"));
					gdto.setName(rs.getString("name"));
					gdto.setContent(rs.getString("content"));
					gdto.setSize(rs.getString("size"));
					gdto.setColor(rs.getString("color"));
					gdto.setAmount(rs.getInt("amount"));
					gdto.setPrice(rs.getInt("price"));
					gdto.setImage(rs.getString("image"));
					gdto.setBest(rs.getInt("best"));
					gdto.setDate(rs.getDate("date"));
					
					goodsList.add(gdto);
					
				}
				
				System.out.println("DAO : 등록된 상품 리스트에 저장 완료 ");
				System.out.println(goodsList);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
			
			return goodsList;
		}
		//getGoodsList()
		
		//getGoods(gno)
		public GoodsDTO getGoods(int gno){
			
			GoodsDTO gdto = null;
			
			try {
				con = getCon();
				
				// 상품 번호에 해당하는 상품 정보 전부를 저장
				sql = "select * from itwill_goods where gno =?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, gno);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					
					gdto = new GoodsDTO();
					
					gdto.setGno(rs.getInt("gno"));
					gdto.setName(rs.getString("name"));
					gdto.setAmount(rs.getInt("amount"));
					gdto.setBest(rs.getInt("best"));
					gdto.setCategory(rs.getString("category"));
					gdto.setColor(rs.getString("color"));
					gdto.setContent(rs.getString("content"));
					gdto.setDate(rs.getDate("date"));
					gdto.setImage(rs.getString("image"));
					gdto.setPrice(rs.getInt("price"));
					gdto.setSize(rs.getString("size"));
				}
				System.out.println("DAO : 상품 검색 성공! " + gdto);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
			return gdto;
		}//getGoods(gno)
		
		//modifyGoods(dto)
		public void modifyGoods(GoodsDTO gdto){ //관리가 계정으로 바로 수정하므로 굳이 리턴 없어도됨.
			
			try {
				con = getCon();
				
				sql = "update itwill_goods set category=?, name=?, price=?, color=?, amount=?, size=?, content=?, best=? "
						+ "where gno =? ";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, gdto.getCategory());
				pstmt.setString(2, gdto.getName());
				pstmt.setInt(3, gdto.getPrice());
				pstmt.setString(4, gdto.getColor());
				pstmt.setInt(5, gdto.getAmount());
				pstmt.setString(6, gdto.getSize());
				pstmt.setString(7, gdto.getContent());
				pstmt.setInt(8, gdto.getBest());
				pstmt.setInt(9, gdto.getGno());
				
				int tmp = pstmt.executeUpdate();
				
				System.out.println("DAO : 상품 정보 수정완료! -> " +tmp);
				//1이 나와야 정상처리
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
		}
		//modifyGoods(dto)
		
		//deleteGoods(gno)
		public void deleteGoods(int gno){
			
			try {
				con = getCon();
				
				sql = "delete from itwill_goods where gno=?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, gno);
				
				int tmp = pstmt.executeUpdate();
				
				System.out.println("DAO : 상품 삭제 완료 -> " + tmp);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
		}
		//deleteGoods(gno)
		
		
		
		
		
	
	
	
	
	
	
	
	

}
