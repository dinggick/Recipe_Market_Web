package com.recipe.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.recipe.vo.Purchase;
import com.recipe.vo.PurchaseDetail;


public class Mail {
	public final String user = "RecipeMarketTeam@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
    public final String password = "Recipe1234";   // 패스워드
    private Session session;
    public Mail() {
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
          this.session = Session.getInstance(props, new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(user, password);
              }
          });

	}

	public void sendPwd(String userMail, String pwd) {

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
			message.setSubject("[비밀번호찾기 - RecipeMarket]");
			String txt = "";
			txt +=  "<br><img style='width: 200px;' src=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRPxFA%2FbtqGuM2ZBAe%2FUKRpsekkax1dKUJnmM9r4k%2Fimg.png\">";
			txt +=  "<br>비밀번호는: " + pwd + "입니다";
			message.setContent(txt, "text/html; charset=utf-8");

			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public void sendVerification(String userMail) {

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
			message.setSubject("[이메일인증 - RecipeMarket]");
			String txt = "";
			txt +=  "<br><img style='width: 200px;' src=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRPxFA%2FbtqGuM2ZBAe%2FUKRpsekkax1dKUJnmM9r4k%2Fimg.png\">";
			txt += "Recipe Market에 가입하신걸 환영합니다. 아래 링크를 눌러 이메일인증을 해주세요.";
			txt += "<br><a href='http://localhost/recipeMarket/customer/verify?email=" + userMail +"'>인증할게요<a>";
			message.setContent(txt, "text/html; charset=utf-8");
			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	public void sendPurchaseInfo(String userMail, Purchase p) {
		System.out.println(p.getPurchaseDetails().get(0).getRecipeInfo().getRecipeName());
		System.out.println(p.getPurchaseDetails().get(0).getPurchaseDetailQuantity());
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
			message.setSubject("[구매완료 - RecipeMarket]");
			String txt ="";
			int total = 0;
			txt += "<br><img style='width: 200px;' src=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRPxFA%2FbtqGuM2ZBAe%2FUKRpsekkax1dKUJnmM9r4k%2Fimg.png\">";
			txt+="<h2>구매완료 :)! </h2><p>저희 Recipe Market을 이용해 주셔서 감사드립니다.<br> 고객님의 구매내역은 아래와 같습니다.</p> "					
					+ "<table><tr><td>레시피이름</td><td>수량</td><td>가격</td></tr>";
			for (PurchaseDetail d : p.getPurchaseDetails()) {
				txt+="<tr><td>"+ d.getRecipeInfo().getRecipeName() + "</td><td>"
						+ d.getPurchaseDetailQuantity() + "</td><td>"
						+ (d.getRecipeInfo().getRecipePrice())*(d.getPurchaseDetailQuantity())+ "</td></tr>";
			total += (d.getRecipeInfo().getRecipePrice())*(d.getPurchaseDetailQuantity());
			}
			txt += "<br><p>총가격 :" + total + "원" + "</p>";
			txt += "</table>";		
			
			
			
			message.setContent(txt, "text/html; charset=utf-8");
			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
