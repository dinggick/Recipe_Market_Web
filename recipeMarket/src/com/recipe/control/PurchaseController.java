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
		//String customerEmail = (String)session.getAttribute("loginInfo");
		String customerEmail = request.getParameter("customerEmail");
		String servletPath="";
		Purchase p = new Purchase();
		List<PurchaseDetail> pdList = new ArrayList<PurchaseDetail>();
		
		
		Customer c = new Customer();
		
		String code = request.getParameter("recipeCode");
		String[] codeArr = code.split(",");
		String quantity = request.getParameter("purchaseQuantity");
		String[] quantityArr = quantity.split(",");
		
		
		try {
			
			for (int i = 0; i < codeArr.length; i++) {
				RecipeInfo ri = new RecipeInfo();
				PurchaseDetail pd = new PurchaseDetail();
				ri.setRecipeCode(Integer.parseInt(codeArr[i]));
				pd.setRecipeInfo(ri);
				
				pd.setPurchaseDetailQuantity(Integer.parseInt(quantityArr[i]));
				pdList.add(pd);
				
				p.setPurchaseDetails(pdList);
				
			}
			c.setCustomerEmail(customerEmail);
			p.setCustomerEmail(c);
			
			service.buy(p);
			
			servletPath = "/success.jsp";
			return servletPath;
		} catch (AddException e) {
			e.printStackTrace();
			return "/fail.jsp";
		}
		
		
	}

}
