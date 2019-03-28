package sam.learn.ejb.mdb;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@MessageDriven(mappedName="jms/JMSTopic")
public class StatusMailerBean implements MessageListener {
	private static final Logger logger = Logger.getLogger(StatusMailerBean.class.getName());

	@Resource(mappedName="mail/fakeSMTP")
	private Session mailer;

	public void onMessage(Message message) {
		try {
			if (message instanceof MapMessage) {
				MapMessage orderMessage = (MapMessage) message;
				String from = orderMessage.getStringProperty("from");
				String to = orderMessage.getStringProperty("to");
				String subject = orderMessage.getStringProperty("subject");
				String content = orderMessage.getStringProperty("content");
				javax.mail.Message msg = new MimeMessage(mailer);
				msg.setFrom(new InternetAddress(from));
				InternetAddress[] address = {new InternetAddress(to)};
				msg.setRecipients(RecipientType.TO, address);
				msg.setSubject(subject);
				msg.setSentDate(new java.util.Date());
				msg.setContent(content, "text/html");
				logger.info("MDB: Sending Message from " + from + " to " + to + "...");
				Transport.send(msg);
				logger.info("MDB: Message Sent");
			} else {
				logger.info("Invalid message ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
