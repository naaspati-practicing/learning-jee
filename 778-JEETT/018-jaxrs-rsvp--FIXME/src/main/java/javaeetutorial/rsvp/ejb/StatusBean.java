package javaeetutorial.rsvp.ejb;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javaeetutorial.rsvp.entity.Event;

@Stateless
@Named
@Path("/status/")
public class StatusBean {
	private static final Logger LOGGER = Logger.getLogger(StatusBean.class.getName());
	
	private List<Event> allCurrentEvents;
	@PersistenceContext private EntityManager em;
	
	@GET
	@Produces({APPLICATION_XML, APPLICATION_JSON})
	@Path("{eventId}/")
	public Event getEvent(@PathParam("eventId") Long eventId) {
		return em.find(Event.class, eventId);
	}
	@SuppressWarnings("unchecked")
	@GET
	@Produces({APPLICATION_XML, APPLICATION_JSON})
	@Path("all")
	public List<Event> getAllCurrentEvents() {
		return this.allCurrentEvents = em.createNamedQuery(Event.NAMED_QUERY_SELECT_ALL_UPCOMING_EVENTS).getResultList();
	}
	public void setAllCurrentEvents(List<Event> allCurrentEvents) {
		this.allCurrentEvents = allCurrentEvents;
	}
}
