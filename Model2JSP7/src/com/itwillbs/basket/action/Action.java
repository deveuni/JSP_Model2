package com.itwillbs.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	// 상수,추상메서드
	
		// 추상메서드 -> 서브클래스들 한태 강제성 부여
		// => 개발 형식의 통일(틀이 정해짐)
		// => 객체간의 관계가 약화됨 => 각각의 객체가 해당 동작만 처리/제어
		
		// Action 페이지의 동작을 미리 선언해서 사용
		public ActionForward execute(HttpServletRequest request,
				HttpServletResponse response) throws Exception;

}
