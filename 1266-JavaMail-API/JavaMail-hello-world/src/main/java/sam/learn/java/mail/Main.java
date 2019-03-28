package sam.learn.java.mail;

import java.util.Objects;

import javax.annotation.Resource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import javafx.application.Application;
import sam.javamail.client.JavaMailClient;

public class Main {

	@Resource(mappedName="mail/naaspati")
	public static Session session;

	public static void main(String[] args) throws MessagingException {
		Objects.requireNonNull(session);
		JavaMailClient.session = session;
		Application.launch(JavaMailClient.class, args);
	}
}
