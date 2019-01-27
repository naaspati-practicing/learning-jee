package sam.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message{
	@Id
	@GeneratedValue
	private long id;
	
	private String text;
	
	public Message() {}
	public Message(String msg) {this.text = msg;}

	public String getText() { return text; }
	public void setText(String text) { this.text = text; }
	@Override
	public String toString() {
		return "Message [id=" + id + ", text=" + text + "]";
	}
	
	
}
