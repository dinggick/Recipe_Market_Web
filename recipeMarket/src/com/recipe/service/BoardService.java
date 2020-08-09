/**
 * @author yonghwan
 */
package com.recipe.service;

import java.util.List;

import com.recipe.dao.BoardDAO;
import com.recipe.exception.AddException;
import com.recipe.exception.FindException;
import com.recipe.model.PageBean;
import com.recipe.vo.Board;

public class BoardService {
	private static BoardService service = new BoardService();
	
	private BoardDAO dao;

	private BoardService() {
		dao = new BoardDAO();
	}
	
	/**
	 * For single tone pattern
	 * @return BoardService
	 */
	public static BoardService getInstance() {
		return service;
	}

	/**
	 * 
	 * @param board
	 * @throws AddException
	 */
	public void write(Board board) throws AddException {
		if (board.getParentNo() != 0)
			throw new AddException("No parent_no required");
		
		dao.insert(board);
	}

	/**
	 * 
	 * @param board
	 * @throws AddException
	 */
	public void reply(Board board) throws AddException {
		if (board.getParentNo() == 0)
			throw new AddException("No parent_no");
		
		dao.insert(board);
	}

	/**
	 * 
	 * @param page
	 * @return
	 * @throws FindException
	 */
	public PageBean findAll(int page) throws FindException {
		if (page < 1)
			throw new FindException("The page does not exist");

		int rowCnt = dao.selectCount();
		PageBean pb = new PageBean(page, rowCnt);
		
		List<Board> list = dao.selectAll(pb.getStartRow(), pb.getEndRow());
		pb.setList(list);
		
		return pb;
	}

	public List<Board> findByNo(int board_no) throws FindException {
		return dao.selectByNo(board_no);
	}
}
