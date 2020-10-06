package com.itwillbs.admin.goods.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminGoodsFrontController extends HttpServlet {
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("doProcess() 호출");
			System.out.println("-----------Controller 진입----------------");
			
			System.out.println("-------------------@ 주소 계산 @--------------------");
			// requestURI : /Model2JSP7/test.me
			// 프로젝트명 + 주소
			String requestURI = request.getRequestURI();
			System.out.println(" requestURI : "+requestURI);
			
			// contextPath : /Model2JSP7
			// 프로젝트명
			String contextPath = request.getContextPath();
			System.out.println(" contextPath : "+contextPath);
			
			// 가상주소 
			// /test.me
			String command = requestURI.substring(contextPath.length());
			System.out.println(" command(가상주소) : "+command);
			
			System.out.println("-------------------@ 주소 계산 @-----------------------");
			
			System.out.println("-------------------@ 주소 비교 @-----------------------");
			
			Action action = null;
			ActionForward forward = null;
			
			
			
			if(command.equals("/GoodsAdd.ag")){
				System.out.println("C : /GoodsAdd.ag 호출");
				// .ag -> .jsp 이동 
				
				// 컨트롤러 -> 뷰 페이지 이동 
				forward = new ActionForward();
				forward.setPath("./admingoods/admin_goods_write.jsp");
				forward.setRedirect(false);
				
			}else if(command.equals("/GoodsAddAction.ag")){
				
				System.out.println("C : /GoodsAddAction.ag 호출");
				
				// .jsp -> .ag -> Action -> DB 
				//GoodsAddAction()객체 생성
				
				action = new GoodsAddAction();
				
				try {
					forward = action.execute(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/AdminGoodsList.ag")){
				System.out.println(" C : /AdminGoodsList.ag 호출");
				
				// .ag -> Action -> DB -> .jsp
				// /AdminGoodsList() 객체 생성 
				
				action = new AdminGoodsList();
				
				 try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			// ***************************상품 수정폼 처리***********************************
			else if(command.equals("/AdminGoodsModify.ag")){
				
				System.out.println("C : /AdminGoodsModify.ag 호출");
				
				// .ag -> Action -> DB -> .jsp 
				// AdminGoodsModifyFormAction() 객체 생성 
				action = new AdminGoodsModifyFormAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// ***************************상품 수정 동작 처리***********************************
			else if(command.equals("/AdminGoodsModifyAction.ag")){
				
				System.out.println("C : /AdminGoodsModifyAction.ag 호출");
				
				// .jsp -> Action -> DB -> List.ag
				
				//AdminGoodsModifyProAction 객체 생성
				action = new AdminGoodsModifyProAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/AdminGoodsDelete.ag")){
				
				System.out.println("C : /AdminGoodsDelete.ag 호출");
				
				action = new AdminGoodsDeleteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			System.out.println("-------------------@ 주소 비교 @-----------------------");
			
			System.out.println("-------------------@ 주소 이동 @-----------------------");
			
			if(forward != null){ // 이동할 정보가 있다.
				
				if(forward.isRedirect()){ // true - sendRedirect()
					// 가상주소(.bo -> .bo), 화면 전환(주소변경, 화면도 변경)
					// 모델 2 - jsp로 이동하는 sendRedirect 이동 하면 잘못된 것
					System.out.println("C: "+forward.getPath()+ "주소로 이동(Redirect)");
					response.sendRedirect(forward.getPath());
					
				}else{ // false - forward() 
					System.out.println("C: "+forward.getPath()+ "주소로 이동(forward)");
					// 가상주소 -> 실제페이지 (.bo -> .jsp) + request 객체 정보를 가지고 이동
					RequestDispatcher dis = 
							request.getRequestDispatcher(forward.getPath());
					
					dis.forward(request, response);
				}
			}
			
			System.out.println("-------------------@ 주소 이동 @-----------------------");
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("doGet() 호출");
			doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("doPost() 호출");
			doProcess(request, response);
	}
	
	

}
