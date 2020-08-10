package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.exception.FindException;
import com.recipe.service.CartService;
import com.recipe.service.PurchaseService;
import com.recipe.vo.Cart;

public class CartListController implements Controller {
	private static CartListController instance;
	private CartService service;
	private static final long serialVersionUID = 1L;
	
	private CartListController() {
		service = CartService.getInstance();
	}
	
	public static CartListController getInstance() {
    	if(instance == null) instance = new CartListController();
    	return instance;
    }
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//String customerEmail = (String)session.getAttribute("loginInfo");
		
		String customerEmail = request.getParameter("customerEmail");
		
		String servletPath = "";
		
		try {
			List<Cart> list = service.findById(customerEmail);
			for(Cart c: list) {
				System.out.println(c);
			}
			
			
			request.setAttribute("list", list);
			servletPath = "/recipeCart.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			return "/fail.jsp";
		}
		
		return servletPath;
	}

}
