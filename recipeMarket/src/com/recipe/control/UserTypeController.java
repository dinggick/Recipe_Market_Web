package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserTypeController implements Controller {
	private static UserTypeController instance;
	
	private UserTypeController() {}
	
	public static UserTypeController getInstance() {
		if(instance == null) instance = new UserTypeController();
		return instance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userType = request.getParameter("userType");
		System.out.println(userType);
		
		if("A".equals(userType) || "R".equals(userType) || "C".equals(userType)) {
			System.out.println(userType);
			request.getSession().setAttribute("userType", userType);
			return "/success.jsp";
		}
		
		return "/fail.jsp";
	}

}
