package com.itwillbs.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;

public class BasketDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M :BasketDeleteAction_execute() 호출");
		
		// 로그인 정보  (로그인 처리 필요)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
				
		ActionForward forward = new ActionForward();
		if(id== null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		
		// 전달되는 정보
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		
		// 객체 생성 
		BasketDAO bkdao = new BasketDAO();
		bkdao.basketDelete(b_num);
		
		// 장바구니 통째로 지울 때는 id값 넣어서 지우기 
		
		
		// 페이지 이동
		//ActionForward forward = new ActionForward();
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);
		
		
		
		return forward;
	}

}
