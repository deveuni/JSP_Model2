package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;

public class AdminGoodsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : AdminGoodsDeleteAction_execute() 호출");
		
		// 관리자 계정 확인(세션ID)
				HttpSession session = request.getSession();

				String id = (String) session.getAttribute("id");

				ActionForward forward = new ActionForward();
				if (id == null || !id.equals("admin")) {
					// response.sendRedirect("./Main.me");
					forward.setPath("./Main.me");
					forward.setRedirect(true);
					return forward;
				}
		
	
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		
		agdao.deleteGoods(gno);
		
		//ActionForward forward = new ActionForward();
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);
		
		
		return forward;
	}

}
