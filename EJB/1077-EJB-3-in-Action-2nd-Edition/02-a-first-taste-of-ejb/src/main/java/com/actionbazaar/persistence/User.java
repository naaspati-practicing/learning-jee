package com.actionbazaar.persistence;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User extends BaseEntity {
	private static final long serialVersionUID = 1378794551452543956L;
	
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;
	
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;
	
	private byte[] picture;
	
	@Column(name="BIRTH_DATE")
	private LocalDate birthDate;

	public String getFirstName(){ return this.firstName; }
	public void setFirstName(String firstName){ this.firstName = firstName; }

	public String getLastName(){ return this.lastName; }
	public void setLastName(String lastName){ this.lastName = lastName; }

	public byte[] getPicture(){ return this.picture; }
	public void setPicture(byte[] picture){ this.picture = picture; }

	public LocalDate getBirthDate(){ return this.birthDate; }
	public void setBirthDate(LocalDate birthDate){ this.birthDate = birthDate; }
	
}
