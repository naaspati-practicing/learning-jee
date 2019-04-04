package com.actionbazaar.jaxb;

import java.io.Serializable;

public class BiographicalInfo implements Serializable {
	private static final long serialVersionUID = 4449530767362400297L;
	
	private String firstName;
	private String lastName;
	
	public String getFirstName(){ return this.firstName; }
	public void setFirstName(String firstName){ this.firstName = firstName; }

	public String getLastName(){ return this.lastName; }
	public void setLastName(String lastName){ this.lastName = lastName; }

}
