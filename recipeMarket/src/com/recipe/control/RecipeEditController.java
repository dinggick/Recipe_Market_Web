package com.recipe.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.recipe.control.Controller;
import com.recipe.service.RecipeService;

public class RecipeEditController implements Controller {
	private static RecipeEditController instance;
	private RecipeService service;
	private static final long serialVersionUID = 1L;

	private RecipeEditController() {
		service = RecipeService.getInstance();
	}

	public static RecipeEditController getInstance() {
		if (instance == null) instance = new RecipeEditController();
		return instance;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("레시피 ajax 등록 or 수정");
		
		try {
			// 업로드 하는법
			// 한글 깨짐
			MultipartRequest mRequest = new MultipartRequest(request, "/usr/local/apache-tomcat-9.0.36/webapps/ROOT/files/img");
			String originalFileName = mRequest.getOriginalFileName("imageUpload");
			String recipeCode = mRequest.getParameter("recipeCode");
			String recipeName = mRequest.getParameter("recipeName");
			String[] ingName = mRequest.getParameterValues("ingredientsName");
			System.out.println("--------- recipeCode : " + recipeCode);
			System.out.println("--------- recipeName : " + recipeName);
			System.out.println("--------- ingName : " + ingName);
			
			if (ingName != null && ingName.length > 0) {
				for (int i=0; i<ingName.length; i++) {
					System.out.println("ingName[" + i + "] : " + ingName[i]);
				}				
			}
			
			System.out.println("--------- originalFileName : " + originalFileName);
			
			
			if (isNullOrEmpty(recipeCode)) {
				System.out.println("등록");
				//service.addRecipe(rdId, recipeInfo, ingInfo, ingList, process);
			} else {
				System.out.println("수정");
				//service.modifyRecipe(rdId, recipeInfo, ingInfo, ingList, process);
			}
			
			// 등록 or 수정 성공시 success 내려
			return "/success.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage().replace("\"", ""));
			return "/fail.jsp";
		}
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
	
	public static String getBody(HttpServletRequest request) throws IOException {
		 
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
 
        body = stringBuilder.toString();
        return body;
    }

}
