package com.itwillbs.admin.order.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.order.db.OrderDTO;

public class AdminOrderDAOImpl implements AdminOrderDAO {
	
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
		// 디비연결
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
	
	@Override
	public List getAdminOrderList() {
		
		List AdminOrderList = new ArrayList();

		
		try {
			// 디비연결
			getCon();
			
			// sql
			sql = "select o_trade_num,o_g_name,o_g_amount,"
					+ "o_g_size,o_g_color,sum(o_sum_money) as o_sum_money,"
					+ "o_trans_num,o_date,o_status,o_trade_type,o_m_id "
					+ "from itwill_order "
					+ "group by o_trade_num order by o_trade_num desc";
			
			// pstmt
			pstmt = con.prepareStatement(sql);
			
			// 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				OrderDTO odto = new OrderDTO();
				
				odto.setO_date(rs.getDate("o_date"));
				odto.setO_g_amount(rs.getInt("o_g_amount"));
				odto.setO_g_color(rs.getString("o_g_color"));
				odto.setO_g_name(rs.getString("o_g_name"));
				odto.setO_g_size(rs.getString("o_g_size"));
				odto.setO_trade_num(rs.getString("o_trade_num"));
				odto.setO_trans_num(rs.getString("o_trans_num"));
				odto.setO_sum_money(rs.getInt("o_sum_money"));
				odto.setO_status(rs.getInt("o_status"));
				odto.setO_trade_type(rs.getString("o_trade_type"));
				odto.setO_m_id(rs.getString("o_m_id"));
				
				AdminOrderList.add(odto);
				
			}// while
			
			System.out.println("DAO : 관리자 주문목록 저장 완료");
			System.out.println(AdminOrderList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return AdminOrderList;
	}


	@Override
	public void updateOrder(String trade_num) {
		// 주문상태 변경 "대기중(0)" -> "발송준비(1)"
		
		try {
			getCon();
			
			sql = "update itwill_order set o_status=? "
					+ "where o_trade_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, trade_num);
			
			pstmt.executeUpdate();

			System.out.println("DAO : 주문번호  사용 상태 변경(대기중->발송준비)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}

	@Override
	public void updateOrder(OrderDTO odto) {
		
		try {
			getCon();
			
			sql = "update itwill_order set o_status=?, o_trans_num=? where o_trade_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, odto.getO_status());
			pstmt.setString(2, odto.getO_trans_num());
			pstmt.setString(3, odto.getO_trade_num());
			
			int num = pstmt.executeUpdate(); //위에 적용된 row 수를 리턴함. 1리턴-하나가 바뀜 , 2리턴-2개 바뀜
			
			
			System.out.println("DAO : 주문상태, 운송장번호 수정완료 ("+num+")");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}


	@Override
	public List getAdminOrderDetail(String trade_num) {
		
		List<OrderDTO> adminOrderDetailList = new ArrayList<OrderDTO>();
		
		try {
			getCon();
			
			sql = "select * from itwill_order where o_trade_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, trade_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				OrderDTO odto = new OrderDTO();
				// 디테일 수정 페이지 들어가서 수정하기
				// 사용자가 어떤 정보들을 수정을 원하는지 모르므로 다 가져가기
				
				odto.setO_date(rs.getDate("o_date"));
				odto.setO_g_amount(rs.getInt("o_g_amount"));
				odto.setO_g_color(rs.getString("o_g_color"));
				odto.setO_g_name(rs.getString("o_g_name"));
				odto.setO_g_num(rs.getInt("o_g_num"));
				odto.setO_g_size(rs.getString("o_g_size"));
				odto.setO_m_id(rs.getString("o_m_id"));
				odto.setO_receive_memo(rs.getString("o_receive_memo"));
				odto.setO_num(rs.getInt("o_num"));
				odto.setO_receive_addr1(rs.getString("o_receive_addr1"));
				odto.setO_receive_addr2(rs.getString("o_receive_addr2"));
				odto.setO_receive_name(rs.getString("o_receive_name"));
				odto.setO_receive_phone(rs.getString("o_receive_phone"));
				odto.setO_status(rs.getInt("o_status"));
				odto.setO_sum_money(rs.getInt("o_sum_money"));
				odto.setO_trade_date(rs.getDate("o_trade_date"));
				odto.setO_trade_num(rs.getString("o_trade_num"));
				odto.setO_trade_payer(rs.getString("o_trade_payer"));
				odto.setO_trade_type(rs.getString("o_trade_type"));
				odto.setO_trans_num(rs.getString("o_trans_num"));
				
				adminOrderDetailList.add(odto);
				
			}// while
			
			System.out.println("DAO : 관리자 주문 정보 수정 - 리스트 저장완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return adminOrderDetailList;
	}


	@Override
	public void deleteOrder(String trade_num) {
		
		try {
			getCon();
			
			sql = "delete from itwill_order where o_trade_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, trade_num);
			
			int result = pstmt.executeUpdate();
			
			System.out.println("DAO : 관리자 주문목록 삭제 (" + result + ")");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}
