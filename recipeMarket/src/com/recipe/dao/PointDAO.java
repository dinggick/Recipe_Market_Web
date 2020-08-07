package com.recipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.recipe.exception.ModifyException;
import com.recipe.jdbc.MyConnection;
import com.recipe.vo.Point;

public class PointDAO {
	
	/**
	 * 좋아요 개수를 하나 늘릴 레시피 코드를 매개변수로 전달받아
	 * DB의 POINT 테이블을 수정
	 * @param 좋아요 개수를 수정할 레시피 코드
	 * @throws ModifyException
	 * @author 최종국
	 */
	public void updateLike(int recipeCode) throws ModifyException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		}
		
		String updatePointSQL = "UPDATE point SET like_count = like_count+1 WHERE recipe_code = ?";
		try {
			pstmt = con.prepareStatement(updatePointSQL);
			pstmt.setInt(1, recipeCode);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 싫어요 개수를 하나 늘릴 레시피 코드를 매개변수로 전달받아
	 * DB의 POINT 테이블을 수정
	 * @param 싫어요 개수를 수정할 레시피 코드
	 * @throws ModifyException
	 * @author 최종국
	 */
	public void updateDisLike(int recipeCode) throws ModifyException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		}
		
		String updatePointSQL = "UPDATE point SET dislike_count = dislike_count+1 WHERE recipe_code = ?";
		try {
			pstmt = con.prepareStatement(updatePointSQL);
			pstmt.setInt(1, recipeCode);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
