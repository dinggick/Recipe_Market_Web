package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.exception.AddException;
import com.recipe.exception.DuplicatedException;
import com.recipe.exception.FindException;
import com.recipe.exception.RemoveException;
import com.recipe.model.PageBean;
import com.recipe.service.ReviewService;
import com.recipe.vo.Purchase;
import com.recipe.vo.RecipeInfo;
import com.recipe.vo.Review;


@WebServlet("/review/")
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
		String customerEmail = (String)request.getSession().getAttribute("loginInfo");
		if( customerEmail == null) {
			request.setAttribute("msg","로그인 되지 않은 사용자의 접근입니다.");
			return servletPath;
		}

		// 나의 리뷰 목록 보기 
		String pathInfo = request.getServletPath();
		if ( "/review/myReviewList".equals(pathInfo) ) {
			servletPath = myReviewList(request, response, customerEmail);
			
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
	
	
	private String myReviewList (HttpServletRequest request, HttpServletResponse response, String customerEmail) {
		String result = "/fail.jsp";
		
		String strPage = request.getParameter("currentPage");
		int currentPage = 1;
		if(!"".equals(strPage))
			currentPage = Integer.parseInt(strPage);

		HttpSession session = request.getSession();
		session.setAttribute("recentPage", currentPage);
		
		PageBean pb;
		try {
			
			pb = reviewService.findByEmailAll(currentPage, customerEmail);
			result = "/myReviewList.jsp";
			pb.setUrl(result);
			request.setAttribute("pb", pb);
			
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			
		}
		return result;
	}
	
	private String removeReview (HttpServletRequest request, HttpServletResponse response) {
		String result = "/fail.jsp";
		int purchaseCode = Integer.parseInt(request.getParameter("purchaseCode"));
		int recipeCode = Integer.parseInt(request.getParameter("recipeCode"));
		if (purchaseCode == 0  || recipeCode == 0) {
			request.setAttribute("msg","구매정보가 없습니다.");
		}
		
		try {
			reviewService.remove(purchaseCode, recipeCode);
			result = "/success.jsp"; 
			
		} catch (RemoveException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		
		return result;
	}
	
	private String addReview (HttpServletRequest request, HttpServletResponse response) {
		String result = "/fail.jsp";
		
		int purchaseCode = Integer.parseInt(request.getParameter("purchaseCode"));
		int recipeCode = Integer.parseInt(request.getParameter("recipeCode"));
		String reviewContent = (String)request.getParameter("reviewContent");
		
		if ( purchaseCode == 0 || "".equals(reviewContent) || recipeCode == 0 ) {
			request.setAttribute("msg","구매정보가 없습니다.");
		}
		
		Purchase p = new Purchase();
		p.setPurchaseCode(purchaseCode);
		
		RecipeInfo recipeInfo = new RecipeInfo();
		recipeInfo.setRecipeCode(recipeCode);

		Review r = new Review();
		r.setPurchase(p);
		r.setRecipeInfo(recipeInfo);
		r.setReviewComment(reviewContent);
		

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
		
		
	  	int recipeCode = Integer.parseInt(request.getParameter("recipeCode"));
	  	if ( recipeCode ==  0 ) { 
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
