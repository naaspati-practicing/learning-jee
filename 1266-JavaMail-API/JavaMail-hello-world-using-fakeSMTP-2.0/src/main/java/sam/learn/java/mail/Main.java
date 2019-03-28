package sam.learn.java.mail;

import java.util.Objects;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;

import javafx.application.Application;
import sam.javamail.client.JavaMailClient;

public class Main {

	@Resource(mappedName="mail/fakeSMTP")
	public static Session session;

	public static void main(String[] args) throws MessagingException {
		Objects.requireNonNull(session);
		JavaMailClient.session = session;
		Application.launch(JavaMailClient.class, args);
	
	}
}
