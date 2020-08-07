package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.service.RecipeService;
import com.recipe.vo.RecipeInfo;

public class SearchController implements Controller {
	private static SearchController instance;
	private RecipeService service;
	private SearchController() {}
	
	public static SearchController getInstance() {
		if(instance == null) instance = new SearchController();
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String servletPath = "";
		String recipeName = request.getParameter("recipeName");
		try {
			List<RecipeInfo> infos = service.findByName(recipeName);
			request.setAttribute("recipeList", infos);
			servletPath = "/success.jsp";
		} catch (FindException e) {
			servletPath = "/fail.jsp";
		}
		return servletPath;
	}

}
