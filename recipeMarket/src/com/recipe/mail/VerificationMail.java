package com.recipe.mail;


public class VerificationMail implements Runnable{
	private Mail mail;
	private String userMail;
	public VerificationMail(String userMail) {
		this.userMail = userMail;		
	}
	@Override
	public void run() {
		mail = new Mail();
		mail.sendVerification(userMail);
		}
		
} 

