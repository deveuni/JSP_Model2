package com.itwillbs.admin.goods.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : AdminGoodsModifyProAction_execute() 호출");
		
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
				
		
		// 한글처리 
		request.setCharacterEncoding("UTF-8");
		
		// jsp에서 수정한 정보 가져오기
		// GoodsDTO 사용 
		GoodsDTO gdto = new GoodsDTO();
		gdto.setCategory(request.getParameter("category"));
		gdto.setName(request.getParameter("name"));
		gdto.setPrice(Integer.parseInt(request.getParameter("price")));
		gdto.setColor(request.getParameter("color"));
		gdto.setSize(request.getParameter("size"));
		gdto.setAmount(Integer.parseInt(request.getParameter("amount")));
		gdto.setContent(request.getParameter("content"));
		gdto.setBest(Integer.parseInt(request.getParameter("best")));
		gdto.setGno(Integer.parseInt(request.getParameter("gno")));
		//gdto.setImage(request.getParameter("image"));
		
		// GoodsDAO 객체 생성 - 수정메서드- modifyGoods(dto)
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		
		agdao.modifyGoods(gdto);
		
		// 페이징 처리
		//ActionForward forward = new ActionForward();
		
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}

}
