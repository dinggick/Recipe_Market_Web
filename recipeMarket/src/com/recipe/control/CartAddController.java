package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.exception.AddException;
import com.recipe.service.CartService;
import com.recipe.vo.Cart;
import com.recipe.vo.RecipeInfo;

public class CartAddController implements Controller{
	private static CartAddController instance;
	private CartService service;
	private static final long serialVersionUID = 1L;
	
	private CartAddController() {
		service = CartService.getInstance();
	}
	
	public static CartAddController getInstance() {
    	if(instance == null) instance = new CartAddController();
    	return instance;
    }
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String customerEmail =  (String)session.getAttribute("loginInfo");
		int code = Integer.parseInt(request.getParameter("recipeCode"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Cart c = new Cart();
		RecipeInfo info = new RecipeInfo();
		String servletPath ="";
		
		try {
			info.setRecipeCode(code);			
			c.setCustomerEmail(customerEmail);
			c.setRecipeInfo(info);
			c.setCartQuantity(quantity);
			service.add(c);
			servletPath = "/success.jsp";
			
			return servletPath;
		} catch (AddException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";
		}
		
		
	}
}
