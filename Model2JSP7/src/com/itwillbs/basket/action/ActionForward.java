package com.itwillbs.basket.action;

public class ActionForward {
	
	// 이동정보를 저장하는 객체 
	// (이동할 페이지, 방식)
	
	private String path;
	private boolean isRedirect;
	// true - sendRedirect 방식 이동
	// false - forward 방식 이동
	
	
	
	@Override
	public String toString() {
		return "ActionForward [path=" + path + ", isRedirect=" + isRedirect + "]";
	}

	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
