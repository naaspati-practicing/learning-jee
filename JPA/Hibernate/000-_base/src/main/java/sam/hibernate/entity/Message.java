package sam.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message implements Serializable {
	private static final long serialVersionUID = -1568021606847230925L;

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
