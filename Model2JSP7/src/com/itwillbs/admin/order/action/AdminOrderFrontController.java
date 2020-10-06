package com.itwillbs.admin.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminOrderFrontController extends HttpServlet {
	
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
		
		// 주문 목록 
		if(command.equals("/AdminOrderList.ao")){
		
			System.out.println("C : /AdminOrderList.ao 호출");
			
			action = new AdminOrderListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminOrderModify.ao")){
			System.out.println("C : /AdminOrderModify.ao 호출");
			
			// AdminOrderModifyAction 객체 생성
			action = new AdminOrderModifyAction();
			
			try {
				forward = action.execute(request, response); // 다형성
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminOrderDetail.ao")){
			System.out.println("C : /AdminOrderDetail.ao 호출");
			
			// AdminOrderDetailAction 
			action = new AdminOrderDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminOrderDelete.ao")){
			System.out.println("C : /AdminOrderDelete.ao 호출");
			
			action = new AdminOrderDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*else if(command.equals("test")){
			// 익명 클래스 (내부 클래스) : 일회성 데이터 사용 - 메서드로 인지(인터페이스 안의 추상메서드 바로 구현) 
			// 컨트롤러에 집중되기 때문에 람다식 별로 안씀.
			
			// control + 1
			action = (request1, response1) -> null;
			
		}
		*/
		
		
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

	
	
	
	
	

