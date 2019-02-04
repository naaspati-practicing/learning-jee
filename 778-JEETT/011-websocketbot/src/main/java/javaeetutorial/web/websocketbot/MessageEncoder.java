package javaeetutorial.web.websocketbot;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import javaeetutorial.web.websocketbot.messages.ChatMessage;
import javaeetutorial.web.websocketbot.messages.InfoMessage;
import javaeetutorial.web.websocketbot.messages.JoinMessage;
import javaeetutorial.web.websocketbot.messages.UsersMessage;

public abstract class MessageEncoder<E> implements Encoder.Text<E> {
	public static final String TYPE_KEY = "type";
	
	@Override public void destroy() { }
	@Override public void init(EndpointConfig config) { }

	protected String json(String...key_value_pairs) {
		int n = 0;
		JSONObject json = new JSONObject();

		while(n < key_value_pairs.length) 
			json.put(key_value_pairs[n++], key_value_pairs[n++]);

		return json.toString();
	}

	public static class ChatMessageEncoder extends MessageEncoder<ChatMessage> { 
		@Override 
		public String encode(ChatMessage e) throws EncodeException {
			return json(
					TYPE_KEY, ChatMessage.TYPE,
					"name", e.getName(),
					"message", e.getMessage(),
					"target", e.getTarget()		
					);
		}
	}
	public static class InfoMessageEncoder extends MessageEncoder<InfoMessage> { 
		@Override 
		public String encode(InfoMessage e) throws EncodeException {
			return json(
					TYPE_KEY, InfoMessage.TYPE,
					"info", e.getInfo()
					);
		}
	}
	public static class JoinMessageEncoder extends MessageEncoder<JoinMessage> { 
		@Override 
		public String encode(JoinMessage e) throws EncodeException {
			return json(
					TYPE_KEY, JoinMessage.TYPE,
					"name", e.getName()
					);
		}
	}
	public static class UsersMessageEncoder extends MessageEncoder<UsersMessage> { 
		@Override 
		public String encode(UsersMessage e) throws EncodeException {
			return new JSONObject()
					.put(TYPE_KEY, UsersMessage.TYPE)
					.put("userlist", new JSONArray(e.getUserlist()))
					.toString();
		}
	}
}
