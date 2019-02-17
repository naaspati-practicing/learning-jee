package sam.grizzly.jersey;

public class HelloMessage {
	
	public static final String PREFIX = "Hello ";
	public static final HelloMessage DEFAULT_MESSAGE = new HelloMessage("Stranger");
	
	public final String recipient;
	
	public HelloMessage(String recipient) {
		this.recipient = recipient;
	}
	
	private static final StringBuilder sb = new StringBuilder(PREFIX);
	
	@Override
	public String toString() {
		synchronized (sb) {
			sb.setLength(PREFIX.length());
			sb.append(recipient)
			.append('!');
			return sb.toString();
		}
	}
}
