package com.itwillbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {
	
	
	// (1) init
	// (2) service(http,http)
	// (3) doGet/doPost(request,response)
	// (4) 재 호출 (F5) 
	// (5) (2),(3) 반복
	// (6) 내용 변경 후 페이지 리로딩시 destroy()
	// (7) 다시 (1) 시작
	
	
	
	@Override
	public void init() throws ServletException {
		System.out.println("서블릿이 초기화!");
		System.out.println("최초 한번만 초기화 (서블릿 로딩)");
		
	}
	

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println(" service(Http,Http) ");
		//doGet(arg0, arg1);
	}


	/*@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println(" service(,) ");
		//doGet(request, response);
		service(arg0, arg1);
	}*/


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() -> doPost()" );
		doPost(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()!!!!!!!");
	}
	
		
	@Override
	public void destroy() {
		System.out.println("destroy() ");
	}

	

}
