package org.glassfish.jersey.examples.managedbeans.resources;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Widget implements Serializable {
	private static final long serialVersionUID = -1145726532565798344L;
	
	@Id private int id;
	private String val;
	
	public Widget() {}

	public Widget(String val) {
		this.val = val;
	}
	public Widget(int id, String val) {
		this.id = id;
		this.val = val;
	}

	public int getId() {
		return id;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "Widget [id=" + id + ", val=" + val + "]";
	}
}
