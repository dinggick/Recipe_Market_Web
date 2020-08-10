package com.recipe.vo;

import java.util.Date;

public class Review {
	private Purchase purchase; // 구매
	private String reviewComment; // 후기내용
	private Date reviewDate; // 후기작성일자

	public Review() {
	}

	public Review(Purchase purchaseCode, String reviewComment, Date reviewDate) {
		super();
		this.purchase = purchaseCode;
		this.reviewComment = reviewComment;
		this.reviewDate = reviewDate;
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

	@Override
	public String toString() {
		return "Review [purchase=" + purchase + ", reviewComment=" + reviewComment + ", reviewDate=" + reviewDate + "]";
	}

}
