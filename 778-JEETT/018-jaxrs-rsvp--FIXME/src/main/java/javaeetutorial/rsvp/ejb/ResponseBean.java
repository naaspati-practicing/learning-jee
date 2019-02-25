package javaeetutorial.rsvp.ejb;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javaeetutorial.rsvp.util.ResponseEnum.*;

import javaeetutorial.rsvp.entity.Response;
import javaeetutorial.rsvp.util.ResponseEnum;

@Stateless
@Path("/{eventId}/{inviteId}")
public class ResponseBean {
	@PersistenceContext
	private EntityManager em;

	private static final Logger logger = Logger.getLogger(ResponseBean.class.getName());

	@GET
	@Produces({APPLICATION_XML, APPLICATION_JSON})
	public Response getResponse(@PathParam("eventId") Long eventId, @PathParam("eventId") Long personId) {
		return (Response) em.createNamedQuery(Response.NAMED_QUERY_FIND_RESPONSES_BY_EVENT_N_PERSON)
				.setParameter("eventId", eventId)
				.setParameter("personId", personId)
				.getSingleResult();
	}
	@POST
	@Consumes({APPLICATION_XML, APPLICATION_JSON})
	public void postResponse(String responseString, @PathParam("eventId") Long eventId, @PathParam("eventId") Long personId) {
		logger.log(Level.INFO, "Updating status to {0} for person ID {1} for event ID {2}", new Object[]{responseString, eventId, personId}); 
		
		Response res = getResponse(eventId, personId);
		ResponseEnum response = responseString == null || responseString.trim().isEmpty() ? null : ResponseEnum.valueOf(responseString);
		
		if(res.getResponse() != response) {
			res.setResponse(response);
			em.merge(res);
		}
	}
}
