package com.recipe.mail;

import com.recipe.vo.Purchase;

public class PurchaseMail implements Runnable{
	private Mail mail;
	private String userMail;
	private Purchase p;
	public PurchaseMail(String userMail, Purchase p) {
		this.userMail = userMail;
		this.p = p;
	}
	@Override
	public void run() {
		mail = new Mail();
		mail.sendPurchaseInfo(userMail, p);
		}		
} 