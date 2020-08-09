package com.recipe.vo;
import java.util.Date;
import java.util.List;

public class Purchase {
	private int purchaseCode; //구매코드
	private Customer customerEmail; //고객ID
	private Date purchaseDate; //구매일자
	private List<PurchaseDetail> purchaseDetails; //구매상세내역
	private Review review; //리뷰
	
	public Purchase() {}

	public Purchase(int purchaseCode, Customer customerEmail, Date purchaseDate, List<PurchaseDetail> purchaseDetails,
			Review review) {
		super();
		this.purchaseCode = purchaseCode;
		this.customerEmail = customerEmail;
		this.purchaseDate = purchaseDate;
		this.purchaseDetails = purchaseDetails;
		this.review = review;
	}

	public int getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(int purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public Customer getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(Customer customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public List<PurchaseDetail> getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
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
				+ purchaseDate + ", purchaseDetails=" + purchaseDetails + ", review=" + review + "]";
	}

	
	
	
	
	
	
	
	
}
