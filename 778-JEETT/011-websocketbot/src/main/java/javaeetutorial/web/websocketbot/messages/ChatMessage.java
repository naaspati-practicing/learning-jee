package javaeetutorial.web.websocketbot.messages;

public class ChatMessage implements Message {
	public static final String TYPE = "chat";
	private String name;
	private String target;
	private String message;
	
	public ChatMessage(String name, String target, String message) {
		this.name = name;
		this.target = target;
		this.message = message;
	}
	
	public String getName() {
		return name;
	}
	public String getTarget() {
		return target;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName()+" [message=" + message + ", name=" + name + ", target=" + target + "]";
	}
}
