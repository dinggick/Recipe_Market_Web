package com.recipe.vo;

public class Favorite {
	private String customerEmail; //고객ID
	private RecipeInfo recipeInfo; //레시피정보
	
	public Favorite() {}
	public Favorite(String customerEmail, RecipeInfo recipeInfo) {
		super();
		this.customerEmail = customerEmail;
		this.recipeInfo = recipeInfo;
	}

	public String getcustomerEmail() {
		return customerEmail;
	}

	public void setcustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public RecipeInfo getRecipeInfo() {
		return recipeInfo;
	}

	public void setRecipeInfo(RecipeInfo recipeInfo) {
		this.recipeInfo = recipeInfo;
	}
	@Override
	public String toString() {
		return "Favorite [customerEmail=" + customerEmail + ", recipeInfo=" + recipeInfo + "]";
	}
	
}
