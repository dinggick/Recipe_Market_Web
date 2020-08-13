package com.recipe.vo;

import java.sql.Date;

public class Customer {
	private String customerEmail; //고객ID
	private String customerPwd; //고객비밀번호
	private String customerName; //고객이름
	private String customerGender; //성별
	private Date customerBirthDate; //생년월일
	private String customerPhone; //고객전화번호
	private Postal postal; //우편번호
	private String customerAddr; //고객상세주소
	private String verification; //인증 여부
	
	public Customer() {}

	public Customer(String customerEmail, String customerPwd, String customerName, String customerGender,
			Date customerBirthDate, String customerPhone, Postal postal, String customerAddr) {
		super();
		this.customerEmail = customerEmail;
		this.customerPwd = customerPwd;
		this.customerName = customerName;
		this.customerGender = customerGender;
		this.customerBirthDate = customerBirthDate;
		this.customerPhone = customerPhone;
		this.postal = postal;
		this.customerAddr = customerAddr;
	}
	
	public Customer(String customerEmail, String customerPwd, String customerName, String customerGender,
			Date customerBirthDate, String customerPhone, Postal postal, String customerAddr, String verification) {
		super();
		this.customerEmail = customerEmail;
		this.customerPwd = customerPwd;
		this.customerName = customerName;
		this.customerGender = customerGender;
		this.customerBirthDate = customerBirthDate;
		this.customerPhone = customerPhone;
		this.postal = postal;
		this.customerAddr = customerAddr;
		this.setVerification(verification);
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPwd() {
		return customerPwd;
	}

	public void setCustomerPwd(String customerPwd) {
		this.customerPwd = customerPwd;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public Date getCustomerBirthDate() {
		return customerBirthDate;
	}

	public void setCustomerBirthDate(Date customerBirthDate) {
		this.customerBirthDate = customerBirthDate;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Postal getPostal() {
		return postal;
	}

	public void setPostal(Postal postal) {
		this.postal = postal;
	}

	public String getCustomerAddr() {
		return customerAddr;
	}

	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	@Override
	public String toString() {
		return "Customer [customerEmail=" + customerEmail + ", customerPwd=" + customerPwd + ", customerName="
				+ customerName + ", customerGender=" + customerGender + ", customerBirthDate=" + customerBirthDate
				+ ", customerPhone=" + customerPhone + ", postal=" + postal + ", customerAddr=" + customerAddr + "]";
	}
}
