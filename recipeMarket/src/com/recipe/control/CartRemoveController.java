package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.RemoveException;
import com.recipe.service.CartService;
import com.recipe.vo.Cart;
import com.recipe.vo.RecipeInfo;

public class CartRemoveController implements Controller {
	private static CartRemoveController instance;
	private CartService service;
	private static final long serialVersionUID = 1L;
	
	private CartRemoveController() {
		service = CartService.getInstance();
	}
	
	public static CartRemoveController getInstance() {
    	if(instance == null) instance = new CartRemoveController();
    	return instance;
    }
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int code = Integer.parseInt(request.getParameter("recipeCode"));
		String servletPath = "";
		Cart c = new Cart();
		RecipeInfo info = new RecipeInfo();
		
		try {
			info.setRecipeCode(code);
			c.setRecipeInfo(info);
			
			service.remove(c);
			servletPath="/success.jsp";
			return servletPath;
		} catch (RemoveException e) {
			e.printStackTrace();
			return "/fail.jsp";
		}
	}

}
