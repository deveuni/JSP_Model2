package com.itwillbs.order.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class OrderStarAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : OrderStarAction_execute() 호출");
		
		// 로그인 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;			
		}
		
		// 구매할 정보 (장바구니) - 저장
		// BasketDAO 객체 생성
		BasketDAO bkdao = new BasketDAO();
		Vector total = bkdao.getBasketList(id);
		
		// 구매자 정보 (회원정보) - 저장
		// MemberDAO 객체 
		MemberDAO mdao = new MemberDAO();
		//MemberBean mb = mdao.getMember(id);
		
		// 장바구니 정보
		request.setAttribute("basketList", total.get(0));
		// 장바구니에 저장된 상품정보
		request.setAttribute("goodsList", total.get(1));
		// 구매하는 사람의 정보
		request.setAttribute("memberBean", mdao.getMember(id));

		// 페이지 이동("./goods_order/goods_buy.jsp")
		forward.setPath("./goods_order/goods_buy.jsp");
		forward.setRedirect(false);
		return forward;
		
		
	}

}
