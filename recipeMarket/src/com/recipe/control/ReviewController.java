package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.service.ReviewService;
import com.recipe.vo.Review;

public class ReviewController implements Controller {
	private static ReviewController instance;
	private ReviewService reviewService;
	
	private ReviewController() {
		reviewService = ReviewService.getInstance();
	}
	
	public static ReviewController getInstance() {
		if(instance == null) instance = new ReviewController();
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		try {
			List<Review> list = reviewService.findById("kosj");
			request.setAttribute("reviewList", list);
			request.getSession().setAttribute("loginInfo", id);
			return "/myReviewList.jsp";
			
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";
		}
	}

}
