package com.recipe.control;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		} else if ("/graph4".equals(pathInfo)) { /* show graph4 */
			String rd_email = request.getParameter("rd_email");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			String customer_gender = request.getParameter("customer_gender");
			String age_group = request.getParameter("age_group");
			int count = Integer.parseInt(request.getParameter("count"));

			String[] sd = start_date.split("/");
			String[] ed = end_date.split("/");
			
			start_date = sd[0] +  sd[1]  + sd[2];
			end_date = ed[0] + ed[1] + ed[2];
						
			String g1 = customer_gender.charAt(0) + "";
			String g2 = customer_gender.charAt(1) + "";
			
			String[] age = age_group.split("_");
			int start_age = Integer.parseInt(age[0]);
			int end_age = Integer.parseInt(age[1]);
			
			try {
				List<Pair<String, Integer>> dataList = null;
				
				dataList = service.findByConditionG4(rd_email, start_date, end_date, g1, g2, start_age, end_age, count);
				System.out.println(dataList);
				request.setAttribute("data_list", dataList);

				jspFileName = "/Graph4.jsp";
				System.out.println("여기에요");
			} catch (FindException e) {
				e.printStackTrace();
			}
		}
		
		return jspFileName;
	}
}
