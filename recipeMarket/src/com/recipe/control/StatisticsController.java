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
		
		if (session.getAttribute("loginInfo") == null) {
			request.setAttribute("msg", "로그인이 필요한 페이지입니다.");
			return jspFileName;
		}
				
		if("/graph1".equals(pathInfo)) { /* Show graph1 */
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
		} else if("/graph2".equals(pathInfo)) { /* Show graph2 */
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
		} else if("/graph3".equals(pathInfo)) { /* Show graph3 */
			try {
				List<Pair<String, Pair<String, Integer>>> dataList = null;
				
				String term = request.getParameter("term");
				int count = Integer.parseInt(request.getParameter("count"));
				
				session.setAttribute("count", count);
				
				String[] dt = term.split("_");
				String startDate = dt[0], endDate = dt[1];
				
				session.setAttribute("startMonth", dt[0].substring(0, 4));
				session.setAttribute("startDate", dt[0].substring(4, 6));
				session.setAttribute("endMonth", dt[1].substring(0, 4));
				session.setAttribute("endDate", dt[1].substring(4, 6));
				
				dataList = service.findBySeasonG3(startDate, endDate, count);
				
				request.setAttribute("data_list", dataList);

				jspFileName = "/Graph3.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		} else if ("/graph4".equals(pathInfo)) { /* Show graph4 */
			String rd_email = request.getParameter("rd_email");			
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			String customer_gender = request.getParameter("customer_gender");
			String age_group = request.getParameter("age_group");
			int order_by = Integer.parseInt(request.getParameter("order_by"));
			int count = Integer.parseInt(request.getParameter("count"));
			
			session.setAttribute("rd_email", rd_email);
			session.setAttribute("start_date", start_date);
			session.setAttribute("end_date", end_date);

			String[] start_dt = start_date.split("/");
			String[] end_dt = end_date.split("/");
			
			start_date = start_dt[0] +  start_dt[1]  + start_dt[2];
			end_date = end_dt[0] + end_dt[1] + end_dt[2];
						
			String gender1 = customer_gender.charAt(0) + "";
			String gender2 = customer_gender.charAt(1) + "";
			
			if (!gender1.equals(gender2)) {
				session.setAttribute("gender", "전체");
			} else if (gender1.equals("M")) {
				session.setAttribute("gender", "남성");
			} else {
				session.setAttribute("gender", "여성");
			}
			
			String[] age = age_group.split("_");
			int start_age = Integer.parseInt(age[0]);
			int end_age = Integer.parseInt(age[1]);
			
			session.setAttribute("start_age", start_age);
			
			System.out.println(rd_email + " " + start_date + " " + end_date + " " + gender1 + " " + gender2 + " " +
					start_age + " " + end_age + " " + order_by + " " + count);
			
			try {
				List<Pair<String, Pair<Integer, Integer>>> dataList = null;
				
				dataList = service.findByConditionG4(rd_email, start_date, end_date, gender1, gender2, start_age, end_age, order_by, count);
				request.setAttribute("data_list", dataList);

				jspFileName = "/Graph4.jsp";
				
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		}
		
		return jspFileName;
	}
}
