package javaeetutorial.web.websocketbot.messages;

/* Represents a join message for the chat */

public class JoinMessage implements Message {
	public static final String TYPE = "join";
	private String name;

	public JoinMessage(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return getClass().getSimpleName()+"@"+name;
	}
	


}
