package com.recipe.service;

import java.util.List;

import com.recipe.dao.PointDAO;
import com.recipe.dao.RecipeInfoDAO;
import com.recipe.dao.RecipeIngredientDAO;
import com.recipe.exception.DuplicatedException;
import com.recipe.exception.FindException;
import com.recipe.exception.ModifyException;
import com.recipe.vo.Ingredient;
import com.recipe.vo.Point;
import com.recipe.vo.RecipeInfo;

public class RecipeService {
	private static RecipeService instance;
	private RecipeInfoDAO recipeInfoDAO;	
	private RecipeIngredientDAO recipeIngredientDAO;
	private PointDAO pointDAO;
	
	private RecipeService() {
		recipeInfoDAO = new RecipeInfoDAO();
		recipeIngredientDAO = new RecipeIngredientDAO();
		pointDAO = new PointDAO();
	}
	
	public static RecipeService getInstance() {
		if(instance == null) instance = new RecipeService();
		return instance;
	}
	
	public List<RecipeInfo> findRecipe(List<String> ingName) throws FindException{
		return recipeInfoDAO.selectByNameAndIngredient(ingName);		
	}
	public RecipeInfo findByCode(int recipeCode) throws FindException {
		return recipeInfoDAO.selectByCode(recipeCode);
	}
	public List<RecipeInfo> findByIngName(List<String> ingName) throws FindException{
		return recipeIngredientDAO.selectByIngName(ingName);
	}
	/**
	 * 추천 레시피 절차를 위한 메소드
	 * @return 추천 레시피 정보를 가진 RecipeInfo
	 * @throws FindException
	 * @author 최종국
	 */
	public List<RecipeInfo> findRecommended() throws FindException {
		return recipeInfoDAO.selectByRank();
	}
	public void addRecipe(String rdEmail, RecipeInfo recipeInfo, String ingInfo, List<Ingredient> ingList, String process, String rootUploadPath) throws DuplicatedException {
		recipeInfoDAO.insert(rdEmail, recipeInfo, ingInfo, ingList, process, rootUploadPath);
	}
	public void modifyRecipe(String rdEmail, RecipeInfo recipeInfo, String ingInfo, List<Ingredient> ingList, String process, String rootUploadPath) throws ModifyException {
		recipeInfoDAO.update(rdEmail, recipeInfo, ingInfo, ingList, process, rootUploadPath);
	}
	public void removeRecipe(String rdEmail, RecipeInfo recipeInfo) throws ModifyException {
		recipeInfoDAO.remove(rdEmail, recipeInfo);
	}
	public List<RecipeInfo> myRecipeList(String rdEmail) throws FindException {
		return recipeInfoDAO.myRecipeList(rdEmail);
	}
	public List<RecipeInfo> findAll() throws FindException {
		return recipeInfoDAO.selectAll();
	}
	/**
	 * 포인트 수정 절차(좋아요)를 위한 메소드
	 * @param 포인트 수정 절차(좋아요)를 진행할 레시피 코드
	 * @throws ModifyException
	 * @author 최종국
	 */
	public void like(int recipeCode) throws ModifyException {
		pointDAO.updateLike(recipeCode);
	}

	/**
	 * 포인트 수정 절차(싫어요)를 위한 메소드
	 * @param 포인트 수정 절차(싫어요)를 진행할 레시피 코드
	 * @throws ModifyException
	 * @author 최종국
	 */
	public void disLike(int recipeCode) throws ModifyException {
		pointDAO.updateDisLike(recipeCode);
	}
}
