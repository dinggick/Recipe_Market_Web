package com.recipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.recipe.exception.AddException;
import com.recipe.exception.DuplicatedException;
import com.recipe.exception.FindException;
import com.recipe.exception.RemoveException;
import com.recipe.jdbc.MyConnection;
import com.recipe.vo.Favorite;
import com.recipe.vo.RecipeInfo;

/**
 * @author Soojeong
 */
public class FavoriteDAO {

	/**
	 * 즐겨찾기 추가 : insert()
	 * @param Favorite f
	 */
	public void insert(Favorite f) throws AddException, DuplicatedException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = MyConnection.getConnection();

		} catch (ClassNotFoundException | SQLException e) {
			e.getStackTrace();
		}

		String insertSQL = "INSERT INTO FAVORITE (customer_email , recipe_code) " 
						 + " VALUES ( ? , ? )";

		String customerEmail = f.getcustomerEmail();
		int recipeCode = f.getRecipeInfo().getRecipeCode();

		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, customerEmail);
			pstmt.setInt(2, recipeCode);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			if (e.getErrorCode() == 1) { // SQLException errorCode == 1 )
				throw new DuplicatedException("Fail : 이미 즐겨찾기 추가되어 있는 레시피 입니다.");
			} else {
				throw new AddException("Fail : 즐겨찾기 추가 실패했습니다.");
			}

		} finally {
			MyConnection.close(pstmt, con);
		}

	}// end method insert();

	/**
	 * 즐겨찾기 목록 전체보기 : selectById()
	 * @param String customerEmail
	 */
	public int selectById(String customerEmail) throws FindException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rowCNT = 0;

		String selectSQL = "SELECT COUNT(*) rowCNT " 
				+ " FROM RECIPE_INFO info "
				+ " JOIN FAVORITE f  ON info.recipe_code = f.recipe_code "
				+ " LEFT JOIN POINT p  ON info.recipe_code = p.recipe_code "
				+ " WHERE CUSTOMER_EMAIL = ? AND RECIPE_STATUS = '1' "
				+ " ORDER BY RECIPE_CODE";

		try {
			con = MyConnection.getConnection();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, customerEmail);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rowCNT = rs.getInt("rowCNT");
			} // end while

		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException (e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return rowCNT;
	} // end method selectById();

	/**
	 * 즐겨찾기 삭제 : deleteByIdnCode()
	 * @param Favorite f
	 */
	public void deleteByIdnCode(Favorite f) throws RemoveException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();

		} catch (ClassNotFoundException | SQLException e) {
			e.getStackTrace();
			throw new RemoveException (e.getMessage());
		}

		String deleteSQL = "DELETE FROM FAVORITE " + "WHERE CUSTOMER_EMAIL = ? " + "AND RECIPE_CODE = ?";

		String customerEmail = f.getcustomerEmail();
		int recipeCode = f.getRecipeInfo().getRecipeCode();

		try {
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setString(1, customerEmail);
			pstmt.setInt(2, recipeCode);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RemoveException (e.getMessage());

		} finally {
			MyConnection.close(pstmt, con);
		}

	} // end method deleteByIdnCode();

	/**
	 * 즐겨찾기 목록 전체보기 : selectById()
	 * @param String customerEmail
	 */
	public List<Favorite> selectById(int startRow, int endRow ,String customerEmail) throws FindException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Favorite> favoriteList = null;
		
		String selectSQL = "  SELECT * FROM  " + 
				"  ( SELECT ROWNUM r, a.*  " + 
				"        FROM (SELECT info.recipe_code     " + 
				"				     , recipe_name      " + 
				"				     , recipe_summ      " + 
				"				     , recipe_price     " + 
				"				     , img_url     " + 
				"				     , f.customer_email      " + 
				"				      FROM RECIPE_INFO info     " + 
				"				      JOIN FAVORITE f  ON info.recipe_code = f.recipe_code     " + 
				"				      LEFT JOIN POINT p  ON info.recipe_code = p.recipe_code     " + 
				"				      WHERE CUSTOMER_EMAIL = ? AND RECIPE_STATUS = '1'     " + 
				"				      ORDER BY RECIPE_CODE ) a   " + 
				" )  WHERE  r BETWEEN ? AND ? ";
		
		try {
			con = MyConnection.getConnection();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, customerEmail);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			favoriteList = new ArrayList<>();
			
			while (rs.next()) {
				RecipeInfo info = new RecipeInfo();
				info.setRecipeCode(rs.getInt("recipe_code"));
				info.setRecipeName(rs.getString("recipe_name"));
				info.setRecipeSumm(rs.getString("recipe_summ"));
				info.setRecipePrice(rs.getInt("recipe_price"));
				info.setImgUrl(rs.getString("img_url"));
				info.setIngredients(null);
				
				Favorite f = new Favorite();
				f.setRecipeInfo(info);
				f.setcustomerEmail(customerEmail);
				
				favoriteList.add(f);
			} // end while
			if ( favoriteList.size() == 0 ) {
				throw new FindException("등록된 즐겨찾기 목록이 없습니다.");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException (e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return favoriteList;
	} // end method selectById();
} // end class FavoriteDAO
