package javaeetutorial.web.websocketbot.messages;

import java.util.List;

/* Represents the list of users currently connected to the chat */
public class UsersMessage implements Message {
	public static final String TYPE = "users";
	
	private List<String> userlist;

	public UsersMessage(List<String> userlist) {
		this.userlist = userlist;
	}
	public List<String> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<String> userlist) {
		this.userlist = userlist;
	}

}
