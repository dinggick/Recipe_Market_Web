package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {
	private static LogoutController instance;
	
	private LogoutController() {}
	
	public static LogoutController getInstance() {
		if(instance == null) instance = new LogoutController();
		return instance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(!"".equals(session.getAttribute("loginInfo"))) {
			session.removeAttribute("loginInfo");
			return "/index.jsp";
		}
		request.setAttribute("msg","로그아웃 실패");
		return "/fail.jsp";
	}
	
}
