package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String pathInfo = request.getServletPath().substring(request.getServletPath().lastIndexOf("/"));
		String jspFileName = "/fail.jsp";
		
		if("/graph1".equals(pathInfo)) { /* show graph1 */
			try {
				List<Pair<Integer, Pair<String, Integer>>> dataList = null;
				dataList = service.findByYearG1(request.getParameter("year"));
				
				request.setAttribute("data_list", dataList);
				
				jspFileName = "/graph1.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if("/graph2".equals(pathInfo)) { /* show graph2 */
			try {
				List<Pair<String, Integer>> dataList = null;
				dataList = service.findByYearG2(request.getParameter("year"), 
						Integer.parseInt(request.getParameter("count")));
				
				request.setAttribute("data_list", dataList);

				jspFileName = "/graph2.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if("/graph3".equals(pathInfo)) { /* show graph3 */
			try {
				List<Pair<String, Integer>> dataList = null;
				
				String termStr = request.getParameter("term");
				
				String[] dt = termStr.split("/");
				String startDate = dt[0], endDate = dt[1];
				
				dataList = service.findBySeasonG3(startDate, endDate,
						Integer.parseInt(request.getParameter("count")));
				
				request.setAttribute("data_list", dataList);

				jspFileName = "/graph3.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		}
		
		return jspFileName;
	}
}
