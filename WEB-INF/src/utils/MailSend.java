package utils;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;

public class MailSend{

	public static void sendEmail(String toEmails, String subject, String textMessage){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new GmailAuthenticator());
		Message message = new MimeMessage(session);

		try{
			message.setFrom(new InternetAddress("ganeshji22922@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmails));
			message.setSubject(subject);
				
			MimeMultipart mMult = new MimeMultipart("related");
			MimeBodyPart mBodyPart = new MimeBodyPart();
			mBodyPart.setContent(textMessage, "text/html");
			System.out.println(textMessage);

			mMult.addBodyPart(mBodyPart);

			message.setContent(mMult);

			Transport.send(message);
		}catch(AddressException e){
			e.printStackTrace();
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}

	public static String passwordRecoveryMail(){
		return "RECOVERY";
	}

	public static void sendAccountActivationMail(String email,String activationCode){
		String textMessage = "<a href='http://localhost:8080/tdf/activate.do?actcode="+activationCode+"&email="+email+"'>Activate Your Account</a>";
		sendEmail(email, "Account Activation", textMessage);
	}
}

class GmailAuthenticator extends Authenticator{
	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication("ganeshji22922@gmail.com", "Krishna2992");
	}	
}
	
	
