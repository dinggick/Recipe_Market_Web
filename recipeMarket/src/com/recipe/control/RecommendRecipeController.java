package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.service.RecipeService;
import com.recipe.vo.RecipeInfo;

public class RecommendRecipeController implements Controller {
	private static RecommendRecipeController instance;
	private RecipeService recipeService;
	private RecommendRecipeController() {
		recipeService = RecipeService.getInstance();
	}
	
	public static RecommendRecipeController getInstance() {
		if(instance == null) instance = new RecommendRecipeController();
		return instance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<RecipeInfo> recommendRecipeList = recipeService.findRecommended();
			request.setAttribute("recommendRecipeList", recommendRecipeList);
			
			return "/recommendRecipeList.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";
		}
	}

}
