package com.recipe.vo;
import java.util.Date;

public class Review {
	private Purchase purchaseCode;
	private String reviewComment; //후기내용
	private Date reviewDate; //후기작성일자
	
	public Review() {}

	public Review(Purchase purchaseCode, String reviewComment, Date reviewDate) {
		super();
		this.purchaseCode = purchaseCode;
		this.reviewComment = reviewComment;
		this.reviewDate = reviewDate;
	}

	public Purchase getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(Purchase purchaseCode) {
		this.purchaseCode = purchaseCode;
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
		return "Review [purchaseCode=" + purchaseCode + ", reviewComment=" + reviewComment + ", reviewDate="
				+ reviewDate + "]";
	}

	

	
	
}
