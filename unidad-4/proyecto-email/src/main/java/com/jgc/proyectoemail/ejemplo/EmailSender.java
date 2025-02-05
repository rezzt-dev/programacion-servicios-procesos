package com.jgc.proyectoemail.ejemplo;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
   // --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- 
  public void sendEmail(String recipient, String subject, String body) {
		//Crear Contraseñas de aplicación: java
		final String username = "rezzt.dev@proton.me";
		final String password = "mfza tenj hrnb oszm";

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", "587");
		props.put("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		try {
			Session session = Session.getInstance(props,
				    new javax.mail.Authenticator() {
				        protected PasswordAuthentication getPasswordAuthentication() {
				            return new PasswordAuthentication(username, password);
				        }
				    }
				);
			session.setDebug(true);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("no-reply@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);
			message.setText(body);

			// set the message content here
			Transport.send(message);
			System.out.println("Email sent successfully to: " + recipient);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String email = "jorgehm260505@gmail.com";
		String subject = "Jorge Putero Somali";
		String body = "jorge guarra.";

		// Sending email without attachment
		for (int i=0; i<10; i++) {
			EmailSender emailSender = new EmailSender();
			emailSender.sendEmail(email, subject, body);
			System.out.println("Mensaje enviado");
		}
	}
}
