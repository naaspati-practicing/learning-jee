package sam.jpa.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="USERS")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id private int id;
	private String name;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Set<Message> messages = new HashSet<>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	public void addMessage(Message message) {
		getMessages().add(message);
		message.setUser(this);
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", messages=" + messages + "]";
	}
}
