package org.glassfish.jersey.examples.server.async.managed;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	private String author;
	private String message;
	private long time = System.currentTimeMillis();

	public Message() {}
	
	public Message(String author, String message) {
		this.author = author;
		this.message = message;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Message [author=" + author + ", message=" + message + ", time=" + time + "]";
	}
}
