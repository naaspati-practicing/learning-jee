package javaeetutorial.rsvp.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import javaeetutorial.rsvp.entity.Event;
import javaeetutorial.rsvp.entity.Person;
import javaeetutorial.rsvp.util.ResponseEnum;

@SessionScoped
@Named
public class StatusManager implements Serializable {
	private static final long serialVersionUID = 7818927777713192887L;
	
	private static final Logger logger = Logger.getLogger(StatusManager.class.getName());
	private Event event;
	private List<Event> events;
	private Client client;
	private WebTarget target;
	
	@Resource(name="javaeetutorial.rsvp.web.StatusManager.baseUri")
	private String baseUri;
	
	@PostConstruct
	private void init() {
		this.client = ClientBuilder.newClient();
	}
	@PreDestroy
	public void clean() {
		client.close();
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public String getEventStatus(Event event) {
        this.setEvent(event);
        return "eventStatus";
    }
	public ResponseEnum[] getStatusValues() {
		return ResponseEnum.values();
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public List<Event> getEvents() {
		List<Event> list = null;
		
		try {
			list = client.target(baseUri)
					.path("/status/all")
					.request(MediaType.APPLICATION_XML)
					.get(new GenericType<List<Event>>() {});
			
			if (list == null) 
                logger.severe("Returned events null.");
             else 
                logger.info("Events have been returned.");
		} catch (ResponseProcessingException ex) {
            logger.log(Level.SEVERE, ex, () -> "baseUri:\""+baseUri+"\", error: " + ex.getMessage());
        } 
		return list;
	}
	
	public String changeStatus(ResponseEnum userResponse, Person person, Event event) {
		logger.log(Level.INFO, "changing status to {0} for {1} {2} for event ID {3}.", new Object[]{userResponse, person.getFirstName(), person.getLastName(), event.getId().toString()});
		
		try {
			client.target(baseUri)
			.path(event.getId().toString())
			.path(person.getId().toString())
			.request(MediaType.APPLICATION_XML)
			.post(Entity.xml(userResponse.toString()));
			
			return "changedStatus";
		} catch (Exception e) {
			logger.log(Level.WARNING, "couldn''t change status for {0} {1}", new Object[]{person.getFirstName(), person.getLastName()});
            logger.log(Level.WARNING, e.getMessage());
            return "error";
		}
	}
}
