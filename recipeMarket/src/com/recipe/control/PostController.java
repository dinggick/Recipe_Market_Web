package com.recipe.control;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.service.PostService;
import com.recipe.vo.Postal;

public class PostController implements Controller {
	private static PostController instance;
	private static final long serialVersionUID = 1L;
	private PostService service;

	private PostController() {
		service = PostService.getInstance();
	}

	public static PostController getInstance() {
		if (instance == null)
			instance = new PostController();
		return instance;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String doro = request.getParameter("searchKeyword");
			List<Postal> result = service.findByDoro(doro);
			request.setAttribute("postals", result);
			return "/postal.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			return "/fail.jsp";
		}
	}
}