package com.recipe.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.recipe.exception.AddException;
import com.recipe.exception.DuplicatedException;
import com.recipe.exception.FindException;
import com.recipe.exception.ModifyException;
import com.recipe.exception.RemoveException;
import com.recipe.jdbc.MyConnection;
import com.recipe.vo.Customer;
import com.recipe.vo.Postal;

public class CustomerDAO {

	/*
	 * 회원가입
	 * 
	 * @param Customer c
	 * 
	 * @throws AddException, DuplicatedException
	 * 
	 * @author 영민
	 */

	public void insert(Customer c) throws AddException, DuplicatedException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
		String insertSQL = "INSERT INTO customer(customer_email, customer_pwd, customer_name, customer_birth_date, customer_gender, customer_phone, buildingno, customer_addr, verification, customer_status)\r\n"
				+ "VALUES (?,?,?,?,?,?,?,?,'n','1')";
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, c.getCustomerEmail());
			pstmt.setString(2, c.getCustomerPwd());
			pstmt.setString(3, c.getCustomerName());
			pstmt.setDate(4, c.getCustomerBirthDate());
			pstmt.setString(5, c.getCustomerGender());
			pstmt.setString(6, c.getCustomerPhone());
			pstmt.setString(7, c.getPostal().getBuildingno());
			pstmt.setString(8, c.getCustomerAddr());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getErrorCode() == 1) {
				throw new DuplicatedException("이미 존재하는 이메일입니다");
			} else {
				throw new AddException(e.getMessage());
			}
		} finally {
			MyConnection.close(pstmt, con);
		}

	}

	/*
	 * 내 정보 보기 (아이디에 해당하는 고객정보를 반환한다)
	 * 
	 * @param email
	 * 
	 * @return customer(고객정보)
	 * 
	 * @throws FindException 아이디에 해당하는 고객이 없을때 오류발생, 처리 오류 발생
	 * 
	 * @author 영민
	 */

	public Customer selectByEmail(String email) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			throw new FindException("selectByEmail:" + e.getMessage());
		}
		String selectByEmailSQL = "SELECT c.customer_email, c.customer_pwd, c.customer_name, c.customer_birth_date, c.customer_gender,c.customer_phone, c.customer_addr"
				+ " , p.zipcode\r\n" + "      , p.buildingno\r\n"
				+ "      ,sido ||' ' || NVL(p.sigungu, ' ') ||' ' || NVL(p.eupmyun, ' ')  city    \r\n"
				+ "      ,doro || ' ' || DECODE(p.building2, '0' , p.building1, p.building1 ||'-' || p.building2) doro\r\n"
				+ "      ,p.building      \r\n"
				+ "FROM customer c LEFT JOIN postal p ON (c.buildingno = p.buildingno)\r\n"
				+ "WHERE customer_email=? and customer_status='1' and verification='y'";
		try {
			pstmt = con.prepareStatement(selectByEmailSQL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 행이 존재한다
				Customer c = new Customer();
				c.setCustomerEmail(rs.getString("customer_email"));
				c.setCustomerPwd(rs.getString("customer_pwd"));
				c.setCustomerName(rs.getString("customer_name"));
				c.setCustomerBirthDate(rs.getDate("customer_birth_date"));
				c.setCustomerGender(rs.getString("customer_gender"));
				c.setCustomerPhone(rs.getString("customer_phone"));
				Postal p = new Postal();
				p.setZipcode(rs.getString("zipcode"));
				p.setBuildingno(rs.getString("buildingno"));
				p.setCity(rs.getString("city"));
				p.setDoro(rs.getString("doro"));
				p.setBuilding(rs.getString("building"));
				c.setPostal(p);
				c.setCustomerAddr(rs.getString("customer_addr"));
				return c;
			}
			throw new FindException("selectByEmail: 이메일에 해당하는 고객없습니다");
		} catch (SQLException e) {
			throw new FindException("selectByEmail:" + e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	/*
	 * 내 정보 수정
	 * 
	 * @param customer c
	 * 
	 * @throws ModifyException
	 * 
	 * @author 영민
	 */

	public void update(Customer c) throws ModifyException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		}
		String updateSQL = "UPDATE customer ";
		boolean isModified = false;

		if (!c.getCustomerPwd().equals("")) {
			if (isModified) {
				updateSQL += ",";
			} else {
				updateSQL += "SET ";

			}
			updateSQL += "customer_pwd = '" + c.getCustomerPwd() + "' ";
			isModified = true;
		}

		if (!c.getCustomerName().equals("")) {
			if (isModified) {
				updateSQL += ",";
			} else {
				updateSQL += "SET ";

			}
			updateSQL += " customer_name = '" + c.getCustomerName() + "' ";
			isModified = true;
		}

		if (!(c.getCustomerBirthDate() ==null)) {
			if (isModified) {
				updateSQL += ",";
			} else {
				updateSQL += "SET ";

			}
			updateSQL += " customer_birth_date = '" + c.getCustomerBirthDate() + "' ";
			isModified = true;
		}

		if (!c.getCustomerGender().equals("")) {
			if (isModified) {
				updateSQL += ",";
			} else {
				updateSQL += "SET ";

			}
			updateSQL += " customer_gender = '" + c.getCustomerGender() + "' ";
			isModified = true;
		}
		if (!c.getCustomerPhone().equals("")) {
			if (isModified) {
				updateSQL += ",";
			} else {
				updateSQL += "SET ";

			}
			updateSQL += " customer_phone = '" + c.getCustomerPhone() + "' ";
			isModified = true;
		}
		if (!c.getPostal().getBuildingno().equals("")) {
			if (isModified) {
				updateSQL += ",";
			} else {
				updateSQL += "SET ";
			}
			updateSQL += " buildingno = '" + c.getPostal().getBuildingno() + "' ";
			isModified = true;
		}

		if (!c.getCustomerAddr().equals("")) {
			if (isModified) {
				updateSQL += ",";
			} else {
				updateSQL += "SET ";

			}
			updateSQL += " customer_addr = '" + c.getCustomerAddr() + "' ";
			isModified = true;
		}

		if (isModified == true) {
			updateSQL += "WHERE customer_id=?";
		}
		if (isModified == true) {

			try {
				pstmt = con.prepareStatement(updateSQL);
				pstmt.setString(1, c.getCustomerEmail());
				// System.out.println(c.getCustomerId());
				//int rowcnt = pstmt.executeUpdate();
				// System.out.println("처리건수:" + rowcnt);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ModifyException(e.getMessage());

			}

		}
		System.out.println(updateSQL);
	}
	/*
	 * update 관련 테스트
	 */
