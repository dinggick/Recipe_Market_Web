package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.service.AccountService;
import com.recipe.vo.Customer;

public class LoginController implements Controller {
	private static LoginController instance;
	private AccountService accountService;
	
	private LoginController() {
		accountService = AccountService.getInstance();
	}
	
	public static LoginController getInstance() {
		if(instance == null) instance = new LoginController();
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		try {
			String customerName = accountService.login(id, pwd);
			request.getSession().setAttribute("loginInfo", id);
			request.getSession().setAttribute("userName", customerName);
			return "/index.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";
		}
	}

}
