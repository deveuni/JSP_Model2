package com.itwillbs.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.order.db.OrderDAO;

public class OrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : OrderDetailAction_execute() 호출");

		// 로그인 제어
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}

		// 주문번호 (trade_num) 저장(파라미터)
		String trade_num 
		  = request.getParameter("trade_num");

		// DAO - 주문번호에 해당하는 정보 전부 가져오기
		OrderDAO ordao = new OrderDAO();
		
		// 저장
		request.setAttribute("orderDetailList", ordao.getOrderDetail(trade_num));

		// 페이지 이동("./goods_order/order_detail.jsp")
		forward.setPath("./goods_order/order_detail.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
