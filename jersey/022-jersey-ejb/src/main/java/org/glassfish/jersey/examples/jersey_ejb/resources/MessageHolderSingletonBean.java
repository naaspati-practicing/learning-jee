package org.glassfish.jersey.examples.jersey_ejb.resources;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.Singleton;

import org.glassfish.jersey.examples.jersey_ejb.entities.Message;

@Singleton
public class MessageHolderSingletonBean {
	private final AtomicInteger current_max_id = new AtomicInteger(0);
	
	private List<Message> list = new CopyOnWriteArrayList<>(new Message[]{
			new Message(new Date(), "message-1", current_max_id.getAndIncrement()),
			new Message(new Date(100), "message-2", current_max_id.getAndIncrement()),
			new Message(new Date(1000), "message-3", current_max_id.getAndIncrement()),
	});
	
	public List<Message> getMessages() {
		return Collections.unmodifiableList(list);
	}
	public int getNewId() {
		return current_max_id.getAndIncrement();
	}
	public Message addMessage(String msg) {
		Message m = new Message(new Date(), msg, current_max_id.getAndIncrement());
		list.add(m);
		return m;
	}
	public Message getMessage(int uniqueId) {
		return list.parallelStream().filter(c -> c != null && c.getUniqueId() == uniqueId).findFirst().orElse(null);
	}
	public boolean deleteMessage(int uniqueId) {
		return list.removeIf(c -> c.getUniqueId() == uniqueId);
	}
}
