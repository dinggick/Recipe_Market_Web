package com.recipe.service;

import java.util.List;

import com.recipe.dao.PurchaseDAO;
import com.recipe.dao.ReviewDAO;
import com.recipe.exception.AddException;
import com.recipe.exception.FindException;
import com.recipe.vo.Purchase;

public class PurchaseService {
	private static PurchaseService instance;
	private PurchaseDAO dao;
	
	private PurchaseService() {
		dao = new PurchaseDAO();
	}
	
	public static PurchaseService getInstance() {
		if(instance == null) instance = new PurchaseService();
		return instance;
	}

	public void buy(Purchase p)  throws AddException{
		dao.insert(p);
	}
	
	
	public List<Purchase> findById(String customerId) throws FindException{
		return dao.selectById(customerId);
	}

}
