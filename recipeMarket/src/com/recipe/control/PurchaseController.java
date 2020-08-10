package com.recipe.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.exception.AddException;
import com.recipe.service.PurchaseService;
import com.recipe.vo.Customer;
import com.recipe.vo.Purchase;
import com.recipe.vo.PurchaseDetail;
import com.recipe.vo.RecipeInfo;

public class PurchaseController implements Controller {
	private static PurchaseController instance;
	private PurchaseService service;
	
	private PurchaseController() {
		service = PurchaseService.getInstance();
	}
	
	public static PurchaseController getInstance() {
		if(instance == null) instance = new PurchaseController();
		return instance;
	}
		
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String customerEmail = (String)session.getAttribute("loginInfo");
		String servletPath="";
		Purchase p = new Purchase();
		List<PurchaseDetail> pdList = new ArrayList<PurchaseDetail>();
		PurchaseDetail pd = new PurchaseDetail();
		RecipeInfo ri = new RecipeInfo();
		Customer c = new Customer();
		
		int code = Integer.parseInt(request.getParameter("recipeCode"));
		int quantity = Integer.parseInt(request.getParameter("purchaseQuantity"));
		
		try {
			c.setCustomerEmail(customerEmail);
			ri.setRecipeCode(code);
			
			pd.setRecipeInfo(ri);
			pd.setPurchaseDetailQuantity(quantity);
			pdList.add(pd);
			p.setCustomerEmail(c);
			p.setPurchaseDetails(pdList);
			
			service.buy(p);
			
			servletPath = "/purchaseList";
			return servletPath;
		} catch (AddException e) {
			e.printStackTrace();
			return "/fail.jsp";
		}
		
		
	}

}
