package com.itwillbs.basket.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.basket.db.BasketDTO;

public class BasketListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : BasketListAction_execute() 호출");
		
		// 로그인 정보 체크 
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		
		if(id== null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// BasketDAO - getBasketList(ID)
		// 아이디에 해당하는 장바구니 정보를 가지고 오기
		BasketDAO bkdao = new BasketDAO();
		
		// 장바구니 정보(옵션, 구매수량..) + 상품 정보(이름, 이미지, 가격...) 
		Vector totalData = bkdao.getBasketList(id);
		
		System.out.println("M : 장바구니 개수 : " + totalData.size());
		
		List<BasketDTO> basketList = (List<BasketDTO>)totalData.get(0);
		
		System.out.println("M : 장바구니 리스트 개수 : " + basketList.size());
		
		// 장바구니 정보가 하나도 없을 경우 
		if(basketList.size() <= 0){
			request.setAttribute("basketListNull", false);
		}else{
			request.setAttribute("basketListNull", true);
		}
		
		
		
		// 저장
		//request.setAttribute("totalData", totalData);
		// 필요할 때 꺼내기 쉽게 하기 위해서 저장한 다음 넘기기 
		// => 원래 정보는 백터에 저장되어 있지만, Action페이지에서 
		// 백터의 정보를 꺼내서 각각 request에 저장해서 전달 
		// => View에서는 복잡한 연산을 피해야 함. 바로 사용 가능한 List형태로 전달하는 것이 좋다(성능때문)
		
		/*List<BasketDTO> basketList =(List<BasketDTO>) totalData.get(0);
		request.setAttribute("basketList", basketList);*/
		
		request.setAttribute("basketList", totalData.get(0));
		request.setAttribute("goodsList", totalData.get(1));
		
		
		
		// 페이지 이동(./goods_order/goods_basket.jsp)
		forward.setPath("./goods_order/goods_basket.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
