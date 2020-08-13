package com.recipe.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.recipe.service.RecipeService;
import com.recipe.vo.Ingredient;
import com.recipe.vo.Point;
import com.recipe.vo.RecipeInfo;

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
      try {
         // 업로드 하는법
         // 한글 깨짐
         HttpSession session = request.getSession();
         String rdEmail = (String)session.getAttribute("loginInfo");
         String imageUrl = "http://localhost/files/img/";
         String rootUploadPath = request.getSession().getServletContext().getRealPath("/").replace("wtpwebapps" + File.separator + "recipeMarket" + File.separator, "webapps" + File.separator + "ROOT");
         String imageUploadPath = rootUploadPath + File.separator + "files" + File.separator + "img" + File.separator;

         // 파일 업로드 상태
         MultipartRequest mRequest = new MultipartRequest(request, imageUploadPath, "utf-8");
         
         /* recipeInfo */
         String recipeCode = mRequest.getParameter("recipeCode");
         String recipeName = mRequest.getParameter("recipeName");
         String[] ingName = mRequest.getParameter("ingName").split(",");
         String[] ingSize = mRequest.getParameter("ingSize").split(",");
         
         String recipeSumm = mRequest.getParameter("recipeSumm");
         int recipePrice = Integer.parseInt(mRequest.getParameter("recipePrice"));
         String[] recipeProcess = mRequest.getParameter("recipeProcess").split(",");
         String originalFileName = mRequest.getOriginalFileName("recipeImage");
         
         String process = ""; 
         Point point = new Point();
         String ingInfo = "";
         List<Ingredient> ingList = new ArrayList<Ingredient>();
         RecipeInfo recipeInfoVo = new RecipeInfo();
         recipeInfoVo.setRecipeName(recipeName);
         
         // 재료 & 수량 Setting
         for (int cnt = 0 ; cnt < ingName.length ; cnt++) {
            Ingredient ingredientVo = new Ingredient();
            ingredientVo.setIngName(ingName[cnt]);
            
            ingInfo += ingName[cnt];      //재료명[0]번째 값을 ingInfo문자열에 추가.
            ingInfo += " " + ingSize[cnt] + " ";   //재료사이즈[0]번째 값을 ingInfo문자열에 추가.
            
            ingList.add(ingredientVo);
         }
         
         recipeInfoVo.setPoint(point);
         recipeInfoVo.setRecipeSumm(recipeSumm);
         recipeInfoVo.setRecipePrice(recipePrice);
         
         for ( int cnt = 0 ; cnt < recipeProcess.length ; cnt++ ) {
            process += "\r\n" + recipeProcess[cnt];
         }

         recipeInfoVo.setRecipeProcess(process);
         recipeInfoVo.setImgUrl(imageUrl + originalFileName);
         
         if (isNullOrEmpty(recipeCode)) {
            service.addRecipe(rdEmail, recipeInfoVo, ingInfo, ingList, process, rootUploadPath);
         } else {
            recipeInfoVo.setRecipeCode(Integer.parseInt(recipeCode));
            service.modifyRecipe(rdEmail, recipeInfoVo, ingInfo, ingList, process, rootUploadPath);
         }
         
         // 등록 or 수정 성공시 success 내려
         return "/success.jsp";
      } catch (Exception e) { 
         e.printStackTrace();
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