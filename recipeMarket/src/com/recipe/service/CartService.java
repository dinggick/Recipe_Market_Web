package com.recipe.service;

import java.util.List;

import com.recipe.dao.CartDAO;
import com.recipe.exception.AddException;
import com.recipe.exception.FindException;
import com.recipe.exception.RemoveException;
import com.recipe.vo.Cart;

public class CartService {
	private static CartService instance;
	private CartDAO dao;
	
	private CartService() {
		dao = new CartDAO();
	}
	
	public static CartService getInstance() {
		if(instance == null) instance = new CartService();
		return instance;
	}
	
	public void add(Cart c) throws AddException{
		dao.insert(c);
	}
	
	public List<Cart> findById(String customerEmail) throws FindException{
		return dao.selectById(customerEmail);
	}
	
	public void remove(Cart c) throws RemoveException{
		dao.delectByCode(c);
	}
}
