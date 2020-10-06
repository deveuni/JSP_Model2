package com.itwillbs.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OrderFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doProcess() !");

		System.out.println("--------Controller 진입-------------");

		System.out.println("--------------@ 주소 계산 @-------------");
		// requestURI : /Model2JSP7/test.me
		// 프로젝트명 + 주소
		String requestURI = request.getRequestURI();
		System.out.println(" requestURI : " + requestURI);

		// contextPath : /Model2JSP7
		// 프로젝트명
		String contextPath = request.getContextPath();
		System.out.println(" contextPath : " + contextPath);

		// 가상주소
		// /test.me
		String command = requestURI.substring(contextPath.length());
		System.out.println(" command(가상주소) : " + command);

		System.out.println("--------------@ 주소 계산 @-------------");

		System.out.println("--------------@ 주소 비교 @--------------");
		Action action= null;
		ActionForward forward = null;
		
		if(command.equals("/OrderStar.or")){
			System.out.println("C : /OrderStar.or 호출");
			// DB에서 구매할 정보를 찾아오기 (장바구니 정보)
			// OrderStarAction() 객체 생성
			action = new OrderStarAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/OrderAdd.or")){
			System.out.println("C : /OrderAdd.or 호출 ");
			
			// OrderAddAction객체 생성
			action = new OrderAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/OrderList.or")){
			System.out.println("C : /OrderList.or 호출");
			
			// OrderListAction() 객체 생성
			action = new OrderListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.trim().equals("/OrderDetail.or")){
			System.out.println("C: /OrderDetail.or 호출");
			// OrderDetailAction객체 생성
			action = new OrderDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		System.out.println("--------------@ 주소 비교 @--------------");

		System.out.println("--------------@ 주소 이동 @--------------");
		if (forward != null) { // 이동할 정보가 있다

			if (forward.isRedirect()) { // true - sendRedirect()
				// 가상주소(.bo -> .bo), 화면전환(주소변경,화면 변경)
				System.out.println("C : " + forward.getPath() + "주소로 이동(Redirect)");
				response.sendRedirect(forward.getPath());

			} else { // false - forward()
				System.out.println("C : " + forward.getPath() + "주소로 이동(forward)");
				// 가상주소 -> 실제페이지 (.bo -> .jsp) + reqeust 객체 정보를 가지고 이동
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());

				dis.forward(request, response);
			}

		}

		System.out.println("--------------@ 주소 이동 @--------------");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet() !");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost() !");
		doProcess(request, response);
	}

}
