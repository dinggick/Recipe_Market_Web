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
				+ "ORDER BY 1\r\n"
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
				list.add(new Pair<>(rs.getInt("age_group")
						, new Pair<>(rs.getString("group_gender")
								, rs.getInt("purchase_amount"))));
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
	public List<Pair<String, Integer>> selectByYearG2(String year, int count) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Pair<String, Integer>> list = null;

		String selectByYearMonthSQL = 
				"SELECT rd_email, total_sales\r\n" + 
				"FROM (\r\n" + 
				"        SELECT rownum, a.*\r\n" + 
				"        FROM (\r\n" + 
				"            SELECT rd.rd_email AS rd_email,\r\n" + 
				"                SUM(ri.recipe_price * pd.purchase_quantity) AS total_sales\r\n" + 
				"            FROM rd JOIN recipe_info ri ON (rd.rd_email = ri.rd_email)\r\n" + 
				"                JOIN purchase_detail pd ON (ri.recipe_code = pd.recipe_code)\r\n" + 
				"                JOIN purchase p ON (pd.purchase_code = p.purchase_code)\r\n" + 
				"            WHERE TO_CHAR(p.purchase_date, 'YYYY') = ?\r\n" + 
				"            GROUP BY rd.rd_email\r\n" + 
				"            ORDER BY 2 DESC) a  )\r\n" + 
				"WHERE rownum <= ?";

		try {
			con = MyConnection.getConnection();

			pstmt = con.prepareStatement(selectByYearMonthSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, year);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();

			if (!rs.next())
				throw new FindException("There is no data corresponding to the condition.");

			list = new ArrayList<>();

			rs.previous();

			while (rs.next()) {
				list.add(new Pair<>(rs.getString("rd_email")
						, rs.getInt("total_sales")));
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
	public List<Pair<String, Pair<String, Integer>>> selectBySeasonG3(String startDate, String endDate, int count) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Pair<String, Pair<String, Integer>>> list = null;

		String selectByYearMonthSQL = 
				"SELECT recipe_name, rd_email, sales_volume\r\n" + 
				"FROM (\r\n" + 
				"        SELECT rownum, a.*\r\n" + 
				"        FROM (\r\n" + 
				"            SELECT ri.recipe_name AS recipe_name,\r\n" + 
				"                ri.rd_email,\r\n" + 
				"                SUM(pd.purchase_quantity) AS sales_volume\r\n" + 
				"            FROM recipe_info ri JOIN purchase_detail pd ON (ri.recipe_code = pd.purchase_code)\r\n" + 
				"                JOIN purchase p ON (pd.purchase_code = p.purchase_code)\r\n" + 
				"            WHERE TO_CHAR(p.purchase_date, 'YYYYMM') BETWEEN ? AND ? -- '202012' AND '202102' is 2020's winter\r\n" + 
				"            GROUP BY ri.recipe_name, ri.rd_email\r\n" + 
				"            ORDER BY 3 DESC ) a )\r\n" + 
				"WHERE rownum <= ?";

		try {
			con = MyConnection.getConnection();

			pstmt = con.prepareStatement(selectByYearMonthSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setInt(3, count);

			rs = pstmt.executeQuery();

			if (!rs.next())
				throw new FindException("There is no data corresponding to the condition.");

			list = new ArrayList<>();

			rs.previous();

			while (rs.next()) {				
				list.add(new Pair<>(rs.getString("rd_email")
						, new Pair<>(rs.getString("recipe_name"), 
						rs.getInt("sales_volume"))));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}

		return list;
	}
	
	/**
	 * Function for Graph4
	 * @param rd_email
	 * @param startDate
	 * @param endDate
	 * @param gender1
	 * @param gender2
	 * @param start_age
	 * @param end_age
	 * @param count
	 * @return
	 * @throws FindException
	 * @author yonghwan
	 */
	public List<Pair<String, Pair<Integer, Integer>>> selectByConditionG4(String rd_email,
			String startDate, String endDate,
			String gender1, String gender2,
			int start_age, int end_age, 
			int order_by, int count) throws FindException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Pair<String, Pair<Integer, Integer>>> list = null;

		String selectByYearMonthSQL = 
				"SELECT recipe_name, total_sales, total_amount\r\n" + 
				"FROM (\r\n" + 
				"        SELECT rownum r, a.*\r\n" + 
				"        FROM (\r\n" + 
				"                SELECT ri.recipe_name AS recipe_name,\r\n" + 
				"                    SUM(ri.recipe_price * pd.purchase_quantity) AS total_sales,\r\n" + 
				"                    SUM(pd.purchase_quantity) AS total_amount\r\n" + 
				"                FROM rd r JOIN recipe_info ri ON (r.rd_email = ri.rd_email)\r\n" + 
				"                    JOIN purchase_detail pd ON (ri.recipe_code = pd.purchase_code)\r\n" + 
				"                    JOIN purchase p ON (pd.purchase_code = p.purchase_code)\r\n" + 
				"                    JOIN customer c ON (p.customer_email = c.customer_email)\r\n" + 
				"                WHERE " + (rd_email.equals("all") ? "" : "(r.rd_email = ?) AND ") +				
				"                    (TO_CHAR(p.purchase_date, 'YYYYMMDD') BETWEEN ? AND ?)\r\n" + 
				"                    AND (c.customer_gender = ?)\r\n" + 
				"                            OR\r\n" + 
				"                        (c.customer_gender = ?)\r\n" + 
				"                    AND \r\n" + 
				"                        (TRUNC(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), customer_birth_date) / 12) / 10) * 10) >= ?\r\n" + 
				"                            AND\r\n" + 
				"                        (TRUNC(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), customer_birth_date) / 12) / 10) * 10) <= ?\r\n" + 
				"                GROUP BY ri.recipe_name\r\n" + 
				"                ORDER BY " + order_by + " DESC) a\r\n" + 
				"        )\r\n" + 
				"WHERE rownum <= ?";;

		try {
			con = MyConnection.getConnection();

			pstmt = con.prepareStatement(selectByYearMonthSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			int idx = 1;
			
			if (!rd_email.equals("all")) {
				pstmt.setString(idx++, rd_email);
			}
			
			pstmt.setString(idx++, startDate);
			pstmt.setString(idx++, endDate);
			pstmt.setString(idx++, gender1);
			pstmt.setString(idx++, gender2);
			pstmt.setInt(idx++, start_age);
			pstmt.setInt(idx++, end_age);
			pstmt.setInt(idx, count);

			rs = pstmt.executeQuery();

			if (!rs.next())
				throw new FindException("There is no data corresponding to the condition.");

			list = new ArrayList<>();

			rs.previous();

			while (rs.next()) {				
				list.add(new Pair<>(rs.getString("recipe_name"), 
						new Pair<>(rs.getInt("total_sales"), 
								rs.getInt("total_amount"))));
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
			for (Pair<String, Integer> p : dao.selectByYearG2("2020", 10)) {
				System.out.println(p.getKey() + " " + p.getValue());
			}
			for (Pair<String, Pair<String, Integer>> p : dao.selectBySeasonG3("202006", "202008", 10)) {
				System.out.println(p.getKey() + " " + p.getValue().getKey() + " " + p.getValue().getValue());
			}
			List<Pair<String, Pair<Integer, Integer>>> list = dao.selectByConditionG4(
					"jjj1211@hanmir.com", "19900601", "20200801", "M", "F", 40, 49, 2, 10);
			for (Pair<String, Pair<Integer, Integer>> p : list) {
				System.out.println(p.getKey() + " " + p.getValue().getKey() + " " + p.getValue().getValue());
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
}
