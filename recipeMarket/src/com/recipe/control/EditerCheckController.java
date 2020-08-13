package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditerCheckController implements Controller {
	private static EditerCheckController instance;
	private EditerCheckController() {}

	public static EditerCheckController getInstance() {
		if(instance == null) instance = new EditerCheckController();
		return instance;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rdEmail = request.getParameter("rdEmail");
		
		if(!rdEmail.equals(request.getSession().getAttribute("loginInfo"))) {
			request.setAttribute("msg", "이 레시피의 작성자가 아닙니다");
			return "/fail.jsp";
		}
		
		return "/success.jsp";
	}

}
