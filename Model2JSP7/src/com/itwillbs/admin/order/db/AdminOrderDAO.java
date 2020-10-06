package com.itwillbs.admin.order.db;

import java.util.List;

import com.itwillbs.order.db.OrderDTO;

public interface AdminOrderDAO {
	// 인터페이스 - 추상메서드
	
	// 관리자가 주문목록을 확인기능 - getAdminOrderList()
	public List getAdminOrderList();
	
	// 주문 번호를 사용해서 주문 상태 변경하는 기능
	public void updateOrder(String trade_num);
	
	// 주문번호를 사용해서 주문상태, 운송장번호 수정 기능
	//public void updateOrder(String trade_num, String trans_num, int status);
	public void updateOrder(OrderDTO odto);
	
	// 주문번호 사용해서 주문정보(리스트 전체)를 가져오기 
	public List getAdminOrderDetail(String trade_num);
	
	// 주문 삭제 기능
	public void deleteOrder(String trade_num);
	
	

}
