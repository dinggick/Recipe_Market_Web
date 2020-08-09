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
import com.recipe.vo.Customer;
import com.recipe.vo.Purchase;
import com.recipe.vo.PurchaseDetail;
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
	public List<Review> selectByCode(int recipeCode) throws FindException {
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
				
				// PurchaseDetail 
				Customer customer = new Customer();
				customer.setCustomerName(rs.getString("C.CUSTOMER_NAME"));

				Purchase purchase = new Purchase();
				purchase.setCustomerEmail(customer);
				
				List<PurchaseDetail> list = new ArrayList<PurchaseDetail>();
				RecipeInfo recipeInfo = new RecipeInfo();
				recipeInfo.setRecipeCode(rs.getInt("PD.RECIPE_CODE"));
				
				PurchaseDetail purchaseDetail = new PurchaseDetail();
				purchaseDetail.setRecipeInfo(recipeInfo);
				list.add(purchaseDetail);
				
				Review r = new Review();
				r.setReviewDate(rs.getDate("R.REVIEW_DATE"));
				r.setReviewComment(rs.getString("R.REVIEW_COMMENT"));
				r.setPurchase(purchase);
				
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
			pstmt.setInt(1, r.getPurchase().getPurchaseCode());
			pstmt.setString(2, r.getReviewComment());
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
			pstmt.setInt(1, purchaseCode);
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
		
		String selectSQL = "SELECT R.purchase_code " + 
				"         , R.review_comment " + 
				"         , P.purchase_date " + 
				"         , PD.recipe_code " + 
				"         , RI.recipe_name " + 
				"FROM review R " + 
				"   JOIN purchase P ON (R.purchase_code = P.purchase_code) " + 
				"   JOIN customer C ON (C.customer_email = P.customer_email) " + 
				"   JOIN purchase_detail PD ON (PD.purchase_code = P.purchase_code) " + 
				"   JOIN recipe_info RI ON (PD.recipe_code = RI.recipe_code) " + 
				"WHERE " + 
				"   C.customer_email = ? and RI.recipe_status = 1";
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, customerEmail);
			rs = pstmt.executeQuery();
			reviewList = new ArrayList<>();
			
			while ( rs.next() ) {
				
				PurchaseDetail purchaseDetail = new PurchaseDetail();
				RecipeInfo recipeInfo = new RecipeInfo();
				recipeInfo.setRecipeCode(rs.getInt("PD.recipe_code"));
				recipeInfo.setRecipeName(rs.getString("RI.recipe_name"));
				
				purchaseDetail.setRecipeInfo(recipeInfo);
				
				List<PurchaseDetail> purchaseDetails = new ArrayList<PurchaseDetail>();
				purchaseDetails.add(purchaseDetail);
				
				
				Purchase purchase = new Purchase();
				purchase.setPurchaseCode(rs.getInt("R.purchase_code"));
				purchase.setPurchaseDate(rs.getDate("P.purchase_date"));
				purchase.setPurchaseDetails(purchaseDetails);
				
				
				Review r = new Review();
				r.setPurchase(purchase);
				r.setReviewComment(rs.getString("R.review_comment"));

				reviewList.add(r);
			} //end while
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException ("Fail : 후기 목록 조회에 실패하였습니다.");
			
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return reviewList;
	} // end method selectByEmail();
	
} //end class ReviewDAO
