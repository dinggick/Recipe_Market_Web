package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.AddException;
import com.recipe.exception.FindException;
import com.recipe.model.PageBean;
import com.recipe.service.BoardService;
import com.recipe.vo.Board;

/**
 * 
 * @author yonghwan
 *
 */
public class BoardController implements Controller {
	private static BoardController ctr = new BoardController();
	private BoardService service;
	
	private BoardController() {
		service = BoardService.getInstance();
	}
	
	/**
	 * 
	 * @return BoardController
	 */
	public static BoardController getInstance() {
		return ctr;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getServletPath().substring(request.getServletPath().lastIndexOf("/"));
		String jspFileName = "/fail.jsp";
		
		if("/write".equals(pathInfo)) {
			Board board = new Board(request.getParameter("board_writer"), 
					request.getParameter("board_title"), 
					request.getParameter("board_content"));
			
			try {
				service.write(board);
				
				jspFileName = "/success.jsp";
				
			} catch (AddException e) {
				e.printStackTrace();
			}
		} else if("/detail".equals(pathInfo)) {
			String strBoard_no = request.getParameter("board_no");
			int board_no = Integer.parseInt(strBoard_no);
			
			try {
				List<Board> boards = service.findByNo(board_no);
				request.setAttribute("detail", boards);
				
				jspFileName = "/boardDetail.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if("/reply".equals(pathInfo)) {
			Board board = new Board(Integer.parseInt(request.getParameter("parent_no")), 
					request.getParameter("board_title"), 
					request.getParameter("board_writer"), 
					request.getParameter("board_content"));
			try {
				service.reply(board);
				
				jspFileName = "/success.jsp";
				
			} catch (AddException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if("/list".equals(pathInfo)) {
			try {
				String strPage = request.getParameter("currentPage");
				
				int currentPage = 1;
				
				if(!"".equals(strPage))
					currentPage = Integer.parseInt(strPage);
				
				PageBean pb = service.findAll(currentPage);
				
				String url = request.getServletPath() + request.getPathInfo();
				pb.setUrl(url);
								
				request.setAttribute("pb", pb);
				
				jspFileName = "/boardList.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		}
		
		return jspFileName;
	}

}
