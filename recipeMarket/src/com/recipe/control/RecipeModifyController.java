package com.recipe.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.service.RecipeService;
import com.recipe.vo.RecipeInfo;

public class RecipeModifyController implements Controller {
	private static RecipeModifyController instance;
	private RecipeService service;
	private static final long serialVersionUID = 1L;
	
	private RecipeModifyController() {
		service = RecipeService.getInstance();
	}

	public static RecipeModifyController getInstance() {
		if (instance == null) instance = new RecipeModifyController();
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String servletPath = "";
//		HttpSession session = request.getSession();
		String rdEmail = request.getParameter("rdEmail");
		if(!rdEmail.equals(request.getSession().getAttribute("loginInfo"))) {
			request.setAttribute("msg", "이 레시피의 작성자가 아닙니다");
			return "/fail.jsp";
		}
		System.out.println("레시피 수정뷰");
		String recipeCode = request.getParameter("recipeCode");
		System.out.println("--------- recipeCode : " + recipeCode);
		
		try {
			if (!isNullOrEmpty(recipeCode)) {
				System.out.println("수정");
				//findByCode를 이용해서 받아온 recipeCode값으로 recipeInfo값들을 받아서 jsp로 보내준다
				RecipeInfo recipeInfo = service.findByCode(Integer.parseInt(recipeCode));
				//받아온 recipeInfo값들 출력해보기
				System.out.println("--------- recipeInfo : " + recipeInfo.toString());
				request.setAttribute("recipeInfo", recipeInfo);
			}
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
		}

		servletPath = "/success.jsp";
		return servletPath;
	}

	/**
	 * String 문자열의 값이 null 이나 empty 이면 true 반환
	 * 
	 * @param value
	 * @return
	 */
	private Boolean isNullOrEmpty(String value) {
		if (value != null && value.length() > 0) {
			return false;
		}
		return true;
	}
}
