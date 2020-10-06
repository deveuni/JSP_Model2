package com.itwillbs.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.order.db.OrderDAO;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : OrderListAction_execute() 호출");

		// 로그인 제어
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}

		// OrderDAO - getOrderList(id)
		OrderDAO ordao = new OrderDAO();
		
		// 저장
		request.setAttribute("orderList",ordao.getOrderList(id));
		
		// 페이지 이동 ("./goods_order/order_list.jsp")
		forward.setPath("./goods_order/order_list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
