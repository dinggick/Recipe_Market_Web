package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.AddException;
import com.recipe.exception.DuplicatedException;
import com.recipe.exception.FindException;
import com.recipe.exception.RemoveException;
import com.recipe.service.ReviewService;
import com.recipe.vo.Customer;
import com.recipe.vo.Purchase;
import com.recipe.vo.Review;

public class ReviewController implements Controller {
	private static ReviewController instance;
	private ReviewService reviewService;

	private ReviewController() {
		reviewService = ReviewService.getInstance();
	}

	public static ReviewController getInstance() {
		if (instance == null)
			instance = new ReviewController();
		return instance;
	}
	
	/**
	 * @author Soojeong
	 * 리뷰 관련 controller 
	 * 나의 후기목록 보기	- /review/myReviewList
	 * 나의 후기 삭제		- /review/remove
	 * 나의 후기 등록 	- /review/add
	 * 레시피코드별 후기목록 보기  - /review/reviewListByRecipeCode?recipeCode=?
	 */

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = "/fail.jsp";

		// 후기 관련 메뉴는 로그인 사용자만 접근가능
		Customer loginUser = (Customer)request.getSession().getAttribute("loginInfo");
		if( loginUser == null) {
			request.setAttribute("msg","로그인 되지 않은 사용자의 접근입니다.");
			return servletPath;
		} 

		// 나의 리뷰 목록 보기 
		String pathInfo = request.getServletPath();
		if ( "/review/myReviewList".equals(pathInfo) ) {
			servletPath = myReviewList(request, response, loginUser);
			
		// 레시피별 리뷰 목록 보기
		} else if ( "/review/reviewListByRecipeCode".equals(pathInfo) ) {
			servletPath = reviewListByRecipeCode(request, response);
			
		// 리뷰등록 
		} else if ( "/review/add".equals(pathInfo) ) { 
			servletPath = addReview(request, response);
		
		// 리뷰삭제 
		} else if ( "/review/remove".equals(pathInfo)) {
			servletPath = removeReview(request, response);
		}
		return servletPath;
	} // end of execute();
	
	
	private String myReviewList (HttpServletRequest request, HttpServletResponse response, Customer loginUser) {
		String result = "/fail.jsp";
		
		try {
			List<Review> myReviewList = reviewService.findByEmail(loginUser.getCustomerEmail());
			request.setAttribute("myReviewList", myReviewList);
			result = "/myReviewList.jsp";
		
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg",e.getMessage());
		}
		
		return result;
	}
	
	private String removeReview (HttpServletRequest request, HttpServletResponse response) {
		String result = "/fail.jsp";
		Purchase purchase = (Purchase) request.getAttribute("purchase");
		if ( purchase == null ) {
			request.setAttribute("msg","구매정보가 없습니다.");
		}
		
		try {
			reviewService.remove(purchase.getPurchaseCode());
			result = "/success.jsp"; 
			
		} catch (RemoveException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		
		return result;
	}
	
	private String addReview (HttpServletRequest request, HttpServletResponse response) {
		String result = "/fail.jsp";
		
		Purchase purchase = (Purchase)request.getAttribute("purchase");
		if ( purchase == null ) {
			request.setAttribute("msg","구매정보가 없습니다.");
		}
		
		Review r = new Review();
		r.setPurchase(purchase);
		r.setReviewComment((String)request.getAttribute("recipeComment"));
		
		try {
			reviewService.add(r);
			result = "/success.jsp"; 
		
		} catch (DuplicatedException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			
		} catch (AddException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		
		return result;
		
	}
	
	private String reviewListByRecipeCode (HttpServletRequest request, HttpServletResponse response) {
		String result = "/fail.jsp";
		
		int recipeCode = (int)request.getAttribute("recipeCode");
		if ( recipeCode == 0 ) {
			request.setAttribute("msg","레시피코드값이 전달되지않았습니다.");
		}
		try {
			List<Review> list = reviewService.findByCode(recipeCode);
			request.setAttribute("reviewListByRecipeCode", list);
			result = "/reviewListByRecipeCode.jsp";
		
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg",e.getMessage());
		}
		
		return result;
	}
} // end of ReviewController
