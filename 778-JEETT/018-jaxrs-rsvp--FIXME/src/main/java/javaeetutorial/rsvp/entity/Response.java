package javaeetutorial.rsvp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javaeetutorial.rsvp.util.ResponseEnum;


@NamedQuery(name=Response.NAMED_QUERY_FIND_RESPONSES_BY_EVENT_N_PERSON,  query=
"SELECT r FROM Response r "+
"JOIN r.event e "+
"JOIN r.person p "+
"WHERE e.id = :eventId AND p.id = :personId"

		)
@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Response implements Serializable , Helper {
	private static final long serialVersionUID = -2524545713386138688L;
	public static final String NAMED_QUERY_FIND_RESPONSES_BY_EVENT_N_PERSON = "javaeetutorial.rsvp.entity.Response.NAMED_QUERY_FIND_RESPONSES_BY_EVENT_N_PERSON";
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne @XmlTransient
	private Event event;
	
	@ManyToOne
	private Person person;
	
	@Enumerated(EnumType.STRING)
	private ResponseEnum response;
	
	public Response() {
		this.response = ResponseEnum.NOT_RESPONDED;
	}

	public Response(Event event, Person person) {
		this(event, person, ResponseEnum.NOT_RESPONDED);
	}
	public Response(Event event, Person person, ResponseEnum response) {
		this.event = event;
		this.person = person;
		this.response = response;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public ResponseEnum getResponse() {
		return response;
	}
	public void setResponse(ResponseEnum response) {
		this.response = response;
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
