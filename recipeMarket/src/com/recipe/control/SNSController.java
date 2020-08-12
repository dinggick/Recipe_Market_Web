package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.service.AccountService;
import com.recipe.vo.Customer;

public class SNSController implements Controller {
	private static SNSController instance;
	private AccountService service;
	
	private SNSController() {
		service = AccountService.getInstance();
	}
	
	public static SNSController getInstance() {
		if(instance == null) instance = new SNSController();
		return instance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String snsEmail = request.getParameter("snsEmail");
		
		try {
			Customer c = service.findByEmail(snsEmail);
			request.getSession().setAttribute("loginInfo", snsEmail);
			request.getSession().setAttribute("userName", c.getCustomerName());
			request.getSession().setAttribute("userType", "C");
			return "/success.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";
		}
	}

}
