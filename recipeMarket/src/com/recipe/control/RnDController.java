/**
 * RnD-related controller
 * @author yonghwan
 */
package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.AddException;
import com.recipe.exception.FindException;
import com.recipe.exception.ModifyException;
import com.recipe.exception.RemoveException;
import com.recipe.service.RnDService;
import com.recipe.vo.RnD;

class RnDMethod {
	static RnD getNewInstance(HttpServletRequest request) {
		return new RnD(request.getParameter("rd_email"),
				request.getParameter("rd_pwd"), 
				request.getParameter("rd_managerName"), 
				request.getParameter("rd_teamName"), 
				request.getParameter("rd_phone"));
	}
}

public class RnDController implements Controller {
	private static RnDController ctr = new RnDController();
	private RnDService service;
	
	private RnDController() {
		service = RnDService.getInstance();
	}
	
	/**
	 * 
	 * @return RnDController
	 */
	public static RnDController getInstance() {
		return ctr;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pathInfo = request.getServletPath().substring(request.getServletPath().lastIndexOf("/"));

		if("/add".equals(pathInfo)) { /* Add RnD's account */
			try {
				service.add(RnDMethod.getNewInstance(request));
				return "/success.jsp";
			} catch (AddException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if("/modify".equals(pathInfo)) { /* Modify RnD's account */				
			try {
				service.modify(RnDMethod.getNewInstance(request));
				return "/success.jsp";
			} catch (ModifyException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if ("/remove".equals(pathInfo)) { /* Remove RnD's account */
			try {
				service.remove(request.getParameter("rd_email"));
				return "/success.jsp";
			} catch (RemoveException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if ("/info".equals(pathInfo)) { /* Show RnD's account */
			try {
				service.findById(request.getParameter("rd_email"));
				return "/success.jsp";
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if ("/list".equals(pathInfo)) { /* Show RnD list */
			try {
				return "/success.jsp";
			} catch (AddException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		}	
		return "/fail.jsp";
	}
}
