package sam.struts.model;

public class MessageStore {
	private String message = "Hello Unknown";

	public void setUserName(String userName) {
		message = "Hello "+userName.trim() + "!";
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "MessageStore.toString() -> "+message;
	}
}
