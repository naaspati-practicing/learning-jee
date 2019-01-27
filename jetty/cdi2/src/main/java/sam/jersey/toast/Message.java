package sam.jersey.toast;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Message {
	private static int n = 1;
	
	@Override
	public String toString() {
		return "Message: "+(n++);
	}
	
}
