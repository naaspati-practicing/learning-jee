package javaeetutorial.web.websocketbot.messages;



/* Represents an information message, like
 * an user entering or leaving the chat */
public class InfoMessage implements Message {
	public static final String TYPE = "info";
	
	private String info;

	public InfoMessage(String info) {
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
	@Override
	public String toString() {
		return getClass().getSimpleName()+"("+info+")";
	}

}
