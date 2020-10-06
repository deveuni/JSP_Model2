package com.itwillbs.admin.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : AdminGoodsList_execute() 호출");
		
		
		// 관리자 계정 확인(세션 ID)
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();

		if(id == null || !id.equals("admin")){
			//response.sendRedirect("./Main.me");
			forward.setPath("./Main.me");
			forward.setRedirect(true);
		}
		
		
		// AdminGoodsDAO 객체 생성 
		// -getGoodsList()
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		
		List<GoodsDTO> goodsList = agdao.getGoodsList();
		
		// 등록된 상품 목록 전부 가져오기 
		System.out.println("M : " + goodsList);
		
		// request 영역에 저장
		request.setAttribute("goodsList", goodsList);
		
		
		// 페이지 이동 (view : './admingoods/admin_list.jsp')
		//ActionForward forward = new ActionForward();
		forward.setPath("./admingoods/admin_goods_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
