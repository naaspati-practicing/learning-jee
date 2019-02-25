package javaeetutorial.rsvp.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import javaeetutorial.rsvp.entity.Event;
import javaeetutorial.rsvp.entity.Response;

@SessionScoped
@Named
public class EventManager implements Serializable {
	private static final long serialVersionUID = -3251310285236675853L;
	
	private static final Logger logger = Logger.getLogger(EventManager.class.getName());
	protected Event currentEvent;
	private Response currentResponse;
	private Client client;
	
	@Resource(name="javaeetutorial.rsvp.web.EventManager.baseUri")
	private String baseUri;
	
	@PostConstruct
	private void init() {
		this.client = ClientBuilder.newClient();
	}
	@PreDestroy
	public void clean() {
		client.close();
	}
	public Event getCurrentEvent() {
		return currentEvent;
	}
	public void setCurrentEvent(Event currentEvent) {
		this.currentEvent = currentEvent;
	}
	public Response getCurrentResponse() {
		return currentResponse;
	}
	public void setCurrentResponse(Response currentResponse) {
		this.currentResponse = currentResponse;
	}
	
	public List<Response> retrieveEventResponses() {
		if (this.currentEvent == null) {
			logger.warning("current event is null");
			return null;
		} 
		
		logger.info(() -> "getting responses for "+this.currentEvent.getName());
		
		try {
			Event e = client.target(baseUri)
					.path(this.currentEvent.getId().toString())
					.request(MediaType.APPLICATION_XML)
					.get(Event.class);
			if(e == null) {
				logger.warning("returned event is null");
				return null;
			}
			return  e.getResponses();
		} catch (Exception e) {
			logger.warning("an error occurred when getting event responses.");
            return null;
		}
	}
	
	public String retrieveEventStatus(Event event) {
        this.setCurrentEvent(event);
        return "eventStatus";
    }
	public String viewResponse(Response response) {
        this.currentResponse = response;
        return "viewResponse";
    }
}
