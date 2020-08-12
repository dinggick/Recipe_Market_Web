package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.service.AccountService;
import com.recipe.service.AdminAccountService;
import com.recipe.service.RnDService;
import com.recipe.vo.Customer;

public class LoginController implements Controller {
	private static LoginController instance;
	private AccountService accountService;
	private RnDService rndService;
	private AdminAccountService adminAccountService;
	
	private LoginController() {
		accountService = AccountService.getInstance();
		rndService = RnDService.getInstance();
		adminAccountService = AdminAccountService.getInstance();
	}
	
	public static LoginController getInstance() {
		if(instance == null) instance = new LoginController();
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURL = request.getServletPath();
		System.out.println(requestURL);
		
		if("/login/customer".equals(requestURL)) {
			return customerLogin(request, response);
		} else if("/login/rnd".equals(requestURL)) {
			return rndLogin(request, response);
		} else if("/login/admin".equals(requestURL)) {
			return adminLogin(request, response);
		}
		
		return "/fail.jsp";
	}
	
	private String customerLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		try {
			String customerName = accountService.login(id, pwd);
			request.getSession().setAttribute("loginInfo", id);
			request.getSession().setAttribute("userName", customerName);
			request.getSession().setAttribute("userType", "C");
			return "/index.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";	
		}
	}

	private String rndLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		try {
			String rndName = rndService.login(id, pwd);
			request.getSession().setAttribute("loginInfo", id);
			request.getSession().setAttribute("userName", rndName);
			request.getSession().setAttribute("userType", "R");
			return "/index_rnd.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";
		}
	}
	
	private String adminLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		try {
			adminAccountService.login(id, pwd);
			request.getSession().setAttribute("loginInfo", id);
			request.getSession().setAttribute("userName", "관리자");
			request.getSession().setAttribute("userType", "A");
			return "/index_admin.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";
		}
	}
}
