package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.mail.PwdMail;
import com.recipe.service.AccountService;
import com.recipe.vo.Customer;

public class FindPwdController implements Controller {
	private static FindPwdController instance;	
	private AccountService service;
	private FindPwdController() {
		service = AccountService.getInstance();
	}	
	public static FindPwdController getInstance() {
    	if(instance == null) instance = new FindPwdController();
    	return instance;
    }
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String servletPath = "";
		String value = request.getParameter("email");
		
		
		try {
			Customer c = service.findByEmail(value);
			Thread thread = new Thread(new PwdMail(value,c.getCustomerPwd()));
			thread.start();
			servletPath = "/success.jsp";
		} catch (FindException e) {
			servletPath = "/fail.jsp";
			e.printStackTrace();
		}	
		return servletPath;
		
					
	
	}

}
