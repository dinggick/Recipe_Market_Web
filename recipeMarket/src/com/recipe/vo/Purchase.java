package com.recipe.vo;
import java.util.Date;

public class Purchase {
	private int purchaseCode; //구매코드
	private String customerEmail; //고객ID
	private Date purchaseDate; //구매일자
	private PurchaseDetail purchaseDetail; //구매상세내역
	private Review review; //리뷰
	
	public Purchase() {}

	public Purchase(int purchaseCode, String customerEmail, Date purchaseDate, PurchaseDetail purchaseDetail,
			Review review) {
		super();
		this.purchaseCode = purchaseCode;
		this.customerEmail = customerEmail;
		this.purchaseDate = purchaseDate;
		this.purchaseDetail = purchaseDetail;
		this.review = review;
	}

	public int getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(int purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public PurchaseDetail getPurchaseDetail() {
		return purchaseDetail;
	}

	public void setPurchaseDetail(PurchaseDetail purchaseDetail) {
		this.purchaseDetail = purchaseDetail;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Purchase [purchaseCode=" + purchaseCode + ", customerEmail=" + customerEmail + ", purchaseDate="
				+ purchaseDate + ", purchaseDetail=" + purchaseDetail + ", review=" + review + "]";
	}
	
	
	
}
