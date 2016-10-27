package pl.kielce.tu.pharmacy.web.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


@Named("mailBean")
@RequestScoped
public class Mail {
	
	private final String username = "jpaejbjsf@gmail.com";
	private final String password = "jpaejbjsf92";

	public Mail()
	{
		
	}
	
	public void sendMail(String recipant, String subject, String senderinfo, String information) 
	{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() 
					{
						return new PasswordAuthentication(username, password);
					}
				  });
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("jpaejbjsf@gmail.com", "ABBC Dental Clinic"));
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		
		try 
		{
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipant));
			message.setSubject(subject);
			message.setText(senderinfo + ":\n" + information);
			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) 
		{
			throw new RuntimeException(e);
		}				
	}
	
	public void sendMailWithAttachment(String recipant, String subject, String information, String fileName)
	{	
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() 
					{
						return new PasswordAuthentication(username, password);
					}
				  });
		Message message = new MimeMessage(session);
		try 
		{			
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipant));
			message.setSubject(subject);			

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(information);
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			//add attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileName);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			
		} catch (MessagingException e) 
		{
			throw new RuntimeException(e);
		}
	}
	
}
