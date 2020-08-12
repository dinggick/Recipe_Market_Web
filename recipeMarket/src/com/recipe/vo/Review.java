package com.recipe.vo;

import java.util.Date;

public class Review {
	private Purchase purchase; // 구매
	private String reviewComment; // 후기내용
	private Date reviewDate; // 후기작성일자
	private RecipeInfo recipeInfo; //레시피 정보

	public Review() {
	}

	public Review(Purchase purchaseCode, String reviewComment, Date reviewDate, RecipeInfo recipeInfo) {
		super();
		this.purchase = purchaseCode;
		this.reviewComment = reviewComment;
		this.reviewDate = reviewDate;
		this.recipeInfo = recipeInfo;
	}
	
	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public RecipeInfo getRecipeInfo() {
		return recipeInfo;
	}

	public void setRecipeInfo(RecipeInfo recipeInfo) {
		this.recipeInfo = recipeInfo;
	}

	@Override
	public String toString() {
		return "Review [purchase=" + purchase + ", reviewComment=" + reviewComment + ", reviewDate=" + reviewDate + "]";
	}
}
