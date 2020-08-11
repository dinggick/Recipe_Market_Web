package com.recipe.service;

import java.util.ArrayList;
import java.util.List;

import com.recipe.dao.FavoriteDAO;
import com.recipe.exception.AddException;
import com.recipe.exception.DuplicatedException;
import com.recipe.exception.FindException;
import com.recipe.exception.RemoveException;
import com.recipe.model.PageBean;
import com.recipe.vo.Favorite;

/**
 * @author Soojeong
 *
 */
public class FavoriteService {
	private static FavoriteService instance;
	private FavoriteDAO dao;
	
	private FavoriteService() {
		dao = new FavoriteDAO();
	}
	
	public static FavoriteService getInstance() {
		if(instance == null) instance = new FavoriteService();
		return instance;
	}
	
	public void add(Favorite f)	throws AddException, DuplicatedException {
		dao.insert(f);
	}
	
	public List<Favorite> findById(String customerId) throws FindException {
		List<Favorite> favoriteList = new ArrayList<>();
		favoriteList = dao.selectById(customerId);
		return favoriteList;
	}
	
	public void remove(Favorite f) throws RemoveException {
		dao.deleteByIdnCode(f);
	}
	
	
	
	/**
	 * 페이징 처리
	 * @return PageBean
	 * @param customerEmail, customerEmail
	 * @throws FindException
	 * @author Soojeong
	 */
	public PageBean findById(int page, String customerEmail) throws FindException {
		if (page < 1)
			throw new FindException("페이지가 존재하지 않습니다.");
		List<Favorite> favoriteList = dao.selectById(customerEmail);
		int rowCnt = favoriteList.size();
		PageBean pb = new PageBean(page, rowCnt);
		pb.setRowCnt(rowCnt);
		
		List<Favorite> fList = dao.selectById(pb.getStartRow(), pb.getEndRow(), customerEmail);
		pb.setList(fList);
		
		return pb;
	}
	
} // end class FavoriteService
