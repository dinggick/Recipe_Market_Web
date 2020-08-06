package com.recipe.service;

import java.util.List;

import com.recipe.dao.GraphDAO;
import com.recipe.exception.FindException;
import com.recipe.pair.Pair;

//import javafx.util.Pair;

public class StatisticsService {
	private GraphDAO dao;
	
	/**
	 * 
	 */
	public StatisticsService() {
		dao = new GraphDAO();
	}
	
	/**
	 * Function for graph1
	 * @param year
	 * @return List<Pair<Integer, Pair<String, Integer>>>
	 * @throws FindException
	 * @author yonghwan
	 */
	public List<Pair<Integer, Pair<String, Integer>>> findByYearG1(String year) 
			throws FindException {
		
		return dao.selectByYearG1(year);
	}
	
	/**
	 * Function for graph2
	 * @param year
	 * @return List<Pair<String, Integer>>
	 * @throws FindException
	 * @author yonghwan
	 */
	public List<Pair<String, Integer>> findByYearG2(String year) 
			throws FindException {
		
		return dao.selectByYearG2(year);
	}
	
	/**
	 * Function for graph3
	 * @param startDate
	 * @param endDate
	 * @return List<Pair<String, Integer>>
	 * @throws FindException
	 * @author yonghwan
	 */
	public List<Pair<String, Integer>> findBySeasonG3(String startDate, String endDate) 
			throws FindException {
		
		return dao.selectBySeasonG3(startDate, endDate);
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
			for (Pair<String, Integer> p : service.findByYearG2("2020")) {
				System.out.println(p.getKey() + " " + p.getValue());
			}
			for (Pair<String, Integer> p : service.findBySeasonG3("202006", "202008")) {
				System.out.println(p.getKey() + " " + p.getValue());
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
}
