package com.recipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.recipe.exception.AddException;
import com.recipe.exception.FindException;
import com.recipe.exception.ModifyException;
import com.recipe.exception.RemoveException;
import com.recipe.jdbc.MyConnection;
import com.recipe.vo.Cart;
import com.recipe.vo.Purchase;
import com.recipe.vo.RecipeInfo;

public class CartDAO {
	
	//장바구니 추가
	public void insert(Cart c) throws AddException{
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		String addCart = "insert into cart values(?,?,?)";
		
		try {
			ps = con.prepareStatement(addCart);
			
			ps.setString(1, c.getCustomerEmail());
			ps.setInt(2, c.getRecipeInfo().getRecipeCode());
			ps.setInt(3, c.getCartQuantity());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException("장바구니에 추가되지않았습니다");
		}finally {
			MyConnection.close(ps, con);
		}	
	}
	
	//내카트 조회
	public List<Cart> selectById(String customerEmail) throws FindException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Cart> list = new ArrayList<>();
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		String selectAllSQL = "select ri.recipe_code, IMG_URL, recipe_name, recipe_summ, recipe_price, cart_quantity \r\n" + 
				"from cart c join recipe_info ri on(c.recipe_code = ri.recipe_code)\r\n" + 
				"where c.customer_email=?";
		
		try {
			ps = con.prepareStatement(selectAllSQL);
			
			ps.setString(1, customerEmail);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				
				RecipeInfo info = new RecipeInfo();
				Cart c = new Cart();
				
				info.setRecipeCode(rs.getInt("recipe_code"));
				info.setImgUrl(rs.getString("IMG_URL"));
				info.setRecipeName(rs.getString("recipe_name"));
				info.setRecipeSumm(rs.getString("recipe_summ"));
				info.setRecipePrice(rs.getInt("recipe_price"));
				c.setCartQuantity(rs.getInt("cart_quantity"));
				c.setRecipeInfo(info);
				list.add(c);
			}
			if(list.size()==0) {
				throw new FindException("장바구니내역이 없습니다");
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, ps, con);
		}
		
	}
	
	//카트 수량 추가
	public void update(Cart c) throws ModifyException{
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		String updateSQL = "update cart set cart_quantity=? where recipe_code=? and customer_email=?";
		
		try {
			ps = con.prepareStatement(updateSQL);
			
			System.out.println("카트" + c);
			ps.setInt(1, c.getCartQuantity());
			ps.setInt(2, c.getRecipeInfo().getRecipeCode());
			ps.setString(3, c.getCustomerEmail());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(ps, con);
		}
		
	}
	
	//장바구니 삭제
	public void delectByCode(Cart c)throws RemoveException{
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		String deleteSQL = "delete cart where recipe_code=?";
		
		try {
			ps = con.prepareStatement(deleteSQL);
			
			ps.setInt(1, c.getRecipeInfo().getRecipeCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RemoveException("삭제되지않았습니다");
		} finally {
			MyConnection.close(ps,con);
		}
	}
	
}
