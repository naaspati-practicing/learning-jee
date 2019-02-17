package org.glassfish.jersey.examples.jersey_ejb.entities;

import static j2html.TagCreator.a;
import static j2html.TagCreator.attrs;
import static j2html.TagCreator.div;
import static j2html.TagCreator.span;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import j2html.tags.ContainerTag;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {

	@XmlAttribute
	private Date created;
	private String message;
	@XmlAttribute
	private int uniqueId;

	public Message(Date created, String message, int uniqueId) {
		this.created = created;
		this.message = message;
		this.uniqueId = uniqueId;
	}

	public int getUniqueId() {
		return uniqueId;
	}
	public Date getCreated() {
		return created;
	}
	public String getMessage() {
		return message;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public ContainerTag toHtml(String href) {
		return div(attrs(".message"),
				(href == null ? null : a("link").withHref(href)),  
				span(attrs(".created"), created == null ? "" : created.toString()),
				span(attrs(".uniqueID"), Integer.toString(uniqueId)),
				span(attrs(".content"), message == null ? "MESSAGE: " : "MESSAGE: ".concat(message))
				);
	}

}
