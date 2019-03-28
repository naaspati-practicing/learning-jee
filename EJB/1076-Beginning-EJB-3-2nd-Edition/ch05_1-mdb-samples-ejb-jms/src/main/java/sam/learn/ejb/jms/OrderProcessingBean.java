package sam.learn.ejb.jms;

import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.Topic;

import com.thedeanda.lorem.LoremIpsum;

@Stateless
public class OrderProcessingBean {
	private final LoremIpsum lorem = new LoremIpsum();
	
	@Resource(mappedName="DefaultJMSConnectionFactory")
	private ConnectionFactory cf;
	
	@Resource(mappedName="jms/JMSTopic")
	private Topic topic;
	
	public String sendOrderStatus() throws JMSException {
		String from = lorem.getEmail();
		String to = lorem.getEmail();
		
		String content = "Your order has been processed. If you have questions  call EJB Application with order id #".concat(UUID.randomUUID().toString());
		
		try(JMSContext jms = cf.createContext(JMSContext.AUTO_ACKNOWLEDGE);
				) {
			MapMessage m = jms.createMapMessage();
			m.setString("from", from);
			m.setString("to", to);
			m.setString("subject", "Status of your wine order");
			m.setString("content", content);
			
			jms.createProducer().send(topic, m);
			
			return "Created a MapMessage and sent it to StatusTopic";
		}
	}
}
