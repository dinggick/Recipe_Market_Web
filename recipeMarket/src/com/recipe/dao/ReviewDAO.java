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

		String selectSQL = "SELECT\r\n" + 
				"    r.review_date,\r\n" + 
				"    r.review_comment,\r\n" + 
				"    pd.recipe_code,\r\n" + 
				"    c.customer_name\r\n" + 
				"FROM\r\n" + 
				"    review r\r\n" + 
				"    JOIN purchase p ON ( p.purchase_code = r.purchase_code )\r\n" + 
				"    JOIN purchase_detail pd ON ( r.recipe_code = pd.recipe_code\r\n" + 
				"                                 AND p.purchase_code = pd.purchase_code )\r\n" + 
				"    JOIN customer c ON ( c.customer_email = p.customer_email )\r\n" + 
				"WHERE\r\n" + 
				"    pd.recipe_code = ?\r\n" + 
				"ORDER BY REVIEW_DATE DESC";
		
		try {
			con = MyConnection.getConnection();		
		} catch (ClassNotFoundException | SQLException e ) {
			e.printStackTrace();
		}
		
		try {
			pstmt = con.prepareStatement(selectSQL);
			System.out.println(recipeCode);
			pstmt.setInt(1, recipeCode);
			rs = pstmt.executeQuery();
			reviewList = new ArrayList<>();
			
			while ( rs.next() ) {
				// 리뷰등록날짜, 리뷰내용, 레시피코드, 리뷰등록한 사용자
				
				// PurchaseDetail 
				Customer customer = new Customer();
				customer.setCustomerName(rs.getString("CUSTOMER_NAME"));

				Purchase purchase = new Purchase();
				purchase.setCustomerEmail(customer);
				
				List<PurchaseDetail> list = new ArrayList<PurchaseDetail>();
				RecipeInfo recipeInfo = new RecipeInfo();
				recipeInfo.setRecipeCode(rs.getInt("RECIPE_CODE"));
				
				PurchaseDetail purchaseDetail = new PurchaseDetail();
				purchaseDetail.setRecipeInfo(recipeInfo);
				list.add(purchaseDetail);
				
				Review r = new Review();
				r.setRecipeInfo(recipeInfo);
				r.setReviewDate(rs.getDate("REVIEW_DATE"));
				r.setReviewComment(rs.getString("REVIEW_COMMENT"));
				r.setPurchase(purchase);
				
				reviewList.add(r);
			} //end while
			
		} catch (SQLException e) {
			e.printStackTrace();
		
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
		
		String insertSQL = "INSERT INTO REVIEW (purchase_code, recipe_code, review_comment, review_date) VALUES ( ? , ?,  ? , SYSDATE)"; 

		try {
			con = MyConnection.getConnection();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.getStackTrace();
		}
		
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, r.getPurchase().getPurchaseCode());
			//System.out.println(r.getRecipeInfo().getRecipeCode());
			pstmt.setInt(2, r.getRecipeInfo().getRecipeCode());
			pstmt.setString(3, r.getReviewComment());
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
	public void deleteByPurchaseCode(int purchaseCode, int recipeCode) throws RemoveException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String deleteSQL = "DELETE FROM REVIEW WHERE purchase_code = ? AND recipe_Code = ?"; 

		try {
			con = MyConnection.getConnection();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.getStackTrace();
		}
		
		try {
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, purchaseCode);
			pstmt.setInt(2, recipeCode);
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
	public int selectByEmail(String customerEmail) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int reviewCNT = 0;
		
		try {
			con = MyConnection.getConnection();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.getStackTrace();
		}
		
		String selectSQL = "SELECT COUNT(*) reviewCNT " + 
				"FROM review R " + 
				"   JOIN purchase P ON (R.purchase_code = P.purchase_code) " + 
				"   JOIN customer C ON (C.customer_email = P.customer_email) " + 
				"   JOIN recipe_info RI ON (R.recipe_code = RI.recipe_code) " + 
				"WHERE " + 
				"   C.customer_email = ? and RI.recipe_status = '1'";
			
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, customerEmail);
			rs = pstmt.executeQuery();
			while ( rs.next() ) {
				reviewCNT = rs.getInt("reviewCNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException ("Fail : 후기 목록 조회에 실패하였습니다.");
			
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return reviewCNT;
	} // end method selectByEmail();
	
	
	/**
	 * customerEmail
	 * @return review 목록 반환
	 * @param customerEmail
	 * @throws FindException
	 * @author Soojeong
	 */
	public List<Review> selectByEmail(int startRow , int endRow, String customerEmail) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Review> reviewList = null;
		
		try {
			con = MyConnection.getConnection();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.getStackTrace();
		}
		
		String selectSQL = " SELECT * FROM  " + 
				"				   (SELECT ROWNUM r, a.* " + 
				"				      FROM ( SELECT R.purchase_code " + 
				"				    , R.review_comment " + 
				"				    , P.purchase_date " + 
				"				    , R.recipe_code  " + 
				"				    , RI.recipe_name " + 
				"				         FROM review R " + 
				"				         JOIN purchase P ON (R.purchase_code = P.purchase_code) " + 
				"				         JOIN customer C ON (C.customer_email = P.customer_email) " + 
				"				         JOIN recipe_info RI ON ( R.recipe_code = RI.recipe_code) " + 
				"				       WHERE C.customer_email = ? and RI.recipe_status = '1' ) a " + 
				"			) WHERE  r BETWEEN ? AND ? ";
		
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, customerEmail);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			reviewList = new ArrayList<>();
			
			while ( rs.next() ) {
				RecipeInfo recipeInfo = new RecipeInfo();
				recipeInfo.setRecipeCode(rs.getInt("recipe_code"));
				recipeInfo.setRecipeName(rs.getString("recipe_name"));
				
				Purchase purchase = new Purchase();
				purchase.setPurchaseCode(rs.getInt("purchase_code"));
				purchase.setPurchaseDate(rs.getDate("purchase_date"));
				
				Review r = new Review();
				r.setRecipeInfo(recipeInfo);
				r.setPurchase(purchase);
				r.setReviewComment(rs.getString("review_comment"));
				
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
