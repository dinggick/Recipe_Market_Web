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
import com.recipe.vo.Point;
import com.recipe.vo.RecipeInfo;
import com.recipe.vo.Review;
/**
 * @author Soojeong
 *
 */
public class ReviewDAO {
	
	/**
	 * recipeCode로  후기목록 조회
	 * @param recipeCode
	 * @throws FindException
	 * @author Soojeong
	 */
	public List selectByCode(int recipeCode) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Review> reviewList = null;

		String selectSQL = "SELECT R.REVIEW_DATE " + 
				"        , R.REVIEW_COMMENT " + 
				"        , PD.RECIPE_CODE " + 
				"        , C.CUSTOMER_NAME " + 
				"FROM purchase_detail PD " + 
				"JOIN review R  on PD.purchase_code = R.purchase_code " + 
				"JOIN purchase P on P.purchase_code = PD.purchase_code " + 
				"JOIN customer C on P.customer_email = C.customer_email " + 
				"WHERE recipe_code = ?  " + 
				"ORDER BY REVIEW_DATE DESC";
		
		try {
			con = MyConnection.getConnection();		
		} catch (ClassNotFoundException | SQLException e ) {
			e.printStackTrace();
		}
		
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, recipeCode);
			rs = pstmt.executeQuery();
			reviewList = new ArrayList<>();
			
			while ( rs.next() ) {
				// 리뷰등록날짜, 리뷰내용, 레시피코드, 리뷰등록한 사용자
				Review r = new Review();
				r.setReviewComment(rs.getString("R.REVIEW_COMMENT"));
				r.setReviewDate(rs.getDate("R.REVIEW_COMMENT"));
				
				
				r.setReviewComment(rs.getString("REVIEW_COMMENT"));
				r.setReviewDate(rs.getDate("REVIEW_DATE"));
				
				Point p = new Point();
				p.setRecipeCode(rs.getInt("RECIPE_CODE"));
				p.setLikeCount(rs.getInt("LIKE_COUNT"));
				p.setDisLikeCount(rs.getInt("DISLIKE_COUNT"));
				
				RecipeInfo info = new RecipeInfo();
				info.setRecipeCode(rs.getInt("RECIPE_CODE"));
				info.setPoint(p);
				
				reviewList.add(r);
			} //end while
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException ("Fail : 레시피에 등록된 후기가 없습니다.");
		
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		
		return reviewList;
	} // end method selectByCode();

	
	/**
	 * Review 객체로 리뷰 등록
	 * @param Review 객체
	 * @throws AddException, DuplicatedException
	 * @author Soojeong
	 */
	public void insert(Review r) throws AddException, DuplicatedException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String insertSQL = "INSERT INTO REVIEW (purchase_code ,review_comment, review_date) VALUES ( ? , ? , SYSDATE)"; 

		try {
			con = MyConnection.getConnection();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.getStackTrace();
		}
		
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			if ( e.getErrorCode() == 1 ) { // SQLException errorCode == 1 )  
				e.printStackTrace();
				throw new DuplicatedException("Fail : 이미 후기가 추가되어 있는 레시피 입니다.");
			} else { 
				e.printStackTrace();
				throw new AddException("Fail : 후기 등록에 실패하였습니다.");
			}
			
		} finally {
			MyConnection.close(pstmt, con);
		}
		
	} // end method insert();

	/**
	 * purchaseCode로 리뷰 삭제 ( 구매 1 : 리뷰 1 의 관계 )
	 * @param purchaseCode
	 * @throws RemoveException
	 * @author Soojeong
	 */
	public void deleteByPurchaseCode(int purchaseCode ) throws RemoveException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String deleteSQL = "DELETE FROM REVIEW WHERE purchase_code = ?"; 

		try {
			con = MyConnection.getConnection();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.getStackTrace();
		}
		
		try {
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RemoveException(e.getMessage());
			
		} finally {
			MyConnection.close(pstmt, con);
		}
		
	} // end method ();
	
	/**
	 * customerEmail
	 * @return review 목록 반환
	 * @param customerEmail
	 * @throws FindException
	 * @author Soojeong
	 */
	public List<Review> selectByEmail(String customerEmail) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Review> reviewList = null;
		
		try {
			con = MyConnection.getConnection();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.getStackTrace();
		}
		
		String selectSQL = "SELECT R.purchase_code\r\n" + 
				"         , R.review_comment\r\n" + 
				"         , P.purchase_date\r\n" + 
				"         , PD.recipe_code\r\n" + 
				"         , RI.recipe_name\r\n" + 
				"FROM review R\r\n" + 
				"   JOIN purchase P ON (R.purchase_code = P.purchase_code)\r\n" + 
				"   JOIN customer C ON (C.customer_email = P.customer_email)\r\n" + 
				"   JOIN purchase_detail PD ON (PD.purchase_code = P.purchase_code)\r\n" + 
				"   JOIN recipe_info RI ON (PD.recipe_code = RI.recipe_code)\r\n" + 
				"WHERE\r\n" + 
				"   C.customer_email = ? and RI.recipe_status = 1";
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, customerEmail);
			rs = pstmt.executeQuery();
			reviewList = new ArrayList<>();
			
			while ( rs.next() ) {
				Review r = new Review();
				//r.setCustomerId(rs.getString("CUSTOMER_ID"));
				r.setReviewComment(rs.getString("REVIEW_COMMENT"));
				r.setReviewDate(rs.getDate("REVIEW_DATE"));
				
				Point p = new Point();
				p.setRecipeCode(rs.getInt("RECIPE_CODE"));
				p.setLikeCount(rs.getInt("LIKE_COUNT"));
				p.setDisLikeCount(rs.getInt("DISLIKE_COUNT"));

				RecipeInfo info = new RecipeInfo();
				info.setPoint(p);
				info.setRecipeCode(rs.getInt("RECIPE_CODE"));
				info.setRecipeName(rs.getString("RECIPE_NAME"));
				
				reviewList.add(r);
			} //end while
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException ("Fail : 후기 목록 조회에 실패하였습니다.");
			
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return reviewList;
	} // end method selectById();
	
} //end class ReviewDAO
