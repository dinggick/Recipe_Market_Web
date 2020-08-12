package com.recipe.service;

import java.util.List;

import com.recipe.dao.GraphDAO;
import com.recipe.exception.FindException;
import com.recipe.pair.Pair;

//import javafx.util.Pair;

public class StatisticsService {
	private static StatisticsService service = new StatisticsService();
	
	private GraphDAO dao;
	
	/**
	 * 
	 */
	private StatisticsService() {
		dao = new GraphDAO();
	}
	
	/**
	 * For single tone pattern
	 * @return StatisticsService
	 */
	public static StatisticsService getInstance() {
		return service;
	}
	
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
	public List<Pair<Integer, Pair<String, Integer>>> findByYearG1(String year) 
			throws FindException {
		
		return dao.selectByYearG1(year);
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
	public List<Pair<String, Integer>> findByYearG2(String year, int count) 
			throws FindException {
		
		return dao.selectByYearG2(year, count);
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
	public List<Pair<String, Pair<String, Integer>>> findBySeasonG3(String startDate, String endDate, int count) 
			throws FindException {
		
		return dao.selectBySeasonG3(startDate, endDate, count);
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
	public List<Pair<String, Pair<Integer, Integer>>> findByConditionG4(String rd_email,
			String startDate, String endDate,
			String gender1, String gender2,
			int start_age, int end_age, 
			int order_by, int count) throws FindException {
	
		return dao.selectByConditionG4(rd_email, startDate, endDate, gender1, gender2, start_age, end_age, order_by, count);
	}
	
	/**
	 * Main function for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StatisticsService service = new StatisticsService();
		try {
			for (Pair<Integer, Pair<String, Integer>> p : service.findByYearG1("2020")) {
				System.out.println(p.getKey() + " " + p.getValue().getKey() + " " + p.getValue().getValue());
			}
			for (Pair<String, Integer> p : service.findByYearG2("2020", 10)) {
				System.out.println(p.getKey() + " " + p.getValue());
			}
			for (Pair<String, Pair<String, Integer>> p : service.findBySeasonG3("202006", "202008", 10)) {
				System.out.println(p.getKey() + " " + p.getValue().getKey() + " " + p.getValue().getValue());
			}
			List<Pair<String, Pair<Integer, Integer>>> list = service.findByConditionG4("rd01@recipe.com", "19900601", "20200801", "M", "F", 10, 99, 2, 10);
			for (Pair<String, Pair<Integer, Integer>> p : list) {
				System.out.println(p.getKey() + " " + p.getValue().getKey() + " " + p.getValue().getValue());
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
}
