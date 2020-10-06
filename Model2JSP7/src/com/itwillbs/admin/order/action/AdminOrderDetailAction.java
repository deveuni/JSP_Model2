package com.itwillbs.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.order.db.AdminOrderDAO;
import com.itwillbs.admin.order.db.AdminOrderDAOImpl;

public class AdminOrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : AdminOrderDetailAction_execute() 호출");
		
		// 세션 제어 (관리자)
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if (id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
				return forward;
		}
		
		// 전달 정보 파라미터값 저장 (trade_num)
		String trade_num = request.getParameter("trade_num");
		
		// 주문 번호에 해당하는 정보를 전부 가져오는 동작을 처리 
		// 주문번호에 따라 묶음 배송 처리 하므로 
		// getAdminOrderDetail(trade_num)
		AdminOrderDAO aodao = new AdminOrderDAOImpl();
		
		//aodao.getAdminOrderDetail(trade_num);
		
		// 주문정보를 저장
		request.setAttribute("adminOrderDetailList", aodao.getAdminOrderDetail(trade_num));
		
		// 페이지 이동 ("./adminorder/admin_order_modify.jsp")
		forward.setPath("./adminorder/admin_order_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
