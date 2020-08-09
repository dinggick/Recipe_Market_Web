package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.ModifyException;
import com.recipe.service.CartService;
import com.recipe.vo.Cart;
import com.recipe.vo.RecipeInfo;

public class CartUpdateController implements Controller {
	private static CartUpdateController instance;
	private CartService service;
	private static final long serialVersionUID = 1L;
	
	private CartUpdateController() {
		service = CartService.getInstance();
	}
	
	public static CartUpdateController getInstance() {
    	if(instance == null) instance = new CartUpdateController();
    	return instance;
    }

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int code = Integer.parseInt(request.getParameter("recipeCode"));
		
		System.out.println(code);
		System.out.println(quantity);
		
		Cart c = new Cart();
		RecipeInfo info = new RecipeInfo();
		String servletPath ="";
		try {
			info.setRecipeCode(code);
			c.setCartQuantity(quantity);
			c.setRecipeInfo(info);
			
			service.update(c);
			servletPath="/success.jsp";

			return servletPath;
		} catch (ModifyException e) {
			e.printStackTrace();
			return "/fail.jsp";
		}
		
		
	}

}
