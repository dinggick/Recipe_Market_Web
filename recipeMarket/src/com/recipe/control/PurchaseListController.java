package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.service.PurchaseService;
import com.recipe.vo.Purchase;

public class PurchaseListController implements Controller{
	private static PurchaseListController instance;
	private PurchaseService service;
	private static final long serialVersionUID = 1L;
	
	private PurchaseListController() {
		service = new PurchaseService();
	}
	
	public static PurchaseListController getInstance() {
    	if(instance == null) instance = new PurchaseListController();
    	return instance;
    }
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("customer_email");
		String servletPath = "";
		
		try {
			List<Purchase> list = service.findById(id);
			
			request.setAttribute("list", list);
			
			servletPath = "/purchaseList.jsp";
			return servletPath;
		} catch (FindException e) {
			e.printStackTrace();
			return "/fail.jsp";
		}
		
		
	}
}
