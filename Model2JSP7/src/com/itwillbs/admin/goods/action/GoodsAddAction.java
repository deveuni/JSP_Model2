package com.itwillbs.admin.goods.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class GoodsAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : GoodsAddAction_execute() 호출");

		// 한글 처리 - 파일업로드 multipart객체 안에서 셋팅하면 됨.
		
		// 파일업로드 준비 
		// ./upload 폴더 생성 (가상경로)
		
		// 파일이 저장되는 실제 위치
		// 프로젝트의 정보
		ServletContext context = request.getServletContext();
		System.out.println("context : " + context);
		// 실제 저장 위치 
		String realPath = context.getRealPath("/upload");
		System.out.println("realPath : " + realPath);
		
		// 크기 
		int maxSize = 10 * 1024 * 1024; //10MB
		
		// Multipart 객체 생성 
		MultipartRequest multi = 
				new MultipartRequest(
						request,
						realPath,
						maxSize,
						"UTF-8",
						new DefaultFileRenamePolicy()
				);
		
		System.out.println("M : 파일업로드 완료 "+ multi);
		
		// 상품정보를 저장(파라미터)
		
		
		// GoodsDTO 객체 생성 (관리자가 올리는 상품을 사용자가 살 거기 때문에 굳이 객체를 나눌 필요가 없다.)
		// -> 저장
		GoodsDTO gdto = new GoodsDTO();
		
		gdto.setCategory(multi.getParameter("category"));
		gdto.setName(multi.getParameter("name"));
		gdto.setPrice(Integer.parseInt(multi.getParameter("price")));
		gdto.setColor(multi.getParameter("color"));
		gdto.setAmount(Integer.parseInt(multi.getParameter("amount")));
		gdto.setSize(multi.getParameter("size"));
		gdto.setContent(multi.getParameter("content"));
		
		// 인기상품 best
		gdto.setBest(0); // 0-일반상품, 1-인기상품 
		
		// 이미지 정보 처리 
		String img = multi.getFilesystemName("file1")+","
				+ multi.getFilesystemName("file2")+","
				+ multi.getFilesystemName("file3")+","
				+ multi.getFilesystemName("file4");
		
		System.out.println("img : " + img);
		
		gdto.setImage(img);
		
		// 파라미터 값 저장 완료
		System.out.println("M : 정보 저장완료 GoodsDTO " + gdto);
		
		
		// DB처리는 관리자가 올리는 상품과 일반사용자가 처리하는 상품 구분해야함
		// AdminGoodsDAO 객체 생성 -> 메서드 사용 
		// insertGoods()
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		agdao.insertGoods(gdto);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}

}
