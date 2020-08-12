package com.recipe.control;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.AddException;
import com.recipe.exception.DuplicatedException;
import com.recipe.exception.FindException;
import com.recipe.exception.ModifyException;
import com.recipe.exception.RemoveException;
import com.recipe.mail.Mail;
import com.recipe.service.AccountService;
import com.recipe.vo.Customer;
import com.recipe.vo.Postal;

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
		
		request.setCharacterEncoding("UTF-8");
		String pathInfo = request.getServletPath().substring(request.getServletPath().lastIndexOf("/"));

		if (pathInfo.equals("/register")) {
			String customer_email = request.getParameter("customer_email");
			String customer_pwd = request.getParameter("customer_pwd");
			String customer_name = request.getParameter("customer_name");
			System.out.println(customer_name);
			String customer_birth_date = request.getParameter("customer_birth_date");
			System.out.println(customer_birth_date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			Date dt = null;
			try {
				dt=new Date(sdf.parse(customer_birth_date).getTime());
				System.out.println(sdf.parse(customer_birth_date));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			String customer_gender = request.getParameter("customer_gender");
			String customer_phone = request.getParameter("customer_phone");
			String buildingno = request.getParameter("buildingno");
			Postal postal = new Postal();
			postal.setBuildingno(buildingno);
			String customer_addr = request.getParameter("customer_addr");
			
			System.out.println(customer_addr);
			
			Customer c = new Customer(customer_email, customer_pwd, customer_name, customer_gender,dt, customer_phone,
					postal, customer_addr);
			
			

			try {
				accountService.add(c);
				Mail mail = new Mail();
				mail.sendVerification(c.getCustomerEmail());
				return "/success.jsp";
			} catch (DuplicatedException e) {
				e.printStackTrace();
				return "/fail.jsp";
			} catch (AddException e) {
				e.printStackTrace();
				return "/fail.jsp";
			}

		} else if (pathInfo.equals("/myInfo")) {
			String customerEmail = request.getParameter("customer_email");
			try {
				Customer customer = accountService.findByEmail(customerEmail);
				request.setAttribute("info", customer);
				return "/myInfo.jsp";
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage().replace("\"", ""));
				return "/fail.jsp";
			}

		} else if (pathInfo.equals("/update")) {
			String customer_email = request.getParameter("customer_email");
			System.out.println("cu:" + customer_email);
			String customer_pwd = request.getParameter("customer_pwd");
			String customer_name = request.getParameter("customer_name");

			String customer_birth_date = request.getParameter("customer_birth_date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			Date dt = null;
			try {
				dt=new Date(sdf.parse(customer_birth_date).getTime());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			String customer_gender = request.getParameter("customer_gender");
			String customer_phone = request.getParameter("customer_phone");
			String buildingno = request.getParameter("buildingno");
			Postal postal = new Postal();
			postal.setBuildingno(buildingno);
			String customer_addr = request.getParameter("customer_addr");
			Customer c = new Customer(customer_email, customer_pwd, customer_name, customer_gender,dt, customer_phone,
					postal, customer_addr);

			try {
				accountService.modify(c);
				return "/success.jsp";
			} catch (ModifyException e) {
				e.printStackTrace();
				return "/fail.jsp";
			}
		} else if (pathInfo.equals("/delete")) {
			String customer_email = request.getParameter("customer_email");
			String customer_pwd = request.getParameter("customer_pwd");
			String customer_name = request.getParameter("customer_name");

			String customer_birth_date = request.getParameter("customer_birth_date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			Date dt = null;
			try {
				dt=new Date(sdf.parse(customer_birth_date).getTime());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			
			String customer_gender = request.getParameter("customer_gender");
			String customer_phone = request.getParameter("customer_phone");
			String buildingno = request.getParameter("buildingno");
			Postal postal = new Postal();
			postal.setBuildingno(buildingno);
			String customer_addr = request.getParameter("customer_addr");
			Customer c = new Customer(customer_email, customer_pwd, customer_name, customer_gender,dt, customer_phone,
					postal, customer_addr);

			try {
				accountService.remove(c);
				return "/success.jsp";
			} catch (RemoveException e) {
				e.printStackTrace();
				return "/fail.jsp";
			}

		}else if(pathInfo.equals("/verify")) {
			String email = request.getParameter("email");
			 try {
				accountService.verify(email);
				return "/static/login.html";
			} catch (ModifyException e) {
				e.printStackTrace();
				return "/fail.jsp";
			}
			
		}
		return "/fail.jsp";
	}

}
