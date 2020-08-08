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

		if("/write".equals(pathInfo)) {
			String board_writer = request.getParameter("board_writer");
			String board_title = request.getParameter("board_title");
			String board_content = request.getParameter("board_content");

			Board board = new Board(board_title, board_writer, board_content);
			
			try {
				service.write(board);
				
				return "/success.jsp";
				
			} catch (AddException e) {
				e.printStackTrace();
			}
		} else if("/detail".equals(pathInfo)) {
			String strBoard_no = request.getParameter("board_no");
			int board_no = Integer.parseInt(strBoard_no);
			try {
				Board board = service.findByNo(board_no);
				request.setAttribute("detail", board);
				return "/boardDetail.jsp";
			} catch (FindException e) {
				String errorMsg = e.getMessage().replace("\n", ",");
				request.setAttribute("errorMsg", errorMsg);
				e.printStackTrace();
				return "/fail.jsp";
			}
		} else if("/reply".equals(pathInfo)) {
			int parent_no = Integer.parseInt(request.getParameter("parent_no")); 
			String board_title = request.getParameter("board_title");
			String board_writer = request.getParameter("board_writer");
			String board_content = request.getParameter("board_content");
			Board board = new Board(parent_no, board_title, board_writer, board_content);
			try {
				service.reply(board);
				return "/success.jsp";
				
			} catch (AddException e) {
				String errorMsg = e.getMessage().replace("\n", ",");
				request.setAttribute("errorMsg", errorMsg);
				e.printStackTrace();
				return "/fail.jsp";
			}
		} else if("/list".equals(pathInfo)) {
			List<Board> list;
			try {
				String strPage = request.getParameter("currentPage");
				int currentPage = 1;
				if(!"".equals(strPage)) {
					currentPage = Integer.parseInt(strPage);
				}
				PageBean pb = service.findAll(currentPage);
				String url = request.getServletPath() + request.getPathInfo();
				pb.setUrl(url);
				
				System.out.println(pb);
				
				request.setAttribute("pb", pb);
				
				return "/boardList.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
			}
		}
		
		return "/fail.jsp";
	}

}
