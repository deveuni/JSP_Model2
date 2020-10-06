package com.itwillbs.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.order.db.AdminOrderDAO;
import com.itwillbs.admin.order.db.AdminOrderDAOImpl;


public class AdminOrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : AdminOrderListAction_execute() 호출");
		
		// 관리자 ID에 해당하는 세션값 제어
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if (id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
				return forward;
		}
		
		// AdminOrderDAO 객체 생성 
		// - getAdminOrderList()
		//AdminOrderDAO aodao = new AdminOrderDAO(); (x)
		AdminOrderDAO aodao = 
					new AdminOrderDAOImpl(); //업캐스팅
		
		// 저장 
		request.setAttribute("adminOrderList", aodao.getAdminOrderList());
		
		// 페이지 이동 ("./admingoods/admin_order_list.jsp")
		forward.setPath("./adminorder/admin_order_list.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
