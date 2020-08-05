package com.recipe.vo;

public class Cart {
	private String customerEmail;
	private RecipeInfo recipeInfo;
	private int cartQuantity;
	
	public Cart() {}
	public Cart(String customerEmail, RecipeInfo recipeInfo, int cartQuantity) {
		super();
		this.customerEmail = customerEmail;
		this.recipeInfo = recipeInfo;
		this.cartQuantity = cartQuantity;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public RecipeInfo getRecipeInfo() {
		return recipeInfo;
	}

	public void setRecipeInfo(RecipeInfo recipeInfo) {
		this.recipeInfo = recipeInfo;
	}

	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	@Override
	public String toString() {
		return "Cart [customerEmail=" + customerEmail + ", recipeInfo=" + recipeInfo + ", cartQuantity=" + cartQuantity
				+ "]";
	}
	
}
