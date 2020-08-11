package com.recipe.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {
	public void sendEmail() {
		   String user = "yi.hailey91@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
	       String password = "Hai1eyYi";   // 패스워드
	       String userMail = "yi.hailey91@gmail.com";
	      
	        Properties props = System.getProperties();
	        props.put("mail.smtp.host", "smtp.gmail.com"); 
	          props.put("mail.smtp.port", "25"); 
	          props.put("mail.debug", "true"); 
	          props.put("mail.smtp.auth", "true"); 
	          props.put("mail.smtp.starttls.enable","true"); 
	          props.put("mail.smtp.EnableSSL.enable","true");
	          props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
	          props.setProperty("mail.smtp.socketFactory.fallback", "false");   
	          props.setProperty("mail.smtp.port", "465");   
	          props.setProperty("mail.smtp.socketFactory.port", "465"); 
	          Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	              protected PasswordAuthentication getPasswordAuthentication() {
	                  return new PasswordAuthentication(user, password);
	              }
	          });

	      try{  
	    	
	         MimeMessage message = new MimeMessage(session);  
	         message.setFrom(new InternetAddress(user));
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(userMail));  
	         message.setSubject("[환영합니다 - RecipeMarket]");  
	         message.setText("누구누구님 레시피마켓가입을 축하드립니다");  
	  
	         // Send message  
	         Transport.send(message);  
	         System.out.println("message sent successfully....");  
	  
	      } catch (MessagingException mex) {
	    	  mex.printStackTrace();	     
	      }      

	}
	
}
