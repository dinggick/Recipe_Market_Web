package com.recipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.recipe.exception.FindException;
import com.recipe.jdbc.MyConnection;
import com.recipe.pair.Pair;

//import javafx.util.Pair;

public class GraphDAO {
	/**
	 * Function for Graph1
	 * Graph1 is Annual Gender and Age purchase amount Statistics
	 * @param year
	 * @return List<Pair<Integer, Pair<String, Integer>>>
	 * Pair<Integer(age_group), Pair<String(group_gender), Integer(purchase_amount)>> is
	 * Object(age_group, group_gender, purchase_amount)
	 * @throws FindException
	 * @author yonghwan
	 */
	public List<Pair<Integer, Pair<String, Integer>>> selectByYearG1(String year) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Pair<Integer, Pair<String, Integer>>> list = null;

		Pair<Integer, Pair<String, Integer>> p1 = null;
		Pair<String, Integer> p2 = null;

		String selectByYearSQL = 
				"SELECT TRUNC(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), customer_birth_date) / 12) / 10) * 10 AS age_group\r\n"
				+ "    , c.customer_gender AS group_gender\r\n"
				+ "    , SUM(recipe_price * pd.purchase_quantity) AS purchase_amount\r\n"
				+ "FROM customer c JOIN purchase p ON (c.customer_email = p.customer_email)\r\n"
				+ "    JOIN purchase_detail pd ON (p.purchase_code = pd.purchase_code)\r\n"
				+ "    JOIN recipe_info ON (pd.recipe_code = recipe_info.recipe_code)\r\n"
				+ "WHERE TO_CHAR(p.purchase_date, 'YYYY') = ?\r\n"
				+ "GROUP BY TRUNC(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), customer_birth_date) / 12) / 10)\r\n"
				+ "    , c.customer_gender\r\n"
				+ "ORDER BY TRUNC(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), customer_birth_date) / 12) / 10)\r\n"
				+ "    , c.customer_gender";

		try {
			con = MyConnection.getConnection();

			pstmt = con.prepareStatement(selectByYearSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, year);
			rs = pstmt.executeQuery();

			if (!rs.next())
				throw new FindException("There is no data corresponding to the condition.");

			rs.previous();

			list = new ArrayList<>();

			while (rs.next()) {
				int ageGroup = rs.getInt("age_group");
				String groupGender = rs.getString("group_gender");
				int purchaseAmount = rs.getInt("purchase_amount");

				p2 = new Pair<>(groupGender, purchaseAmount);
				p1 = new Pair<>(ageGroup, p2);
				
				list.add(p1);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}

		return list;
	}
	
	/**
	 * Function for Graph2
	 * Graph2 is Departmental Revenue Statistics by Year
	 * @param year
	 * @return List<Pair<String, Integer>>
	 * Pair<String(rd_email), Integer(total_sales)> is
	 * Object(rd_email, total_sales)
	 * @throws FindException
	 * @author yonghwan
	 */
	public List<Pair<String, Integer>> selectByYearG2(String year) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Pair<String, Integer>> list = null;
		Pair<String, Integer> p1 = null;

		String selectByYearMonthSQL = 
				"SELECT rd.rd_email AS rd_email\r\n"
				+ "    , SUM(ri.recipe_price * pd.purchase_quantity) AS total_sales\r\n"
				+ "FROM rd JOIN recipe_info ri ON (rd.rd_email = ri.rd_email)\r\n"
				+ "    JOIN purchase_detail pd ON (ri.recipe_code = pd.recipe_code)\r\n"
				+ "    JOIN purchase p ON (pd.purchase_code = p.purchase_code)\r\n"
				+ "WHERE TO_CHAR(p.purchase_date, 'YYYY') = ?\r\n" + "GROUP BY rd.rd_email";

		try {
			con = MyConnection.getConnection();

			pstmt = con.prepareStatement(selectByYearMonthSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, year);
			rs = pstmt.executeQuery();

			if (!rs.next())
				throw new FindException("There is no data corresponding to the condition.");

			list = new ArrayList<>();

			rs.previous();

			while (rs.next()) {
				String rdEmail = rs.getString("rd_email");
				int totalSales = rs.getInt("total_sales");

				p1 = new Pair<>(rdEmail, totalSales);
				
				list.add(p1);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}

		return list;
	}
	
	/**
	 * Function for Graph3
	 * Graph3 is seasonal recipe sales Statistics
	 * @param startDate
	 * @param endDate
	 * @return List<Pair<String, Integer>>
	 * Pair<String(recipe_name), Integer(sales_volume)> is
	 * Object(recipe_name, sales_volume)
	 * @throws FindException
	 * @author yonghwan
	 */
	public List<Pair<String, Integer>> selectBySeasonG3(String startDate, String endDate) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Pair<String, Integer>> list = null;
		Pair<String, Integer> p1 = null;

		String selectByYearMonthSQL = 
				"SELECT ri.recipe_name AS recipe_name\r\n" + 
				"    , SUM(pd.purchase_quantity) AS sales_volume\r\n" + 
				"FROM recipe_info ri JOIN purchase_detail pd ON (ri.recipe_code = pd.purchase_code)\r\n" + 
				"    JOIN purchase p ON (pd.purchase_code = p.purchase_code)\r\n" + 
				"WHERE TO_CHAR(p.purchase_date, 'YYYYMM') BETWEEN ? AND ? -- '202012' AND '202102' is 2020's winter\r\n" + 
				"GROUP BY ri.recipe_name";

		try {
			con = MyConnection.getConnection();

			pstmt = con.prepareStatement(selectByYearMonthSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);

			rs = pstmt.executeQuery();

			if (!rs.next())
				throw new FindException("There is no data corresponding to the condition.");

			list = new ArrayList<>();

			rs.previous();

			while (rs.next()) {
				String recipeName = rs.getString("recipe_name");
				int salesVolume = rs.getInt("sales_volume");

				p1 = new Pair<>(recipeName, salesVolume);
				
				list.add(p1);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}

		return list;
	}

	/**
	 * Main function for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GraphDAO dao = new GraphDAO();
		try {
			for (Pair<Integer, Pair<String, Integer>> p : dao.selectByYearG1("2020")) {
				System.out.println(p.getKey() + " " + p.getValue().getKey() + " " + p.getValue().getValue());
			}
			for (Pair<String, Integer> p : dao.selectByYearG2("2020")) {
				System.out.println(p.getKey() + " " + p.getValue());
			}
			for (Pair<String, Integer> p : dao.selectBySeasonG3("202006", "202008")) {
				System.out.println(p.getKey() + " " + p.getValue());
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
}
