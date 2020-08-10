package com.recipe.control;

import java.io.IOException;
import java.util.ArrayList;
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
	private SearchController() {
		service = RecipeService.getInstance();
	}
	
	public static SearchController getInstance() {
		if(instance == null) instance = new SearchController();
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String servletPath = "";
		List<String> ingName = new ArrayList<>();
		
		String value = request.getParameter("ingName");
		request.setAttribute("ingName", value.trim());
		String[] str = value.trim().split(" ");
		for (String s : str) {
			ingName.add(s);
			
		}				
		try {
			List<RecipeInfo> infos = service.findRecipe(ingName);
			request.setAttribute("recipeList", infos);
			servletPath = "/recipeList.jsp";
		} catch (FindException e) {
			servletPath = "/recipeList.jsp";
			
		}
		return servletPath;
	}

}
