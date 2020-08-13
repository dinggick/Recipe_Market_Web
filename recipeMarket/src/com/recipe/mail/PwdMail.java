package com.recipe.mail;

public class PwdMail implements Runnable{
	private Mail mail;
	private String userMail;
	private String pwd;
	public PwdMail(String userMail, String pwd) {
		this.userMail = userMail;
		this.pwd = pwd;
	}
	@Override
	public void run() {
		mail = new Mail();
		mail.sendPwd(userMail, pwd);
		}
		
} 