package javaeetutorial.rsvp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="Person")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Person implements Serializable, Helper  {
	private static final long serialVersionUID = -6652105899125349287L;
	
	@XmlTransient @OneToMany(mappedBy="person")
	private List<Response> responses;
	
	@XmlTransient @OneToMany(mappedBy="owner")
	private List<Event> ownedEvents;
	
	@XmlTransient @ManyToMany(mappedBy="invitees")
	private List<Event> events;
	
	@Id @GeneratedValue
	private Long id;
	
	protected String firstName;
	protected String lastName;
	
	public Person(String firstName, String lastName) {
		this();
		
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Person() {
		this.events = new ArrayList<>();
		this.ownedEvents = new ArrayList<>();
		this.responses = new ArrayList<>();
	}
	public List<Response> getResponses() {
		return responses;
	}
	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
	public List<Event> getOwnedEvents() {
		return ownedEvents;
	}
	public void setOwnedEvents(List<Event> ownedEvents) {
		this.ownedEvents = ownedEvents;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
