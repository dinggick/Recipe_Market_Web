package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.service.RecipeService;
import com.recipe.vo.RecipeInfo;

public class MyRecipeListController implements Controller {
	private static MyRecipeListController instance;
	private RecipeService service;
	private static final long serialVersionUID = 1L;
	
	private MyRecipeListController() {
		service = RecipeService.getInstance();
	}

	public static MyRecipeListController getInstance() {
		if (instance == null) instance = new MyRecipeListController();
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String servletPath = "";
//		HttpSession session = request.getSession();
		System.out.println("나의 레시피 뷰");
		String rdEmail = "rd04@recipe.com";//request.getParameter("rdEmail");
		System.out.println("--------- rdEmail : " + rdEmail);
		
		try {
			if (!isNullOrEmpty(rdEmail)) {
				System.out.println("나의 레시피 보기");
				//myRecipeList를 이용해서 받아온 recipeInfo값들을 jsp로 보내준다
				List<RecipeInfo> recipeInfo = service.myRecipeList(rdEmail);
				//받아온 recipeInfo값들 출력해보기
				System.out.println("--------- recipeInfo : " + recipeInfo.toString());
				request.setAttribute("recipeInfo", recipeInfo);
			}
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
		}

		servletPath = "/myRecipeList.jsp";
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
