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
		String path = request.getServletPath();
		int recipeCode = Integer.parseInt(request.getParameter("recipeCode"));
		String forwardPath = "/fail.jsp";
		
		if("/point/like".equals(path)) {
			try {
				service.like(recipeCode);
				forwardPath = "/sucess.jsp";
			} catch (ModifyException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage().replace("\"", ""));
				forwardPath = "/fail.jsp";
			}
		} else if("/point/dislike".equals(path)) {
			
		}
		
		return forwardPath;
	}
}
