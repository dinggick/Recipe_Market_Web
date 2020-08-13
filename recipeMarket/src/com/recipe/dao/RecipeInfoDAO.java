package com.recipe.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.recipe.exception.DuplicatedException;
import com.recipe.exception.FindException;
import com.recipe.exception.ModifyException;
import com.recipe.jdbc.MyConnection;
import com.recipe.vo.Ingredient;
import com.recipe.vo.Point;
import com.recipe.vo.RecipeInfo;
import com.recipe.vo.RecipeIngredient;
import com.recipe.vo.RecipePage;

public class RecipeInfoDAO {
   private static final String recipeProessPath = "/files/recipeProcess/";
   
   public RecipeInfo selectByCode(int recipeCode) throws FindException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = MyConnection.getConnection();

      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }
      String selectByCodeSQL = "SELECT RI.RECIPE_CODE, RIN.img_url,RIN.RECIPE_NAME, RIN.RECIPE_SUMM, RIN.RECIPE_PRICE, RI.ing_code, ING.ING_NAME, RIN.recipe_process, PT.LIKE_COUNT, PT.DISLIKE_COUNT\r\n" + 
            "FROM RECIPE_INGREDIENT RI \r\n" + 
            "LEFT JOIN RECIPE_INFO RIN ON RI.recipe_code = RIN.recipe_code\r\n" + 
            "JOIN INGREDIENT ING ON RI.ing_code = ING.ing_code\r\n" + 
            "left JOIN POINT PT ON RI.RECIPE_CODE = PT.RECIPE_CODE\r\n" + 
            "WHERE RIN.recipe_Code IN\r\n" + 
            "(select recipe_code FROM recipe_info WHERE recipe_code = ? AND recipe_status = 1)";
      List<RecipeIngredient> ingList = new ArrayList<>();
      RecipeInfo recipeInfo = new RecipeInfo();
      int prevCode = 0; //
      try {
         pstmt = con.prepareStatement(selectByCodeSQL);
         pstmt.setInt(1, recipeCode);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            int rCode = rs.getInt("recipe_code");
            int ingCode = rs.getInt("ing_code");
            String ingName = rs.getString("ing_name");
            String img_url = rs.getString("img_url");
            Ingredient ingredient = new Ingredient(ingCode, ingName);
            RecipeIngredient recipeIng = new RecipeIngredient(ingredient);
            //코드랑 이름 값 (Ingredient) recipeIng 에 넣어주고 리스트에 애드해줌
            ingList.add(recipeIng);
            //코드값이 바뀔떄 recipeInfo 에 값 넣어주기
            if (prevCode != rCode) {
               recipeInfo.setRecipeCode(rCode);
               recipeInfo.setImgUrl(rs.getString("img_url"));
               recipeInfo.setRecipeName(rs.getString("recipe_name"));
               recipeInfo.setRecipePrice(rs.getInt("recipe_price"));
               recipeInfo.setRecipeSumm(rs.getString("recipe_summ"));
               recipeInfo.setRecipeProcess(rs.getString("recipe_process"));
               recipeInfo.setImgUrl(rs.getString("img_url"));
               recipeInfo.setIngredients(ingList);
               Point pt = new Point(rCode, rs.getInt("like_count"), rs.getInt("dislike_count"));
               recipeInfo.setPoint(pt);
            }
         }
         if (recipeInfo.getRecipeName() == null) {
            throw new FindException("찾은 레시피가 없습니다");
         }

      } catch (SQLException e) {

         e.printStackTrace();
      } finally {
         MyConnection.close(rs, pstmt, con);
      }

      return recipeInfo;
   }
   public List<RecipeInfo> selectByNameAndIngredient(List<String> ingName) throws FindException{
     
	  Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<RecipeInfo> recipeInfo = new ArrayList<>();
      try {
         con = MyConnection.getConnection();

      } catch (ClassNotFoundException | SQLException e) {
         throw new FindException(e.getMessage());
      }
      
      String newSQL =""; 
      //더해질 쿼리
      String selectByIngNameSQL ="SELECT RI.RECIPE_CODE, RIN.img_url,RIN.RECIPE_NAME, RIN.RECIPE_SUMM, RIN.RECIPE_PRICE, RI.ing_code, ING.ING_NAME, RIN.recipe_process, PT.LIKE_COUNT, PT.DISLIKE_COUNT\r\n" + 
            "FROM RECIPE_INGREDIENT RI \r\n" + 
            "LEFT JOIN RECIPE_INFO RIN ON RI.recipe_code = RIN.recipe_code\r\n" + 
            "JOIN INGREDIENT ING ON RI.ing_code = ING.ing_code\r\n" + 
            "LEFT JOIN POINT PT ON RIN.RECIPE_CODE = PT.RECIPE_CODE\r\n" +
            "WHERE RI.recipe_code IN \r\n" + 
            "(select ring.recipe_code FROM Ingredient ig JOIN recipe_ingredient ring on ig.ing_code = ring.ing_code Where ig.ing_name LIKE ?) AND RIN.RECIPE_Status = 1 INTERSECT  ";
      
      for (int i = 1; i < ingName.size(); i++ ) {
         newSQL += selectByIngNameSQL;   
         //리스트 사이즈만큼 돌면서 쿼리가 더해지고, 마지막줄은 INTERSECT 를 서브스트링해 더해짐
         if (i == ingName.size() -1) {
            newSQL += selectByIngNameSQL.substring(0, selectByIngNameSQL.length() -11);
         }         
      }
      if (ingName.size() == 1) {
         newSQL = selectByIngNameSQL.substring(0, selectByIngNameSQL.length() -11);
      }
      
      String recipeName = "";
      for(String s : ingName) {
         recipeName += s;
      }
   
      newSQL += "UNION SELECT RIN.Recipe_code,RIN.img_url,RIN.RECIPE_NAME, RIN.RECIPE_SUMM, RIN.RECIPE_PRICE, RI.ing_code, ING.ING_NAME, RIN.recipe_process,PT.LIKE_COUNT, PT.DISLIKE_COUNT\r\n" + 
            "FROM RECIPE_INGREDIENT RI \r\n" + 
            "LEFT JOIN RECIPE_INFO RIN ON RI.recipe_code = RIN.recipe_code\r\n" + 
            "LEFT JOIN INGREDIENT ING ON RI.ing_code = ING.ing_code\r\n" + 
            "LEFT JOIN POINT PT ON RIN.RECIPE_CODE = PT.RECIPE_CODE\r\n" + 
            "WHERE rin.recipe_name LIKE ? AND RIN.RECIPE_STATUS = 1";
      try {
         
         pstmt = con.prepareStatement(newSQL);
         for (int i = 1; i < ingName.size()+1; i++) {
            pstmt.setString(i, "%" +ingName.get(i-1) + "%");
         }
         pstmt.setString(ingName.size() +1, "%" + recipeName+"%");
         rs = pstmt.executeQuery();
         List<RecipeIngredient> ingList = null;   
         int prevCode = 0;
         while(rs.next()) {   
            
            int rCode = rs.getInt("recipe_code");
            //코드값이 바뀔떄 recipeInfo 객체 생성하고 값넣어줌      
            if (prevCode != rCode) {               
               RecipeInfo recipeInfo2 = new RecipeInfo();
               ingList = new ArrayList<>();
               recipeInfo2.setIngredients(ingList);
               recipeInfo2.setRecipeCode(rCode);
               recipeInfo2.setImgUrl(rs.getString("img_url"));
               recipeInfo2.setRecipeName(rs.getString("recipe_name"));
               recipeInfo2.setRecipePrice(rs.getInt("recipe_price"));
               recipeInfo2.setRecipeSumm(rs.getString("recipe_summ"));
               recipeInfo2.setRecipeProcess(rs.getString("recipe_process"));
               recipeInfo2.setIngredients(ingList);               
               Point pt = new Point(rCode, rs.getInt("like_count"), rs.getInt("dislike_count"));
               recipeInfo2.setPoint(pt);
               recipeInfo.add(recipeInfo2);

               prevCode = rCode;
            }

            int ingCode = rs.getInt("ing_code");
            String ingName2 = rs.getString("ing_name");
            Ingredient ingredient = new Ingredient(ingCode, ingName2);
            RecipeIngredient recipeIng = new RecipeIngredient(ingredient);            
            ingList.add(recipeIng);

         }
//         if (recipeInfo.size() == 0) {
//            throw new FindException("찾은 레시피가 없습니다");
//         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         MyConnection.close(rs, pstmt, con);
      }

      return recipeInfo;
   }
   
   /**
    * 레시피 등록
    * 
    * @param rdEmail
    * @param recipe_InfoVo
    * @param ingInfo
    * @param ingList
    * @param process
    * @throws DuplicatedException
    */
   public void insert(String rdEmail, RecipeInfo recipe_InfoVo, String ingInfo, List<Ingredient> ingList, String process, String rootUploadPath) throws DuplicatedException{
      //입력받아온 recipe_InfoVo,ingList
      Connection con = null; // DB연결된 상태(세션)을 담은 객체
      PreparedStatement pstmt = null;  // SQL 문을 나타내는 객체
      ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체

      // 람다식을 사용하면 가독성있는 소스를 사용할 수 있다!
      // 단점 : 람다식을 모르면 이해하기 어렵다
      String ing_name = ingList.stream()
                           .map(i -> "'" + i.getIngName() + "'")
                           .collect(Collectors.joining(", "));
//      System.out.println("ing_name test : " + ingList.stream().map(i -> "'" + i.getIngName() + "'").collect(Collectors.joining(", ")));
//      for (Ingredient ingredientVO : ingList) {      //ingList에 있는 객체들을 ingredientVO에 넣으면서 반복문 실행
//         ing_name +=", '" + ingredientVO.getIngName() + "'";         //", '재료1', '재료2', '재료3', ....식으로 문자넣음. // 사과1개
//      }
      
      String quary = "SELECT COUNT(1) AS CNT FROM RECIPE_INFO WHERE RECIPE_NAME = ?";      //레시피명이 존재하는것이라면 1이나옴. 없다면 0.
      try {
         con = MyConnection.getConnection();
         pstmt = con.prepareStatement(quary);
         pstmt.setString(1, recipe_InfoVo.getRecipeName());

         rs = pstmt.executeQuery();

         // 첫번째 데이터 전인지? = 데이터가 있는지? 
//         if (rs.isBeforeFirst()) {
//            throw new DuplicatedException("이미 존재하는 레시피입니다.");
//         }
         int countFlag = 0;
         while (rs.next()) {      //쿼리문을 돌렸을때 받아온 컬럼의 값이 있을때 true
            countFlag = rs.getInt(1);      //있다면 1을 countFlag에 넣는다.
         }
         if (0 < countFlag) {
            throw new DuplicatedException("이미 존재하는 레시피입니다.");
         }
         rs.close();

         for (Ingredient ingredientVO : ingList) {      //ingList에 입력받아놨던 재료명(ing_name)을 하나씩 검사하면서 테이블에 재료명이 있으면 무시하고 없으면 생성하는 부분
            quary = "MERGE INTO INGREDIENT " + 
                  "USING DUAL " + 
                  "   ON (ING_NAME = ?) " + 
                  "WHEN NOT MATCHED THEN " + 
                  "    INSERT (ING_CODE, ING_NAME) " + 
                  "VALUES ((SELECT MAX(ING_CODE) + 1 FROM INGREDIENT), ?)";
            pstmt = con.prepareStatement(quary);
            pstmt.setString(1, ingredientVO.getIngName());
            pstmt.setString(2, ingredientVO.getIngName());
            pstmt.executeUpdate();
            pstmt.close();
         }

         List<Ingredient> ing_codeList = new ArrayList<Ingredient>();
         Ingredient ingredientVo = new Ingredient();

         //,빼고 ING_CODE와 ING_NAME값을 셀렉트 해오는 쿼리
         quary = "SELECT ING_CODE, ING_NAME FROM INGREDIENT WHERE ING_NAME IN ( "
               + ing_name + ")";   //", '재료1', '재료2', '재료3' "식으로 문자넣음.

         pstmt = con.prepareStatement(quary);
         rs = pstmt.executeQuery();      //쿼리 실행시 재료코드값과 재료이름을 받아옴

         while (rs.next()) {
            ingredientVo = new Ingredient();
            ingredientVo.setIngCode(rs.getInt(1));      //첫번째 값은 재료코드값으로
            ingredientVo.setIngName(rs.getString(2));      //두번째값은 재료이름값으로
            ing_codeList.add(ingredientVo);      //인덱스 하나하나 ing_codeList에 넣어준다.
         }
         rs.close();
         pstmt.close();

         quary = "SELECT RECIPE_CODE_SEQ.NEXTVAL FROM DUAL";      //레시피코드의시퀀스넘버 값을 구해온다.
         pstmt = con.prepareStatement(quary);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            recipe_InfoVo.setRecipeCode(rs.getInt(1));//setRecipe_code메소드를 이용해서 recipe_InfoVo의 Recipe_code에 넣어준다
         }

         //DB에 들어갈 이미지파일명 바꾸기
         String imageUrl = "http://localhost/files/img/";
         String savePath = rootUploadPath + File.separator + "files" + File.separator + "img" + File.separator;
         String oldImageUrl = recipe_InfoVo.getImgUrl();   //"http://localhost/files/img/어피치찜.png"   
         int recipeCode = recipe_InfoVo.getRecipeCode();   //레시피코드값 넣기

         String oldFileName = oldImageUrl.substring(oldImageUrl.lastIndexOf("/") + 1);   //"어피치찜.png"으로 자름
         String newFileName = recipeCode + oldFileName.substring(oldFileName.lastIndexOf("."));   //"레시피명.png"로 확장자명 살리기 
         recipe_InfoVo.setImgUrl(imageUrl + newFileName);   //"http://localhost/files/img/" + "레시피명.png"로 변경해서 넣음
         
         File oldFile = new File(savePath + oldFileName);
         File newFile = new File(savePath + newFileName);
         
         // 새로운 파일이 존재하는지 확인하여 있으면 파일 DELETE 후 renameTo 실행
         if (newFile.exists()) {
            if (newFile.delete()) {
               System.out.println("new File Path 삭제 성공");
            } else {
               System.out.println("new File Path 삭제 실패");
            }
         }
         // 파일명 변경
         oldFile.renameTo(newFile);
         
         // recipe Process 컬럼의 존재이유를 모르겠다.
         // img_url 별도의 다른 도메인을 사용하거나,아니면 등록 자체에 외부 url 을 등록하는게 아니라면 예) 네이버
         // 도메인까지 DB 에 넣는건 이후에 도메인 확장성을 생각하면 불필요하다고 생각한다.
         // 변동이 거의 없는 고정경로를 변수선언해서 관리하는게 이후에 유지보수성이 좋다.
         recipe_InfoVo.setRecipeProcess("http://localhost" + recipeProessPath + recipe_InfoVo.getRecipeCode() + ".txt");      //recipeprocess에 레시피코드를 파일명으로 한 파일생성경로를 넣어준다.
         //Win전용 Process경로
//         ("http://localhost/files/recipeProcess/")
         //Win전용 Img경로
//         ("http://localhost/files/img/7.png")
         
         //Mac전용 Process경로
//         recipe_InfoVo.setRecipeProcess("/usr/local/apache-tomcat-9.0.36/webapps/ROOT/files/recipeProcess" + recipe_InfoVo.getRecipeCode() + ".txt");      //recipeprocess에 레시피코드를 파일명으로 한 파일생성경로를 넣어준다.
         //Mac전용 Img경로
//         ("/usr/local/apache-tomcat-9.0.36/webapps/ROOT/files/img")
         rs.close();
         pstmt.close();
         quary = "INSERT INTO RECIPE_INFO VALUES(?, ?, ?, ?, ?, ?, ?, ?)";      //RECIPE_INFO 에 값들을 넣어주는 쿼리문
         pstmt = con.prepareStatement(quary);
         pstmt.setInt(1, recipe_InfoVo.getRecipeCode());
         pstmt.setString(2, recipe_InfoVo.getRecipeName());
         pstmt.setString(3, recipe_InfoVo.getRecipeSumm());
         pstmt.setDouble(4, recipe_InfoVo.getRecipePrice());
         pstmt.setString(5, recipe_InfoVo.getRecipeProcess());
         pstmt.setString(6, recipe_InfoVo.getImgUrl());
         pstmt.setString(7, rdEmail);
         
         pstmt.setString(8, ("1"));      //status는 1로 고정

         pstmt.executeUpdate();
         pstmt.close();

         fileOutput(recipe_InfoVo.getRecipeProcess().replace("http://localhost", rootUploadPath), ingInfo + "\n" + process);
         
         quary = "INSERT INTO POINT VALUES(?, 0, 0)";      //좋아요싫어요 초기값설정해주는 쿼리문
         pstmt = con.prepareStatement(quary);
         pstmt.setInt(1, recipe_InfoVo.getRecipeCode());

         pstmt.executeUpdate();
         pstmt.close();

         for (Ingredient ingredientVO : ing_codeList) {         //ing_codeList에 있는것들을 ingredientVO에 넣으면서 반복문 돌림.
            quary = "INSERT INTO RECIPE_INGREDIENT VALUES (?, ?)";      //리세피코드, 재료코드, 용량 insert 해주는 쿼리
            pstmt = con.prepareStatement(quary);
            pstmt.setInt(1, recipe_InfoVo.getRecipeCode());
            pstmt.setInt(2,  ingredientVO.getIngCode());
            pstmt.executeUpdate();
            pstmt.close();
         }

      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      } finally {
         // DB 연결을 종료한다.
         try {
            MyConnection.close(rs, pstmt, con);
         } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
         }
      }
   }
   
   /**
    * 레시피 수정
    * 
    * @param rdEmail
    * @param recipe_InfoVo
    * @param ingInfo
    * @param ingList
    * @param process
    * @throws ModifyException
    */
   public void update(String rdEmail, RecipeInfo recipe_InfoVo,String ingInfo ,List<Ingredient> ingList, String process, String rootUploadPath) throws ModifyException {
      //입력받아온 recipe_InfoVo,ingList
      Connection con = null; // DB연결된 상태(세션)을 담은 객체
      PreparedStatement pstmt = null;  // SQL 문을 나타내는 객체
      ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체

      // 람다식을 사용하면 가독성있는 소스를 사용할 수 있다!
      // 단점 : 람다식을 모르면 이해하기 어렵다
      String ing_name = ingList.stream()
                           .map(i -> "'" + i.getIngName() + "'")
                           .collect(Collectors.joining(", "));
      //-----------------------ingList에서 IngName,IngCpcty를 나눠줘야 함.
//      String ing_name = "";
//      for (Ingredient ingredientVO : ingList) {      //ingList에 있는 객체들을 ingredientVO에 넣으면서 반복문 실행
//         ing_name +=", '" + ingredientVO.getIngName() + "'";         //", '재료1', '재료2', '재료3', ....식으로 문자넣음. // 사과1개
//      }
      //-----------------------
      String quary = "SELECT COUNT(1) AS CNT FROM RECIPE_INFO WHERE RECIPE_NAME = ?";      //레시피명이 존재하는것이라면 1이나옴. 없다면 0.
      try {
         con = MyConnection.getConnection();
         pstmt = con.prepareStatement(quary);
         pstmt.setString(1, recipe_InfoVo.getRecipeName());

         rs = pstmt.executeQuery();

//         if (rs.isBeforeFirst()) {
//            throw new ModifyException("해당 레시피 이름이 이미 존재합니다");
//         }
         int countFlag = 0;
         while (rs.next()) {      //쿼리문을 돌렸을때 받아온 컬럼의 값이 있을때 true
            countFlag = rs.getInt(1);      //있다면 1을 countFlag에 넣는다.
         }
         if (0 < countFlag) {
            throw new ModifyException("해당 레시피 이름이 이미 존재합니다");
         }
         rs.close();
         pstmt.close();

         quary = "SELECT RD_EMAIL FROM RECIPE_INFO WHERE RECIPE_CODE = ?";
         pstmt = con.prepareStatement(quary);
         pstmt.setInt(1, recipe_InfoVo.getRecipeCode());

         rs = pstmt.executeQuery();
         if (rs.next()) {
            String selectedRdEmail = rs.getString(1);
            if (!selectedRdEmail.equals(rdEmail)) {
               throw new ModifyException("이 레시피의 작성자가 아닙니다"); 
            }
         }
         pstmt.close();

         //레시피코드로 연결된 RECIPE_INGREDIENT테이블의 재료코드값을 삭제한다.
         quary = "DELETE FROM RECIPE_INGREDIENT WHERE RECIPE_CODE = ?";
         pstmt = con.prepareStatement(quary);
         pstmt.setInt(1, recipe_InfoVo.getRecipeCode());

         pstmt.executeUpdate();
         pstmt.close();

         for (Ingredient ingredientVO : ingList) {      //ingList에 입력받아놨던 재료명(ing_name)을 하나씩 검사하면서 테이블에 재료명이 있으면 무시하고 없으면 생성하는 부분
            quary = "MERGE INTO INGREDIENT " + 
                  "USING DUAL " + 
                  "   ON (ING_NAME = ?) " + 
                  "WHEN NOT MATCHED THEN " + 
                  "    INSERT (ING_CODE, ING_NAME) " + 
                  "VALUES ((SELECT MAX(ING_CODE) + 1 FROM INGREDIENT), ?)";
            pstmt = con.prepareStatement(quary);
            pstmt.setString(1, ingredientVO.getIngName());
            pstmt.setString(2, ingredientVO.getIngName());
            pstmt.executeUpdate();
            pstmt.close();
         }

         //DB에 들어갈 이미지파일명 바꾸기
         String imageUrl = "http://localhost/files/img/";
         String savePath = rootUploadPath + File.separator + "files" + File.separator + "img" + File.separator;
         String oldImageUrl = recipe_InfoVo.getImgUrl();   //"http://localhost/files/img/어피치찜.png"   
         int recipeCode = recipe_InfoVo.getRecipeCode();   //레시피코드값 넣기

         String oldFileName = oldImageUrl.substring(oldImageUrl.lastIndexOf("/") + 1);   //"어피치찜.png"으로 자름
         String newFileName = recipeCode + oldFileName.substring(oldFileName.lastIndexOf("."));   //"레시피명.png"로 확장자명 살리기 
         recipe_InfoVo.setImgUrl(imageUrl + newFileName);   //"http://localhost/files/img/" + "레시피명.png"로 변경해서 넣음
         
         File oldFile = new File(savePath + oldFileName);
         File newFile = new File(savePath + newFileName);
         
         // 새로운 파일이 존재하는지 확인하여 있으면 파일 DELETE 후 renameTo 실행
         if (newFile.exists()) {
            if (newFile.delete()) {
               System.out.println("new File Path 삭제 성공");
            } else {
               System.out.println("new File Path 삭제 실패");
            }
         }
         // 파일명 변경
         oldFile.renameTo(newFile);
         
         List<Ingredient> ing_codeList = new ArrayList<Ingredient>();
         Ingredient ingredientVo = new Ingredient();

         //,빼고 ING_CODE와 ING_NAME값을 셀렉트 해오는 쿼리
         quary = "SELECT ING_CODE, ING_NAME FROM INGREDIENT WHERE ING_NAME IN ( "
               + ing_name + ")";   //", '재료1', '재료2', '재료3' "식으로 문자넣음.

         pstmt = con.prepareStatement(quary);
         rs = pstmt.executeQuery();      //쿼리 실행시 재료코드값과 재료이름을 받아옴

         while (rs.next()) {
            ingredientVo = new Ingredient();
            ingredientVo.setIngCode(rs.getInt(1));      //첫번째 값은 재료코드값으로
            ingredientVo.setIngName(rs.getString(2));      //두번째값은 재료이름값으로
            ing_codeList.add(ingredientVo);      //인덱스 하나하나 ing_codeList에 넣어준다.
         }
         rs.close();
         pstmt.close();

         quary = "UPDATE RECIPE_INFO SET RECIPE_NAME=?, RECIPE_SUMM=?, RECIPE_PRICE=?, IMG_URL=? WHERE RECIPE_CODE=?";      //RECIPE_INFO 에 값들을 넣어주는 쿼리문
         pstmt = con.prepareStatement(quary);

         pstmt.setString(1, recipe_InfoVo.getRecipeName());
         pstmt.setString(2, recipe_InfoVo.getRecipeSumm());
         pstmt.setDouble(3, recipe_InfoVo.getRecipePrice());
         pstmt.setString(4, recipe_InfoVo.getImgUrl());
         pstmt.setInt(5, recipe_InfoVo.getRecipeCode());

         pstmt.executeUpdate();
         pstmt.close();

         // 파일 업로드
         // insert 할때는 localhost 도메인 set 하고 replace 로 rootUploadPath 설정했는데,
//         recipe_InfoVo.setRecipeProcess("http://localhost" + recipeProessPath + recipe_InfoVo.getRecipeCode() + ".txt");   
//         fileOutput(recipe_InfoVo.getRecipeProcess().replace("http://localhost", rootUploadPath), ingInfo + "\n" + process);
         
         // update 할때는 localhost 도메인 set 할 필요가 없어서 rootUploadPath 로 바로 설정함
         fileOutput(rootUploadPath + recipeProessPath + recipe_InfoVo.getRecipeCode() + ".txt", ingInfo + "\n" + process);
         
//-------------------------------------------------------------------------------------------------------------------------------------         
//이미지수정시 파일경로 재추가 어떻게할지?---------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------
         for (Ingredient ingredientVO : ing_codeList) {         //ing_codeList에 있는것들을 ingredientVO에 넣으면서 반복문 돌림.
            quary = "INSERT INTO RECIPE_INGREDIENT VALUES (?, ?)";      //리세피코드, 재료코드, 용량 insert 해주는 쿼리
            pstmt = con.prepareStatement(quary);
            pstmt.setInt(1, recipe_InfoVo.getRecipeCode());
            pstmt.setInt(2,  ingredientVO.getIngCode());
            pstmt.executeUpdate();
            pstmt.close();
         }

      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            MyConnection.close(rs, pstmt, con);
         } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
         }
      }
   }
   
   public void remove(String rdEmail, RecipeInfo recipeInfo) throws ModifyException {
      Connection con = null; // DB연결된 상태(세션)을 담은 객체
      PreparedStatement pstmt = null;  // SQL 문을 나타내는 객체
      ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체

      //선택한 레시피의 rd_id값이 로그인한 rd_id값과 같은지 확인
      String quary = "SELECT RD_EMAIL FROM RECIPE_INFO WHERE RECIPE_CODE = ?";
      try {
         con = MyConnection.getConnection();
         pstmt = con.prepareStatement(quary);
         pstmt.setInt(1, recipeInfo.getRecipeCode());

         rs = pstmt.executeQuery();
         if(rs.next()) {
            String selectedRdEmail = rs.getString("rd_Email");
            if(!selectedRdEmail.equals(rdEmail)) {
               throw new ModifyException("이 레시피의 작성자가 아닙니다"); 
            }
         }
         pstmt.close();

         //레시피코드를 기준으로 레시피 활성화여부값을 0으로 수정
         quary ="UPDATE RECIPE_INFO SET RECIPE_STATUS = '0' WHERE RECIPE_CODE = ?";

         pstmt = con.prepareStatement(quary);
         pstmt.setInt(1, recipeInfo.getRecipeCode());

         pstmt.executeUpdate();
         pstmt.close();
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }finally{
         try{
            MyConnection.close(rs, pstmt, con);
         }catch(Exception e){
            throw new RuntimeException(e.getMessage());
         }
      }
   }
   public List<RecipeInfo> selectAll() throws FindException {
      Connection con = null; // DB연결된 상태(세션)을 담은 객체
      PreparedStatement pstmt = null;  // SQL 문을 나타내는 객체
      ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체


      List<RecipeInfo> recipeInfoList = new ArrayList<>();

      String quary = "SELECT i.recipe_code, i.recipe_name, i.recipe_summ, i.recipe_price, i.recipe_process, i.img_url, p.like_count, p.dislike_count FROM recipe_info i JOIN POINT p ON i.recipe_code = p.recipe_code where i.recipe_status=1";

      try {
         con = MyConnection.getConnection();
         pstmt = con.prepareStatement(quary);

         rs = pstmt.executeQuery();

         while(rs.next()) {
            RecipeInfo recipeInfo = new RecipeInfo();
            Point point = new Point();
            recipeInfo.setRecipeCode(rs.getInt(1));      //첫번째 값은 레시피코드값으로
            recipeInfo.setRecipeName(rs.getString(2));      //두번째값은 레시피이름값으로
            recipeInfo.setRecipeSumm(rs.getString(3));      //세번째값은 레시피요약값으로
            recipeInfo.setRecipePrice(rs.getInt(4));      //네번째값은 레시피가격값으로
            recipeInfo.setRecipeProcess(rs.getString(5));      //다섯번째값은 경로값으로
            recipeInfo.setImgUrl(rs.getString(6));
            point.setLikeCount(rs.getInt(7));      //여섯번째값은 좋아요값으로
            point.setDisLikeCount(rs.getInt(8));      //일곱번째값은 싫어요값으로
            recipeInfo.setPoint(point);               //좋아요,싫어요 값을 Point에 넣는다.

            recipeInfoList.add(recipeInfo);      //인덱스 하나하나 recipeInfoList에 넣어준다.
         }
         if(recipeInfoList.isEmpty()) {
            throw new FindException("레시피가 하나도 없습니다");
         }
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }finally{
         try{
            MyConnection.close(rs, pstmt, con);
         }catch(Exception e){
            throw new RuntimeException(e.getMessage());
         }
      }
      return recipeInfoList;
   }
   
   /**
    * 나의 레시피리스트 검색
    * 
    * @param rdEmail
    * @return
    * @throws FindException
    */
   public List<RecipeInfo> myRecipeList(String rdEmail) throws FindException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = MyConnection.getConnection();
      } catch (ClassNotFoundException | SQLException e) {
      }
      
      String quary = "SELECT i.recipe_code, i.recipe_name, i.recipe_summ, i.recipe_price, i.recipe_process,"
            + "i.img_url, p.like_count, p.dislike_count "
            + "FROM recipe_info i JOIN POINT p ON i.recipe_code = p.recipe_code "
            + "WHERE i.rd_email = ? AND i.recipe_status = 1";
      
      List<RecipeInfo> result = new ArrayList<RecipeInfo>();
      try {
         pstmt = con.prepareStatement(quary);
         pstmt.setString(1, rdEmail);
         rs = pstmt.executeQuery();
         while (rs.next()) { 
            result.add(new RecipeInfo(rs.getInt("recipe_code"), rs.getString("recipe_name"), rs.getString("recipe_summ"), rs.getInt("recipe_price"), rs.getString("recipe_process"), rs.getString("img_url"), new Point(rs.getInt("recipe_code"), rs.getInt("like_count"), rs.getInt("dislike_count")), null));
         }
         if (result.isEmpty()) {
            throw new FindException("등록된 레시피가 없습니다");
         }
      } catch (SQLException e) {

         e.printStackTrace();
      } finally {
         MyConnection.close(rs, pstmt, con);
      }
      return result;
   }

   /**
    * 나의 레시피리스트 페이징
    * 
    * @param rdEmail
    * @param pageNo
    * @param pageSize
    * @param orderType (판매량 : P, 총 매출액 : T)
    * @return
    * @throws FindException
    */
   public List<RecipePage> myRecipeListPage(String rdEmail, int pageNo, int pageSize, String orderType) throws FindException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = MyConnection.getConnection();
      } catch (ClassNotFoundException | SQLException e) {
      }
      
      // 정렬 타입에 따라 정렬 쿼리 변경
      String orderQuery = "ORDER BY A.PURCHASE_QUANTITY DESC, A.TOTAL_AMOUNT DESC, A.LIKE_COUNT DESC";
      if (orderType.equals("T")) {
         orderQuery = "ORDER BY A.TOTAL_AMOUNT DESC, A.PURCHASE_QUANTITY DESC, A.LIKE_COUNT DESC";
      }
      
      String quary = "SELECT B.* FROM ( "
            + "   SELECT ROW_NUMBER() OVER (" + orderQuery +  ") NUM, A.* FROM ( "
            + "      SELECT i.RECIPE_CODE, i.RECIPE_NAME, i.RECIPE_PRICE, p.LIKE_COUNT, NVL(SUM(d.PURCHASE_QUANTITY), 0) AS PURCHASE_QUANTITY, NVL(i.RECIPE_PRICE * SUM(d.PURCHASE_QUANTITY), 0) AS TOTAL_AMOUNT "
            + "      FROM RECIPE_INFO i "
            + "      LEFT JOIN POINT p ON i.recipe_code = p.recipe_code "
            + "      LEFT JOIN PURCHASE_DETAIL d ON i.RECIPE_CODE = d.RECIPE_CODE "
            + "      WHERE i.RD_EMAIL = ? AND i.RECIPE_STATUS = 1 "
            + "      GROUP BY i.RECIPE_CODE, i.RECIPE_NAME, i.RECIPE_PRICE, p.LIKE_COUNT "
            + "   ) A "
            + ") B WHERE NUM BETWEEN ? AND ? ";
      
      List<RecipePage> result = new ArrayList<RecipePage>();
      try {
         pstmt = con.prepareStatement(quary);
         pstmt.setString(1, rdEmail);
         pstmt.setInt(2, ((pageNo - 1) * pageSize + 1));
         pstmt.setInt(3, pageNo * pageSize);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            RecipePage recipePageData = new RecipePage();
            recipePageData.setNum(rs.getInt("NUM"));
            recipePageData.setRecipeCode(rs.getInt("RECIPE_CODE"));
            recipePageData.setRecipeName(rs.getString("RECIPE_NAME"));
            recipePageData.setRecipePrice(rs.getInt("RECIPE_PRICE"));
            recipePageData.setLikeCount(rs.getInt("LIKE_COUNT"));
            recipePageData.setPurchaseQuantity(rs.getInt("PURCHASE_QUANTITY"));
            recipePageData.setTotalAmount(rs.getInt("TOTAL_AMOUNT"));
            result.add(recipePageData);
         }
         if (result.isEmpty()) {
            throw new FindException("등록된 레시피가 없습니다");
         }
      } catch (SQLException e) {

         e.printStackTrace();
      } finally {
         MyConnection.close(rs, pstmt, con);
      }
      return result;
   }

   /**
    * 나의 레시피리스트 수
    * 
    * @param rdEmail
    * @return
    * @throws FindException
    */
   public int myRecipeCount(String rdEmail) throws FindException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = MyConnection.getConnection();
      } catch (ClassNotFoundException | SQLException e) {
      }
      
      String quary = "SELECT COUNT(1) "
            + "FROM RECIPE_INFO "  
            + "WHERE RD_EMAIL = ? AND RECIPE_STATUS = 1 ";

      int count = 0;
      try {
         pstmt = con.prepareStatement(quary);
         pstmt.setString(1, rdEmail);
         rs = pstmt.executeQuery();
         
         while (rs.next()) {      //쿼리문을 돌렸을때 받아온 컬럼의 값이 있을때 true
            count = rs.getInt(1);      //있다면 1을 countFlag에 넣는다.
         }
//         if (count < 1) {
//            throw new FindException("등록된 레시피가 없습니다");
//         }
      } catch (SQLException e) {

         e.printStackTrace();
      } finally {
         MyConnection.close(rs, pstmt, con);
      }
      return count;
   }
   /**
    * 좋아요 개수(내림차순), 싫어요 개수(오름차순), 작성된 후기 개수(내림차순)를 기준으로 추천 레시피를 선정하여 반환한다 
    * @return 추천 레시피 정보를 포함한 RecipeInfo 객체
    * @throws FindException
    */
   public List<RecipeInfo> selectByRank() throws FindException {
      List<RecipeInfo> result = new ArrayList<RecipeInfo>();
      
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         con = MyConnection.getConnection();
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
         throw new FindException(e.getMessage());
      }

      String selectByRankSQL = "SELECT\r\n" + 
            "    *\r\n" + 
            "FROM\r\n" + 
            "    (\r\n" + 
            "        SELECT\r\n" + 
            "            p.recipe_code       recipe_code,\r\n" + 
            "            p.like_count        like_count,\r\n" + 
            "            p.dislike_count     dislike_count,\r\n" + 
            "            i.recipe_name       recipe_name,\r\n" + 
            "            i.recipe_summ       recipe_summ,\r\n" + 
            "            i.recipe_price      recipe_price,\r\n" + 
            "            i.recipe_process    recipe_process,\r\n" + 
            "            i.img_url           img_url,\r\n" + 
            "            i.rd_email          rd_email\r\n" + 
            "        FROM\r\n" + 
            "                 point p\r\n" + 
            "            JOIN recipe_info i ON ( p.recipe_code = i.recipe_code )\r\n" + 
            "        WHERE\r\n" + 
            "            i.recipe_status = '1'\r\n" + 
            "        ORDER BY\r\n" + 
            "            like_count DESC,\r\n" + 
            "            dislike_count ASC,\r\n" + 
            "            (\r\n" + 
            "                SELECT\r\n" + 
            "                    COUNT(*)\r\n" + 
            "                FROM\r\n" + 
            "                    review\r\n" + 
            "                WHERE\r\n" + 
            "                    i.recipe_code = p.recipe_code\r\n" + 
            "            ) DESC\r\n" + 
            "    )\r\n" + 
            "WHERE\r\n" + 
            "    ROWNUM BETWEEN 1 AND 10";

      try {
         pstmt = con.prepareStatement(selectByRankSQL);
         rs = pstmt.executeQuery();

         while(rs.next()) result.add(new RecipeInfo(rs.getInt("recipe_code"), rs.getString("recipe_name"), rs.getString("recipe_summ"), rs.getInt("recipe_price"), rs.getString("recipe_process"), rs.getString("img_url"), new Point(rs.getInt("recipe_code"), rs.getInt("like_count"), rs.getInt("dislike_count")), null));
         //if(rs.next()) return new RecipeInfo(rs.getInt("recipe_code"), rs.getString("recipe_name"), rs.getString("recipe_summ"), rs.getDouble("recipe_price"), rs.getString("recipe_process"), new Point(rs.getInt("recipe_code"), rs.getInt("like_count"), rs.getInt("dislike_count")), null);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      if(result.isEmpty()) throw new FindException("추천 레시피 탐색 오류");

      return result;
   }
   
   private boolean fileOutput(String fileFullPath, String message) {
      try {
         System.out.println("fileOutput > fileFullPath : " + fileFullPath);
         @SuppressWarnings("resource")
         OutputStream output = new FileOutputStream(fileFullPath);      //파일경로명을  output에 넣는다.
         String str = message;
         byte[] by=str.getBytes();         //메시지들을 바이트배열에 넣는다
         output.write(by);         //그것들을 쓴다.

      } catch (Exception e) {
         System.out.println("FileOutput Failure");
         e.printStackTrace();
         return false;
      }
      return true;
   }
   public static void main(String[] args) {
      RecipeInfoDAO dao = new RecipeInfoDAO();
      List<String> ingName = new ArrayList<>();
      ingName.add("김치");
      ingName.add("참치");
      try {
         List<RecipeInfo> infos = dao.selectByNameAndIngredient(ingName);
         for (RecipeInfo i : infos) {
            System.out.println(i.getRecipeName());
         }
         
      } catch (FindException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
//      
//      try {
//         RecipeInfo info = dao.selectByCode(code);
//         
//         System.out.println(info.getRecipeName());
//      } catch (FindException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//      
   }

}