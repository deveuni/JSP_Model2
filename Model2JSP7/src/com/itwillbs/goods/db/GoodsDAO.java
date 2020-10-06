package com.itwillbs.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.basket.db.BasketDTO;

public class GoodsDAO {
	

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
		
		
		
		
		//GoodsListAll()
		public List<GoodsDTO> GoodsListAll(){
			
			List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
			
			
			try {
				con = getCon();
				
				sql = "select * from itwill_goods";
				
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					GoodsDTO gdto = new GoodsDTO();
					
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
					
					goodsList.add(gdto);
					
				}
				
				System.out.println("DAO : 상품 정보 저장 완료");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
			return goodsList;
		}//GoodsListAll()
		
		//GoodsList(item)
		public List<GoodsDTO> GoodsList(String item){
			
			List<GoodsDTO> goodsList =  new ArrayList<GoodsDTO>();
			StringBuffer SQL = new StringBuffer();
			
			//sql = "abc";
			//sql.toUpperCase(); // ABC
			// sql = abc
			
			// String tmp = sql.toUpperCase(); //ABC
			// sql = tmp; => ABC
			
			// <StringBuffer>
			// SQL = "abc";
			//SQL.substring(1); //bc
			//SQL => bc
			
			try {
				con = getCon();
				
				// 전체, 카테고리, 인기 상품
				/*sql = "select * from itwill_goods";
				sql = "select * from itwill_goods where category=?";
				sql = "select * from itwill_goods where best=?";*/
				
				//SQL = "select * from itwill_goods"; (x)
				SQL.append("select * from itwill_goods");
				
				if(item.equals("all")){
					
				}else if(item.equals("best")){
					SQL.append(" where best=?"); // 띄어쓰기 주의
				}else {
					SQL.append(" where category=?");
				}
				
				// pstmt 객체는 String 타입만 받으므로 
				pstmt = con.prepareStatement(SQL.toString());
				//pstmt = con.prepareStatement(SQL+""); 연결자 붙여서 String 타입으로 ~(객체는 제외)
				
				// ?
				if(item.equals("all")){
					
				}else if(item.equals("best")){
					pstmt.setInt(1, 1); // DB-best 값이 1인 요소 검색 
				}else{
					pstmt.setString(1, item);
				}
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					GoodsDTO gdto = new GoodsDTO();
					
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
					
					goodsList.add(gdto);
					
				}
				
				System.out.println("DAO : 상품 정보 저장 완료");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
			return goodsList;
		}
		//GoodsList(item)
		
		//getGoods(gno)
		public GoodsDTO getGoods(int gno){
			
			GoodsDTO gdto = null;
			
			try {
				con = getCon();
				
				sql = "select * from itwill_goods where gno =?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, gno);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					
					gdto = new GoodsDTO();
					
					gdto.setAmount(rs.getInt("amount"));
					gdto.setBest(rs.getInt("best"));
					gdto.setCategory(rs.getString("category"));
					gdto.setColor(rs.getString("color"));
					gdto.setContent(rs.getString("content"));
					gdto.setDate(rs.getDate("date"));
					gdto.setGno(rs.getInt("gno"));
					gdto.setImage(rs.getString("image"));
					gdto.setName(rs.getString("name"));
					gdto.setPrice(rs.getInt("price"));
					gdto.setSize(rs.getString("size"));
					
				}
				
				System.out.println("DAO : 상품번호에 해당하는 상품정보 저장 완료");
				System.out.println("DAO : " + gdto);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
			return gdto;
		}
		//getGoods(gno)
		
		//updateAmount(basketList)
		// 구매 후 처리하는 동작
		public void updateAmount(List<BasketDTO> basketList){
		
			try {
				getCon();
				
				// 구매한 상품 만큼 수량 차감
				
				for(int i=0;i<basketList.size();i++){
				
					BasketDTO bkdto = (BasketDTO)basketList.get(i);
					
				sql = "update itwill_goods set amount = amount - ? where gno =? ";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, bkdto.getB_g_amount());
				pstmt.setInt(2, bkdto.getB_g_num());
				
				pstmt.executeUpdate();
		
				}
				System.out.println("DAO : 구매 후 상품 수량 수정 완료");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
			
		}//updateAmount(basketList)
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
