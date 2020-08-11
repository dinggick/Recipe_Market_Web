package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.service.RecipeService;

public class RecipeAddController implements Controller {
	private static RecipeAddController instance;
	private RecipeService service;
	private static final long serialVersionUID = 1L;
	
	private RecipeAddController() {
		service = RecipeService.getInstance();
	}

	public static RecipeAddController getInstance() {
		if(instance == null) instance = new RecipeAddController();
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String servletPath = "";
		//HttpSession session = request.getSession();
		System.out.println("레시피 등록뷰");

		servletPath = "/recipeAdd.jsp";

		return servletPath;
	}

}
