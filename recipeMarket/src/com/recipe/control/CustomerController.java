package com.recipe.control;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.service.AccountService;
import com.recipe.vo.Customer;
import com.recipe.vo.Postal;
import com.sun.jmx.snmp.Timestamp;

public class CustomerController implements Controller {
	private static CustomerController instance;
	private AccountService accountService;

	private CustomerController() {
		this.accountService = AccountService.getInstance();

	}

	public static CustomerController getInstance() {
		if (instance == null) {
			instance = new CustomerController();

		}
		return instance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sp = request.getServletPath();
		if (sp.equals("/register")) {
			String customer_email = request.getParameter("customer_email");
			String customer_pwd = request.getParameter("customer_pwd");
			String customer_name = request.getParameter("customer_name");
			
			String customer_birth_date = request.getParameter("customer_birth_date");
			int yy = Integer.parseInt(customer_birth_date.substring(0, 1));
			int mm = Integer.parseInt(customer_birth_date.substring(2, 3));
			int dd = Integer.parseInt(customer_birth_date.substring(4, 5));
			Date dt = new Date(yy, mm, dd);
			
			String customer_gender = request.getParameter("customer_gender");
			String customer_phone = request.getParameter("customer_phone");
			String buildingno = request.getParameter("buildingno");
			Postal postal = new Postal();
			postal.setBuildingno(buildingno);
			String customer_addr = request.getParameter("customer_addr");
			Customer c = new Customer(customer_email, customer_pwd, customer_name, customer_gender, dt, customer_phone, postal, customer_addr);
			
		} else if (sp.equals("/myInfo")) {
			String customerEmail = (String)request.getSession().getAttribute("loginInfo");
			try {
				Customer c = accountService.findByEmail(customerEmail);
				request.setAttribute("info", c);
				return "/myinfo.jsp";
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage().replace("\"", ""));
				return "/fail.jsp";
			}
			
		} else if (sp.equals("/customer/update")) {

		} else if (sp.equals("/customer/delete")) {

		}
		return null;
	}

}
