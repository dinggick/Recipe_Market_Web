/**
 * Statistics-related controller
 * @author yonghwan
 */
package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.pair.Pair;
import com.recipe.service.StatisticsService;

public class StatisticsController implements Controller {
	private static StatisticsController ctr = new StatisticsController();
	private StatisticsService service;
	
	private StatisticsController() {
		service = StatisticsService.getInstance();
	}
	
	/**
	 * 
	 * @return StatisticsController
	 */
	public static StatisticsController getInstance() {
		return ctr;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pathInfo = request.getServletPath().substring(request.getServletPath().lastIndexOf("/"));

		if("/graph1".equals(pathInfo)) { /* show graph1 */
			try {
				List<Pair<Integer, Pair<String, Integer>>> dataList = null;
				dataList = service.findByYearG1(request.getParameter("year"));
				
				request.setAttribute("data_list", dataList);
				
				return "/graph1.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if("/graph2".equals(pathInfo)) { /* show graph2 */
			try {
				List<Pair<String, Integer>> dataList = null;
				dataList = service.findByYearG2(request.getParameter("year"));
				
				request.setAttribute("data_list", dataList);

				return "/graph2.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if("/graph3".equals(pathInfo)) { /* show graph3 */
			try {
				List<Pair<String, Integer>> dataList = null;
				dataList = service.findBySeasonG3(request.getParameter("start_date"),
						request.getParameter("end_date"));
				
				request.setAttribute("data_list", dataList);

				return "/graph3.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		}
		
		return "/fail.jsp";
	}
}
