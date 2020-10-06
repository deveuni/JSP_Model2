package com.itwillbs.order.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.basket.db.BasketDTO;
import com.itwillbs.goods.db.GoodsDAO;
import com.itwillbs.order.db.OrderDAO;
import com.itwillbs.order.db.OrderDTO;


public class OrderAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : OrderAddAction_execute() 호출");

		// 로그인 제어
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}

		// 전달된 정보를 저장
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// OrderDTO 객체 생성 -> 정보 저장
		OrderDTO odto = new OrderDTO();
		
		odto.setO_m_id(id);
		odto.setO_receive_name(request.getParameter("o_receive_name"));
		odto.setO_receive_phone(request.getParameter("o_receive_phone"));
		odto.setO_receive_addr1(request.getParameter("o_receive_addr1"));
		odto.setO_receive_addr2(request.getParameter("o_receive_addr2"));
		odto.setO_receive_memo(request.getParameter("o_receive_memo"));
		
		odto.setO_trade_payer(request.getParameter("o_trade_payer"));
		odto.setO_trade_type("온라인 입금");// 라디오 버튼값

		BasketDAO bkDao = new BasketDAO();
		Vector data = bkDao.getBasketList(id);

		// 장바구니 정보
		List<BasketDTO> basketList = (List<BasketDTO>)data.get(0);
		// 상품정보
        List<GoodsDTO> goodsList = (List<GoodsDTO>)data.get(1);
        
		// 결재 모듈(카카오,아임포트,U+) 추가영역
        System.out.println("M : 사용자 결재 완료!!! ");
        
        
		// OrderDAO 객체 생성 - addOrder(주문정보,장바구니정보,상품정보)
        OrderDAO orDao = new OrderDAO();
        orDao.addOrder(odto,basketList,goodsList);
        
        System.out.println("M : itwill_order 테이블에 저장완료");
        
        // 구매 확정 메일, 문자, 카카오톡 메세지
        System.out.println("M : 사용자 거래 내역 메일 전송 완료");       
        
        // 상품 정보 수정 - 구매 수량 만큼 차감(상품 개수)
        // itwill_goods 테이블 정보 수정
        GoodsDAO gdao = new GoodsDAO();
        gdao.updateAmount(basketList);
                
        // 상품 장바구니 비우기  (삭제)
        // itwill_basket 테이블 접근
        bkDao.basketDelete(id);

		// 페이지이동 (주문목록)
        forward.setPath("./OrderList.or");
        forward.setRedirect(true);

		return forward;
	}

}
