package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.service.RecipeService;
import com.recipe.vo.RecipeInfo;

public class RecipeRemoveController implements Controller {
	private static RecipeRemoveController instance;
	private RecipeService service;
	private static final long serialVersionUID = 1L;
	
	private RecipeRemoveController() {
		service = RecipeService.getInstance();
	}

	public static RecipeRemoveController getInstance() {
		if(instance == null) instance = new RecipeRemoveController();
		return instance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String rdEmail = (String)session.getAttribute("loginInfo");
		String recipeCode = request.getParameter("recipeCode");
		try {
			if (!isNullOrEmpty(recipeCode)) {	//레시피코드값이 있다면 아래 수행
				RecipeInfo recipeInfo = new RecipeInfo();//service.findByCode(Integer.parseInt(recipeCode));
				recipeInfo.setRecipeCode(Integer.parseInt(recipeCode));
				service.removeRecipe(rdEmail, recipeInfo);
				
				return "/myRecipeList";
			} else {
				request.setAttribute("msg", "레시피 코드가 없습니다.");
				return "/fail.jsp";
			}
			
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";
		}
		
	}
	
	private Boolean isNullOrEmpty(String value) {
		if (value != null && value.length() > 0) {// value값이 있다면 false를 반환
			return false;
		}
		return true;
	}
}
