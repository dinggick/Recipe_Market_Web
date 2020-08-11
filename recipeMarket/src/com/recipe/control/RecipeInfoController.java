package com.recipe.control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.exception.FindException;
import com.recipe.service.RecipeService;
import com.recipe.vo.RecipeInfo;

public class RecipeInfoController implements Controller{
	private static RecipeInfoController instance;
	private RecipeService recipeService;
	private static final long serialVersionUID = 1L;
       
    private RecipeInfoController() {
    	recipeService = RecipeService.getInstance(); //싱글턴 객체 획득
    }
    
    public static RecipeInfoController getInstance() {
    	if(instance == null) instance = new RecipeInfoController();
    	return instance;
    }

    /**
     * "/recipeMarket/recipeInfo?recipeCode=레시피코드" 로 요청이 온 경우 실행된다.
     * 레시피 정보를 DB에서 가져오고, 재료 정보와 과정 정보를 가진 텍스트 파일에서 내용을 읽어들여 recipeInfo.jsp로 해당 데이터들을 보내준다.
     * @author CJK
     */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int recipeCode = Integer.parseInt(request.getParameter("recipeCode"));

		try {
			RecipeInfo ri = recipeService.findByCode(recipeCode); //DB에서 레시피 정보를 가져온다
			URL processUrl = new URL(ri.getRecipeProcess()); //해당 레시피의 재료, 과정 정보를 가진 텍스트 파일에 접근하기 위한 URL 설정
			
			BufferedReader br = new BufferedReader(new InputStreamReader(processUrl.openStream()));
			String ingNprocess = "";
			String readVal;
			while((readVal = br.readLine()) != null) ingNprocess += readVal + "\n";
			br.close();
			
			double price = 0;
			String[] splitted = ingNprocess.split("\n");
			String ingredients = splitted[0];
			String[] process = Arrays.copyOfRange(splitted, 2, splitted.length);

			request.setAttribute("recipeInfo", ri);
			request.setAttribute("ingredients", ingredients);
			request.setAttribute("process", process);
			request.setAttribute("price", price);
			
			
			return "/recipeInfo.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";
		}
	}

}
