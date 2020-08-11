package com.recipe.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.service.FavoriteService;
import com.recipe.service.RecipeService;
import com.recipe.vo.Favorite;
import com.recipe.vo.RecipeInfo;

public class RecommendRecipeController implements Controller {
	private static RecommendRecipeController instance;
	private RecipeService recipeService;
	private FavoriteService favoriteService;
	
	private RecommendRecipeController() {
		recipeService = RecipeService.getInstance();
		favoriteService = FavoriteService.getInstance();
	}
	
	public static RecommendRecipeController getInstance() {
		if(instance == null) instance = new RecommendRecipeController();
		return instance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customerEmail = (String) request.getSession().getAttribute("loginInfo");
		
		try {
			List<RecipeInfo> recommendRecipeList = recipeService.findRecommended();
			request.setAttribute("recommendRecipeList", recommendRecipeList);
			
			if(customerEmail != null) {
				List<Favorite> favoriteListByEmail = favoriteService.findById(customerEmail);
				List<Boolean> favoriteCheckList = new ArrayList<Boolean>();
				
				for(int i = 0; i < recommendRecipeList.size(); i++) {
					int j = 0;
					for(j = 0; j < favoriteListByEmail.size(); j++) {
						if(favoriteListByEmail.get(j).getRecipeInfo().equals(recommendRecipeList.get(i))) {
							favoriteCheckList.add(true);
							break;
						}
						
					}
					if(j == favoriteListByEmail.size()) favoriteCheckList.add(false);
				}
				
				System.out.println(recommendRecipeList.size());
				System.out.println(favoriteCheckList.size());
				request.setAttribute("favoriteCheckList", favoriteCheckList);
			}
			
			return "/recommendRecipeList.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";
		}
	}

}
