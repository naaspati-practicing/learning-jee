package com.actionbazaar.jaxb;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
	private static final long serialVersionUID = 3352596995014683848L;
	
	private String firstName;
	private String lastName;
	private int id;
	private  byte[] picture;
	private LocalDate dateOfBirth;
	
	public String getFirstName(){ return this.firstName; }
	public void setFirstName(String firstName){ this.firstName = firstName; }

	public String getLastName(){ return this.lastName; }
	public void setLastName(String lastName){ this.lastName = lastName; }

	public int getId(){ return this.id; }
	public void setId(int id){ this.id = id; }

	public byte[] getPicture(){ return this.picture; }
	public void setPicture(byte[] picture){ this.picture = picture; }

	public LocalDate getDateOfBirth(){ return this.dateOfBirth; }
	public void setDateOfBirth(LocalDate dateOfBirth){ this.dateOfBirth = dateOfBirth; }
}
