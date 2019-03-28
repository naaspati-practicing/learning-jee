package sam.learn.ejb.jms;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.json.JSONException;
import org.json.JSONObject;

@MessageDriven(
		activationConfig = {
				@ActivationConfigProperty(propertyName="destinationLookup", propertyValue="jms/JMSTopic"),
				@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Topic"),
								
		}
		)
public class StatusMailerBean implements MessageListener {
	private static final Logger logger = Logger.getLogger(StatusMailerBean.class.getName());

	@Override
	public void onMessage(Message m) {
		if(m instanceof MapMessage) {
			MapMessage map = (MapMessage) m;
			
			try {
				logger.info("recieved: "+ new JSONObject()
				.put("from", map.getString("from"))
				.put("to", map.getString("to"))
				.put("subject", map.getString("subject"))
				.put("content", map.getString("content"))
				.toString(4));
			} catch (JSONException | JMSException e) {
				e.printStackTrace();
			}
		} else {
			logger.severe("unknown msg type: "+m);
		}
 			
	}
	

}
