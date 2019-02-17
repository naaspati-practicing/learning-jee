package sam.jetty.jersey;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class HelloMessage {
	private static int N;
	private int id;
	private String name;
	
	public HelloMessage() {
		this.id = N++;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return id+": Hello "+(name == null ? "stranger" : name)+"!";
	}
}
