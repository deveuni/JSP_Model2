package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : AdminGoodsModifyFormAction_execute() 호출");
		
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
		
		// 전달된 파라미터값 저장 (gno)
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		// AdminGoodsDAO() 객체 생성 - getGoods(gno)
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		
		
		// 상품 번호에 해당하는 상품정보 전부 가져오기 
		GoodsDTO gdto = agdao.getGoods(gno);
		
		// request 영역에 저장
		request.setAttribute("gdto", gdto);
		//request.setAttribute("gdto", agdao.getGoods(gno)); 
		
		// View 페이지로 이동('./admingoods/admin_goods_modify.jsp')
		//ActionForward forward = new ActionForward();
		forward.setPath("./admingoods/admin_goods_modify.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
