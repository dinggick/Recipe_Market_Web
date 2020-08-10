package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.service.RecipeService;
import com.recipe.vo.RecipeInfo;

public class RecipeModifyController implements Controller {
	private static RecipeModifyController instance;
	private RecipeService service;
	private static final long serialVersionUID = 1L;
	
	private RecipeModifyController() {
		service = RecipeService.getInstance();
	}

	public static RecipeModifyController getInstance() {
		if (instance == null) instance = new RecipeModifyController();
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String servletPath = "";
		//HttpSession session = request.getSession();
		System.out.println("레시피 수정뷰");
		String recipeCode = request.getParameter("recipeCode");
		System.out.println("--------- recipeCode : " + recipeCode);
		
		try {
			if (!isNullOrEmpty(recipeCode)) {
				System.out.println("수정");
				//service.addRecipe(rdId, recipeInfo, ingInfo, ingList, process);
				RecipeInfo recipeInfo = service.findByCode(Integer.parseInt(recipeCode));
				System.out.println("--------- recipeName : " + recipeInfo.getRecipeName());
				request.setAttribute("recipeInfo", recipeInfo);
			}
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
		}

		servletPath = "/recipeModify.jsp";
		return servletPath;
	}

	/**
	 * String 문자열의 값이 null 이나 empty 이면 true 반환
	 * 
	 * @param value
	 * @return
	 */
	private Boolean isNullOrEmpty(String value) {
		if (value != null && value.length() > 0) {
			return false;
		}
		return true;
	}
}
