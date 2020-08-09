package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.ModifyException;
import com.recipe.service.RecipeService;

public class PointController implements Controller {
	private static PointController instance;
	private RecipeService service;
	
	private PointController() {
		service = RecipeService.getInstance();
	}
	
	public static PointController getInstance() {
		if(instance == null) instance = new PointController();
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		String forwardPath = "/fail.jsp";
		
		if("/point/like".equals(servletPath)) {
			forwardPath = like(request, response);
		} else if("/point/dislike".equals(servletPath)) {
			forwardPath = disLike(request, response);
		}
		
		return forwardPath;
	}
	
	private String like(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int recipeCode = Integer.parseInt(request.getParameter("recipeCode"));
		String result = "/fail.jsp";
		try {
			service.like(recipeCode);
			result = "/success.jsp";
		} catch (ModifyException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			result = "/fail.jsp";
		}
		return result;
	}
	
	private String disLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int recipeCode = Integer.parseInt(request.getParameter("recipeCode"));
		String result = "/fail.jsp";
		try {
			service.disLike(recipeCode);
			result = "/success.jsp";
		} catch (ModifyException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			result = "/fail.jsp";
		}
		return result;
	}
}
