package sam.learn.ejb.mdb;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName="jms/JMSQueue")
public class OrderProcessingMDBBean implements MessageListener {
	private static final Logger logger = Logger.getLogger(OrderProcessingMDBBean.class.getName());

	@Override
	public void onMessage(Message message) {
		try {
            TextMessage tm = (TextMessage) message;
            logger.info("Message received (async): " + tm.getText());
        } catch (JMSException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
	}

}
