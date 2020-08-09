package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.AddException;
import com.recipe.exception.FindException;
import com.recipe.exception.ModifyException;
import com.recipe.exception.RemoveException;
import com.recipe.model.PageBean;
import com.recipe.service.RnDService;
import com.recipe.vo.RnD;


/**
 * 
 * @author yonghwan
 *
 */
class RnDMethod {
	static RnD getNewInstance(HttpServletRequest request) {
		return new RnD(request.getParameter("rd_email"),
				request.getParameter("rd_pwd"), 
				request.getParameter("rd_manager_name"), 
				request.getParameter("rd_team_name"), 
				request.getParameter("rd_phone"));
	}
}

/**
 * RnD-related controller
 * @author yonghwan
 */
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
		
		String servletPath = request.getServletPath();
		String pathInfo = servletPath.substring(servletPath.lastIndexOf("/"));
		
		String jspFileName = "/fail.jsp";
		
		if("/add".equals(pathInfo)) { /* Add RnD's account */
			try {
				RnD rnd = RnDMethod.getNewInstance(request);
				service.add(RnDMethod.getNewInstance(request));
				
				jspFileName = "/success.jsp";
				
			} catch (AddException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if("/find".equals(pathInfo)) { /* Add RnD's account */
			try {
				service.findById(request.getParameter("rd_email"));
				
				jspFileName = "/success.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage().replace("\"", ""));
			}
		} else if("/modify".equals(pathInfo)) { /* Modify RnD's account */				
			try {
				service.modify(RnDMethod.getNewInstance(request));
				
				jspFileName = "/success.jsp";
				
			} catch (ModifyException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if ("/remove".equals(pathInfo)) { /* Remove RnD's account */
			try {
				service.remove(request.getParameter("rd_email"));
				
				jspFileName = "/success.jsp";
				
			} catch (RemoveException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if ("/info".equals(pathInfo)) { /* Show RnD's account */
			try {
				RnD rnd = service.findById(request.getParameter("rd_email"));
				request.setAttribute("rnd", rnd);
				
				jspFileName = "/RnDInfo.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if ("/list".equals(pathInfo)) { /* Show RnD list */			
			String strPage = request.getParameter("currentPage");
			
			int currentPage = 1;
			
			if(!"".equals(strPage))
				currentPage = Integer.parseInt(strPage);
			
			try {				
				PageBean pb = service.findAll(currentPage);
				pb.setUrl(servletPath);
								
				request.setAttribute("pb", pb);
				
				jspFileName = "/RnDList.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		}	
		
		return jspFileName;
	}
}
