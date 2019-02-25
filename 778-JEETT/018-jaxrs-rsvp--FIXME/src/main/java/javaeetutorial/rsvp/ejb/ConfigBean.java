package javaeetutorial.rsvp.ejb;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaeetutorial.rsvp.entity.Event;
import javaeetutorial.rsvp.entity.Person;
import javaeetutorial.rsvp.entity.Response;
import javaeetutorial.rsvp.util.ResponseEnum;

@Singleton
@Startup
public class ConfigBean {
	
	@PersistenceContext
	private EntityManager em;
	
	private static final Logger LOGGER = Logger.getLogger(ConfigBean.class.getName());
	
	@PostConstruct
	void init() {
		Person dad = new Person("father", "ofJava");
		em.persist(dad);
		
		Event event = new Event();
		event.setName("Duke's Birthday Party");
		event.setLocation("Top of the Mark");
		event.setDate(new GregorianCalendar(2010, Calendar.MAY, 23, 19, 0).getTime());
		em.persist(event);
		
		dad.getOwnedEvents().add(event);
		dad.getEvents().add(event);
		event.setOwner(dad);
		event.getInvitees().add(dad);
		
		Response dadResponse = new Response(event, dad, ResponseEnum.ATTENDING);
		em.persist(event);
		
		event.getResponses().add(dadResponse);
		
		Person duke = new Person("Duke", "ofJava");
		em.persist(duke);
		
		Person tux = new Person("Tux", "Penguin");
		em.persist(tux);
		
		// set the relationships
        event.getInvitees().add(duke);
        duke.getEvents().add(event);
        Response dukesResponse = new Response(event, duke);
        em.persist(dukesResponse);
        event.getResponses().add(dukesResponse);
        duke.getResponses().add(dukesResponse);

        event.getInvitees().add(tux);
        tux.getEvents().add(event);
        Response tuxsResponse = new Response(event, tux);
        em.persist(tuxsResponse);
        event.getResponses().add(tuxsResponse);
        tux.getResponses().add(tuxsResponse);
	} 
	

}
