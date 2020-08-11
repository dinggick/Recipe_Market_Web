package com.recipe.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.service.RecipeService;
import com.recipe.vo.RecipePage;

public class MyRecipeListController implements Controller {
	private static MyRecipeListController instance;
	private RecipeService service;
	private static final long serialVersionUID = 1L;
	
	private MyRecipeListController() {
		service = RecipeService.getInstance();
	}

	public static MyRecipeListController getInstance() {
		if (instance == null) instance = new MyRecipeListController();
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String servletPath = "";
		HttpSession session = request.getSession();
		System.out.println("나의 레시피 뷰");
		String rdEmail = "rd04@recipe.com";//(String)session.getAttribute("loginInfo");
		System.out.println("--------- rdEmail : " + rdEmail);

		int pageSize = 10;
		String pageNo = nvl(request.getParameter("pageNo"), "1");
		String orderType = nvl(request.getParameter("orderType"), "P");	// orderType (판매량 : P, 총 매출액 : T)
		
		try {
			if (!isNullOrEmpty(rdEmail)) {
				//System.out.println("나의 레시피 보기");
				int myRecipeCount = service.myRecipeCount(rdEmail);
				request.setAttribute("myRecipeCount", myRecipeCount);
				if (myRecipeCount > 0) {
					//myRecipeList를 이용해서 받아온 recipeInfo값들을 jsp로 보내준다
					List<RecipePage> myRecipeListPage = service.myRecipeListPage(rdEmail, Integer.parseInt(pageNo), pageSize, orderType);
					//받아온 recipeInfo값들 출력해보기
					//System.out.println("--------- myRecipeListPage : " + myRecipeListPage.toString());
					request.setAttribute("myRecipeListPage", myRecipeListPage);
				}
				
				// 페이징 정보 세팅
				int totalCount = myRecipeCount;	// 총 게시글 
				int currentPage = Integer.parseInt(pageNo); // 현재 페이지 (get)
				int totalPage = (int) Math.ceil((double)totalCount / pageSize);
				int groupSize = 5;		// 한 그룹에 표시할 페이지 번호 수
				int currentGroup = ((currentPage - 1)/groupSize) + 1;
				int totalGroup = (int) Math.ceil((double)totalPage / groupSize);
				
				// pagenation Setting
				int currentGroupStartNo = (groupSize * (currentGroup - 1)) + 1;
				int currentGroupEndNo = currentGroupStartNo + groupSize - 1;
				currentGroupEndNo = (currentGroupEndNo < totalPage ? currentGroupEndNo : totalPage);
				// 이전 : prev1
				int prevGroupLastNo = (currentGroup < 2 ? 1 : (currentGroup - 1) * groupSize);
				// 다음 : next1
				int nextGroupStartNo = (currentGroup < totalGroup ? (currentGroup * groupSize) + 1 : totalPage);

				// paging 관련 attribute
				request.setAttribute("pageNo", pageNo);
				request.setAttribute("orderType", orderType);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("currentGroupStartNo", currentGroupStartNo);
				request.setAttribute("currentGroupEndNo", currentGroupEndNo);
				request.setAttribute("prevGroupLastNo", prevGroupLastNo);
				request.setAttribute("nextGroupStartNo", nextGroupStartNo);
				
			}
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
		}

		servletPath = "/myRecipeList.jsp";
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

	/**
	 * String 문자열의 값이 null 이나 empty 이면 true 반환
	 * 
	 * @param value
	 * @return
	 */
	private String nvl(String value, String defaultValue) {
		if (value != null && value.length() > 0) {
			return value;
		}
		return defaultValue;
	}
}
