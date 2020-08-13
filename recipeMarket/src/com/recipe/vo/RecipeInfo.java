package com.recipe.vo;

import java.util.List;

public class RecipeInfo {
	private int recipeCode; //레시피코드
	private String recipeName; //레시피이름
	private String recipeSumm; //레시피요약
	private int recipePrice; //레시피가격
	private String recipeProcess; //레시피조리과정
	private String imgUrl; //이미지경로
	private Point point; //좋아요
	private List<RecipeIngredient> ingredients; //레시피재료
	private RnD rd; //이 레시피를 작성한 R&D
	
	public RecipeInfo() {}

	public RecipeInfo(int recipeCode, String recipeName, String recipeSumm, int recipePrice, String recipeProcess,
			String imgUrl, Point point, List<RecipeIngredient> ingredients) {
		super();
		this.recipeCode = recipeCode;
		this.recipeName = recipeName;
		this.recipeSumm = recipeSumm;
		this.recipePrice = recipePrice;
		this.recipeProcess = recipeProcess;
		this.imgUrl = imgUrl;
		this.point = point;
		this.ingredients = ingredients;
	}
	
	public RecipeInfo(int recipeCode, String recipeName, String recipeSumm, int recipePrice, String recipeProcess,
			String imgUrl, Point point, List<RecipeIngredient> ingredients, RnD rd) {
		super();
		this.recipeCode = recipeCode;
		this.recipeName = recipeName;
		this.recipeSumm = recipeSumm;
		this.recipePrice = recipePrice;
		this.recipeProcess = recipeProcess;
		this.imgUrl = imgUrl;
		this.point = point;
		this.ingredients = ingredients;
		this.setRd(rd);
	}

	public int getRecipeCode() {
		return recipeCode;
	}

	public void setRecipeCode(int recipeCode) {
		this.recipeCode = recipeCode;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getRecipeSumm() {
		return recipeSumm;
	}

	public void setRecipeSumm(String recipeSumm) {
		this.recipeSumm = recipeSumm;
	}

	public int getRecipePrice() {
		return recipePrice;
	}

	public void setRecipePrice(int recipePrice) {
		this.recipePrice = recipePrice;
	}

	public String getRecipeProcess() {
		return recipeProcess;
	}

	public void setRecipeProcess(String recipeProcess) {
		this.recipeProcess = recipeProcess;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public List<RecipeIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public RnD getRd() {
		return rd;
	}

	public void setRd(RnD rd) {
		this.rd = rd;
	}

	@Override
	public String toString() {
		return "RecipeInfo [recipeCode=" + recipeCode + ", recipeName=" + recipeName + ", recipeSumm=" + recipeSumm
				+ ", recipePrice=" + recipePrice + ", recipeProcess=" + recipeProcess + ", imgUrl=" + imgUrl
				+ ", point=" + point + ", ingredients=" + ingredients + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + recipeCode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeInfo other = (RecipeInfo) obj;
		if (recipeCode != other.recipeCode)
			return false;
		return true;
	}
}
