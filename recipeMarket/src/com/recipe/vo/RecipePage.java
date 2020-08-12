package com.recipe.vo;

public class RecipePage {
	private int num;				//번호
	private int recipeCode; 		//레시피코드
	private String recipeName; 		//레시피이름
	private int recipePrice; 		//레시피가격
	private int likeCount;			//좋아요 수
	private int purchaseQuantity;	// 판매량
	private int totalAmount;		// 총 매출액
	
	
	public RecipePage() { }
	
	public RecipePage(int num, int recipeCode, String recipeName, int recipePrice, int likeCount,
			int purchaseQuantity, int totalAmount) {
		super();
		this.num = num;
		this.recipeCode = recipeCode;
		this.recipeName = recipeName;
		this.recipePrice = recipePrice;
		this.likeCount = likeCount;
		this.purchaseQuantity = purchaseQuantity;
		this.totalAmount = totalAmount;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public int getRecipePrice() {
		return recipePrice;
	}
	public void setRecipePrice(int recipePrice) {
		this.recipePrice = recipePrice;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Override
	public String toString() {
		return "RecipePageingDAO [num=" + num + ", recipeCode=" + recipeCode + ", recipeName=" + recipeName
				+ ", recipePrice=" + recipePrice + ", likeCount=" + likeCount + ", purchaseQuantity=" + purchaseQuantity
				+ ", totalAmount=" + totalAmount + "]";
	}

}
