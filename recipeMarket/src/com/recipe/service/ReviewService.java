package com.recipe.service;

import java.util.List;

import com.recipe.dao.ReviewDAO;
import com.recipe.exception.AddException;
import com.recipe.exception.DuplicatedException;
import com.recipe.exception.FindException;
import com.recipe.exception.RemoveException;
import com.recipe.model.PageBean;
import com.recipe.vo.Review;

/**
 * @author Soojeong
 * 후기 등록, recipeCode로  후기목록 조회, customer_email 계정으로 등록한 후기 목록보기, 후기삭제
 */
public class ReviewService {
	private static ReviewService instance;
	private ReviewDAO dao;
	
	private ReviewService() {
		dao = new ReviewDAO();
	}
	
	public static ReviewService getInstance() {
		if(instance == null) instance = new ReviewService();
		return instance;
	}
	
	/**
	 * recipeCode로  후기목록 조회
	 * @param recipeCode
	 * @throws FindException
	 * @author Soojeong
	 */
	public List<Review> findByCode(int recipeCode) throws FindException {
		return dao.selectByCode(recipeCode);
	}
	
	/**
	 * Review 객체로 리뷰 등록
	 * @param Review 객체
	 * @throws AddException, DuplicatedException
	 * @author Soojeong
	 */
	public void add(Review r ) throws AddException, DuplicatedException {
		dao.insert(r);
	}
	
	/**
	 * purchaseCode로 리뷰 삭제 ( 구매 1 : 리뷰 1 의 관계 )
	 * @param purchaseCode
	 * @throws RemoveException
	 * @author Soojeong
	 */
	public void remove(int purchaseCode, int recipeCode) throws RemoveException {
		dao.deleteByPurchaseCode(purchaseCode , recipeCode);
	}
	
	/**
	 * 페이징 처리
	 * @return PageBean
	 * @param customerEmail, customerEmail
	 * @throws FindException
	 * @author Soojeong
	 */
	public PageBean findByEmailAll(int page, String customerEmail) throws FindException {
		if (page < 1)
			throw new FindException("페이지가 존재하지 않습니다.");
		int reviewCNT = dao.selectByEmail(customerEmail);
		PageBean pb = new PageBean(page, reviewCNT);
		pb.setRowCnt(reviewCNT);
		List<Review> rList = dao.selectByEmail(pb.getStartRow(), pb.getEndRow(), customerEmail);
		pb.setList(rList);
		
		return pb;
	}

} // end class ReviewService
