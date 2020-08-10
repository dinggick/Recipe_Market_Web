package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.exception.FindException;
import com.recipe.pair.Pair;
import com.recipe.service.StatisticsService;

/**
 * Statistics-related controller
 * @author yonghwan
 */
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
		
		HttpSession session = request.getSession();
		
		String pathInfo = request.getServletPath().substring(request.getServletPath().lastIndexOf("/"));
		String jspFileName = "/fail.jsp";
		
		if("/graph1".equals(pathInfo)) { /* show graph1 */
			try {
				List<Pair<Integer, Pair<String, Integer>>> dataList = null;
				
				String year = request.getParameter("year");
				session.setAttribute("year", year);
				
				dataList = service.findByYearG1(year);				
				request.setAttribute("data_list", dataList);
								
				jspFileName = "/Graph1.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if("/graph2".equals(pathInfo)) { /* show graph2 */
			try {
				List<Pair<String, Integer>> dataList = null;
				
				String year = request.getParameter("year");
				int count = Integer.parseInt(request.getParameter("count"));
				
				session.setAttribute("year", year);
				session.setAttribute("count", count);

				dataList = service.findByYearG2(year, count);
								
				request.setAttribute("data_list", dataList);

				jspFileName = "/Graph2.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if("/graph3".equals(pathInfo)) { /* show graph3 */
			try {
				List<Pair<String, Integer>> dataList = null;
				
				String term = request.getParameter("term");
				int count = Integer.parseInt(request.getParameter("count"));
				
				session.setAttribute("count", count);
				
				String[] dt = term.split("_");
				String startDate = dt[0], endDate = dt[1];
				
				session.setAttribute("startDate", startDate);
				session.setAttribute("endDate", endDate);
				
				dataList = service.findBySeasonG3(startDate, endDate, count);
				
				request.setAttribute("data_list", dataList);

				jspFileName = "/Graph3.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		}
		
		return jspFileName;
	}
}
