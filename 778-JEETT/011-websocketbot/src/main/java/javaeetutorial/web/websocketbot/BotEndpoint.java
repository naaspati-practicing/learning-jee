package javaeetutorial.web.websocketbot;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import javaeetutorial.web.websocketbot.MessageEncoder.ChatMessageEncoder;
import javaeetutorial.web.websocketbot.MessageEncoder.InfoMessageEncoder;
import javaeetutorial.web.websocketbot.MessageEncoder.JoinMessageEncoder;
import javaeetutorial.web.websocketbot.MessageEncoder.UsersMessageEncoder;
import javaeetutorial.web.websocketbot.messages.ChatMessage;
import javaeetutorial.web.websocketbot.messages.InfoMessage;
import javaeetutorial.web.websocketbot.messages.JoinMessage;
import javaeetutorial.web.websocketbot.messages.Message;
import javaeetutorial.web.websocketbot.messages.UsersMessage;

@ServerEndpoint(
		value="/websocketbot",
		decoders = MessageDecoder.class,
		encoders = {
				ChatMessageEncoder.class,
				InfoMessageEncoder.class,
				JoinMessageEncoder.class,
				UsersMessageEncoder.class
		}
		)
public class BotEndpoint {
	private static final Logger LOGGER = Logger.getLogger(BotEndpoint.class.getSimpleName());
	private static final String ACTIVE = "active";
	private static final String NAME = "name";

	@Inject private BotBean bot;
	@Resource(name="comp/DefaultManagedExecutorService")
	private ManagedExecutorService mes;

	@OnOpen
	public void open(Session session) {
		LOGGER.info("Connection Opened: "+session.getId());
	}

	@OnMessage
	public void message(Session session, Message msg) {
		LOGGER.info("Received: " + msg.toString()+", from: "+session.getId());

		if(msg instanceof JoinMessage) {
			/* Add the new user and notify everybody */
			JoinMessage jmsg = (JoinMessage) msg;
			Map<String, Object> map =  session.getUserProperties();
			map.put(NAME, jmsg.getName());
			map.put(ACTIVE, Boolean.TRUE);

			sendAll(session, 
					new InfoMessage(jmsg.getName()+" has joined the chat"),
					new ChatMessage(bot.getName(), jmsg.getName(), "Hi There!!"),
					new UsersMessage(userlist(session))
					);
		} else if(msg instanceof ChatMessage) {
			ChatMessage cm = (ChatMessage) msg;
			sendAll(session, cm);
			if(cm.getTarget().equals(bot.getName())) {
				/* The bot replies to the message */
				mes.execute(() -> sendAll(session, new ChatMessage(bot.getName(), cm.getName(), bot.respond(cm.getMessage()))));
			}
		}
	}

	@OnClose
	public void close(Session session) {
		Map<String, Object> map =  session.getUserProperties();
		map.put(ACTIVE, Boolean.FALSE);
		String name = (String) map.get(NAME);
		if(name != null) {
			sendAll(session, 
					new InfoMessage(name+" has left the chat"),
					new UsersMessage(userlist(session))
					);
		}
		LOGGER.info("Connection closed: "+session.getId());
	}
	
	@OnError
	public void error(Session session, Throwable t) {
		LOGGER.log(Level.SEVERE, "Connection error: "+session.getId(), t);
	}
	private void sendAll(Session session, Message...messages) {
		for (Message m : messages) {
			openConnections(session)
			.forEach(s -> {
				try {
					s.getBasicRemote().sendObject(m);
					LOGGER.info("message sent: "+m+", to: "+s.getId());
				} catch (IOException | EncodeException e) {
					LOGGER.log(Level.SEVERE, "failed to send message: "+m+", to: "+session.getId(), e);
				}
			});
		}
	}

	private List<String> userlist(Session session) {
		return openConnections(session)
				.map(Session::getUserProperties)
				.filter(map -> map.get(ACTIVE) == Boolean.TRUE)
				.map(map -> map.get(NAME))
				.filter(Objects::nonNull)
				.map(String.class::cast)
				.collect(Collectors.toList());
	}

	private Stream<Session> openConnections(Session session) {
		return session.getOpenSessions()
				.stream()
				.filter(Session::isOpen);
	}
}