//	public static void main(String[] args) {
//		CustomerDAO dao = new CustomerDAO();
//		Customer c = new Customer();
//		c.setCustomerId("id1");
//		c.setCustomerPwd("ppp");
//		c.setCustomerName("nnn");
//		try {
//			dao.update(c);
//		} catch (ModifyException e) {
//			e.printStackTrace();
//		}
//		
//	}

	/*
	 * 회원탈퇴
	 * 
	 * @param customer c
	 * 
	 * @throws RemoveException
	 * 
	 * @author 영민
	 */

	public void update1(Customer c) throws RemoveException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
		String updateSQL = "UPDATE customer" + " SET customer_status=0" + " WHERE customer_email=?";
		try {
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, c.getCustomerEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RemoveException("removeEmail:" + e.getMessage());
		} finally {
			MyConnection.close(pstmt, con);

		}
	}
	
	public void verify(String email) throws ModifyException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		}
		String updateSQL = "UPDATE customer" + " SET verification='y'" + " WHERE customer_email=?";
		try {
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ModifyException("verifyEmail:" + e.getMessage());
		} finally {
			MyConnection.close(pstmt, con);

		}
	}

	/*
	 * 회원탈퇴 TEST
	 */
//	public static void main(String[] args) {
//		CustomerDAO dao = new CustomerDAO();
//		Customer c = new Customer();
//		c.setCustomerId("id1");
//		try {
//			dao.update1(c);
//		} catch (RemoveException e) {
//			e.printStackTrace();
//		}
//	}
}
