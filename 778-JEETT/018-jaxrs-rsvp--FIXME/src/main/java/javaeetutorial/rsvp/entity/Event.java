package javaeetutorial.rsvp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQuery(name=Event.NAMED_QUERY_SELECT_ALL_UPCOMING_EVENTS, query="select e from Event e")
@XmlRootElement(name="Event")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Event implements Serializable, Helper {
	private static final long serialVersionUID = -5637108568598253296L;
	
	public static final String NAMED_QUERY_SELECT_ALL_UPCOMING_EVENTS = "javaeetutorial.rsvp.entity.Event.selectAllUpcomingEvents";
	
	@Id @GeneratedValue
	private Long id;
	
	@OneToMany(mappedBy = "event")
	private List<Response> responses;
	
	@ManyToMany
	protected List<Person> invitees;
	
	@ManyToOne
	protected Person owner;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	protected String name;
	protected String location;
	
	public Event() {
		this.invitees = new ArrayList<>();
		this.responses = new ArrayList<>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Response> getResponses() {
		return responses;
	}
	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
	public List<Person> getInvitees() {
		return invitees;
	}
	public void setInvitees(List<Person> invitees) {
		this.invitees = invitees;
	}
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return toString0();
	}
	@Override
	public int hashCode() {
		return hashCode0();
	}
	@Override
	public boolean equals(Object obj) {
		return equals0(obj);
	}

}
