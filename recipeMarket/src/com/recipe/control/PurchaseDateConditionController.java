package com.recipe.control;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.exception.AddException;
import com.recipe.exception.FindException;
import com.recipe.service.PurchaseService;
import com.recipe.vo.Customer;
import com.recipe.vo.Purchase;
import com.recipe.vo.PurchaseDetail;
import com.recipe.vo.RecipeInfo;

public class PurchaseDateConditionController implements Controller {
	private static PurchaseDateConditionController instance;
	private PurchaseService service;
	
	private PurchaseDateConditionController() {
		service = PurchaseService.getInstance();
	}
	
	public static PurchaseDateConditionController getInstance() {
		if(instance == null) instance = new PurchaseDateConditionController();
		return instance;
	}
		
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String customerEmail = (String)session.getAttribute("loginInfo");
		String value = request.getParameter("date");
		
		Date date = new Date();
		
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();
		//String customerEmail= request.getParameter("customerEmail");
		
		String servletPath = "";
		
		try {
			List<Purchase> list = service.findBydate(customerEmail, date);
			
			request.setAttribute("list", list);
			
			servletPath = "/purchaseList.jsp";
			return servletPath;
		} catch (FindException e) {
			e.printStackTrace();
			return "/fail.jsp";
		}
		
		
	}

}
