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

public class SearchController implements Controller {
	private static SearchController instance;
	private RecipeService service;
	private FavoriteService favoriteService;
	
	private SearchController() {
		service = RecipeService.getInstance();
		favoriteService = FavoriteService.getInstance();
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

		try {
			int d = Integer.parseInt(value);
			try {
				RecipeInfo recipe = service.findByCode(d);
				System.out.println(recipe);
				request.setAttribute("recipeInfo", recipe);
				String customerEmail = (String) request.getSession().getAttribute("loginInfo");
				if(customerEmail != null) {
					List<Favorite> favoriteListByEmail = favoriteService.findById(customerEmail);
					int i = 0;
					for(i = 0; i < favoriteListByEmail.size(); i++) {
						if(recipe.equals(favoriteListByEmail.get(i).getRecipeInfo())) {
							request.setAttribute("favoriteCheck", true);
							break;
						}
					}
					if(i == favoriteListByEmail.size()) request.setAttribute("favoriteCheck", false);
				}
				servletPath = "/recipeInfo?recipeCode=" + d;
			} catch (FindException e) {
				servletPath = "/recipeList.jsp";
			}
			
		} catch (NumberFormatException nfe) {
			request.setAttribute("ingName", value.trim());
			String[] str = value.trim().split(" ");
			for (String s : str) {
				ingName.add(s);				
			}				
			try {
				List<RecipeInfo> infos = service.findRecipe(ingName);
				request.setAttribute("recipeList", infos);
				String customerEmail = (String) request.getSession().getAttribute("loginInfo");
				if(customerEmail != null) {
					List<Favorite> favoriteListByEmail = favoriteService.findById(customerEmail);
					List<Boolean> favoriteCheckList = new ArrayList<Boolean>();
					System.out.println("checklist size : " + favoriteCheckList.size());
					
					for(int i = 0; i < infos.size(); i++) {
						int j = 0;
						for(j = 0; j < favoriteListByEmail.size(); j++) {
							if(infos.get(i).equals(favoriteListByEmail.get(j).getRecipeInfo())) {
								favoriteCheckList.add(true);
								break;
							}
						}
						if(j == favoriteListByEmail.size()) favoriteCheckList.add(false);
					}
					request.setAttribute("favoriteCheckList", favoriteCheckList);

					System.out.println("info size : " + infos.size());
					System.out.println("checklist size : " + favoriteCheckList.size());
				}
				servletPath = "/recipeList.jsp";
			} catch (FindException e) {
				servletPath = "/recipeList.jsp";				
			}
			
		}	
		return servletPath;
	}

}
