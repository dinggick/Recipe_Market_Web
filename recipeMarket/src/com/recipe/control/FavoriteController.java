package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.AddException;
import com.recipe.exception.DuplicatedException;
import com.recipe.exception.FindException;
import com.recipe.exception.RemoveException;
import com.recipe.service.FavoriteService;
import com.recipe.vo.Favorite;
import com.recipe.vo.RecipeInfo;

@WebServlet("/favorite/")
public class FavoriteController implements Controller {
	private static FavoriteController instance;
	private FavoriteService favoriteService;

	private FavoriteController() {
		favoriteService = FavoriteService.getInstance();
	}

	public static FavoriteController getInstance() {
		if (instance == null)
			instance = new FavoriteController();
		return instance;
	}
	
	/**
	 * @author Soojeong
	 * 즐겨찾기 controller 
	 * 즐겨찾기목록	- /favorite/favoriteList
	 * 즐겨찾기삭제	- /favorite/remove
	 * 즐겨찾기등록 	- /favorite/add
	 */

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = "/favoriteResult.jsp";

 		// 즐겨찾기 메뉴는 로그인한 사용자만 사용가능
		String customerEmail = (String)request.getSession().getAttribute("loginInfo");
		if( customerEmail == null) {
			request.setAttribute("status", "fail");
			request.setAttribute("msg","로그인 되지 않은 사용자의 접근입니다.");
			return servletPath;
		}
		
		// 즐겨찾기 목록 보기
		String pathInfo = request.getServletPath();
		if ( "/favorite/favoriteList".equals(pathInfo) ) {
			servletPath = favoriteList(request, response, customerEmail);
			
		// 즐겨찾기 등록 
		} else if ( "/favorite/add".equals(pathInfo) ) { 
			servletPath = addFavorite(request, response, customerEmail);
		
		// 즐겨찾기삭제 
		} else if ( "/favorite/remove".equals(pathInfo)) {
			servletPath = removeFavorite(request, response, customerEmail);
		}
		return servletPath;
	} // end of execute();
	
	
	private String favoriteList (HttpServletRequest request, HttpServletResponse response, String customerEmail) {
		String result = "/fail.jsp";
		
		try {
			List<Favorite> favoriteList = favoriteService.findById(customerEmail);
			request.setAttribute("favoriteList", favoriteList);
			result = "/favoriteList.jsp";
		
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg",e.getMessage());
			request.setAttribute("status", "fail");
		}
		return result;
	}
	
	private String removeFavorite (HttpServletRequest request, HttpServletResponse response, String customerEmail) {
		String result = "/fail.jsp";
		
		int recipeCode = Integer.parseInt(request.getParameter("recipeCode"));
		if( recipeCode == 0 ) {
			request.setAttribute("status", "fail");
			request.setAttribute("msg","즐겨찾기에 등록되지않은 레시피 입니다.");
			return result;
		}

		RecipeInfo recipeInfo = new RecipeInfo();
		recipeInfo.setRecipeCode(recipeCode);
		
		Favorite f = new Favorite();
		f.setcustomerEmail(customerEmail);
		f.setRecipeInfo(recipeInfo);
		
		try {
			favoriteService.remove(f);
			request.setAttribute("status", "success");
			result = "/success.jsp";
			
		} catch (RemoveException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("status", "fail");
		}
		
		return result;
	}
	
	private String addFavorite(HttpServletRequest request, HttpServletResponse response, String customerEmail) {
		String result = "/fail.jsp";
		
		int recipeCode = Integer.parseInt(request.getParameter("recipeCode"));
		if( recipeCode == 0 ) {
			request.setAttribute("status", "fail");
			request.setAttribute("msg","레시피정보가 올바르지않습니다.");
			return result;
		}
		
		RecipeInfo recipeInfo = new RecipeInfo();
		recipeInfo.setRecipeCode(recipeCode);
		
		Favorite f = new Favorite();
		f.setcustomerEmail(customerEmail);
		f.setRecipeInfo(recipeInfo);
		
		try {
			favoriteService.add(f);
			result="/success.jsp";
		
		} catch (DuplicatedException e) {
			e.printStackTrace();
			request.setAttribute("status", "fail");
			request.setAttribute("msg", e.getMessage());
			
		} catch (AddException e) {
			e.printStackTrace();
			request.setAttribute("status", "fail");
			request.setAttribute("msg", e.getMessage());
		}
		return result;
	}
	
} // end of ReviewController
