package javaeetutorial.web.websocketbot;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import org.json.JSONObject;

import javaeetutorial.web.websocketbot.messages.ChatMessage;
import javaeetutorial.web.websocketbot.messages.JoinMessage;
import javaeetutorial.web.websocketbot.messages.Message;

public class MessageDecoder implements Decoder.Text<Message>{
	private static final Logger LOGGER = Logger.getLogger(MessageDecoder.class.getName());
	
	private String parsing;
	private Message parsed;
	private DecodeException exception;
	
	@Override public void init(EndpointConfig config) { }
	@Override public void destroy() {
		parsed = null;
		parsing = null;
		exception = null;
	}

	@Override
	public Message decode(String s) throws DecodeException {
		if(Objects.equals(s, parsing)) {
			if(exception != null)
				throw exception;
			return parsed;
		}
			

		Message msg = null;
		parsing = s;
		exception = null;
		
		try {
			LOGGER.info("parsing: "+s);
			
			JSONObject json = new JSONObject(s);
			switch (json.getString(MessageEncoder.TYPE_KEY)) {
				case JoinMessage.TYPE:
					msg = new JoinMessage(json.getString("name"));
					break;
				case ChatMessage.TYPE: 
					msg = new ChatMessage(
							json.getString("name"),
							json.getString("target"),
							json.getString("message")
							);
					break;
				default:
					new IllegalArgumentException("unknown type: "+json.getString("type"));
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "parsing failed: "+s, e);
			throw exception = new DecodeException(s, "failed to parse or bad json", e);
		}
		
		parsed = msg;
		LOGGER.info("parsed: "+msg);
		return msg;
	}

	@Override
	public boolean willDecode(String s) {
		s = s == null ? null : s.trim();
		
		if(s == null || s.isEmpty() || s.charAt(0) != '{' || s.charAt(s.length() - 1) != '}')
			return false;
		
		try {
			LOGGER.info("willdecode: "+(decode(s) != null));
			return decode(s) != null;
		} catch (DecodeException e) {
			return false;
		}
	}
}
