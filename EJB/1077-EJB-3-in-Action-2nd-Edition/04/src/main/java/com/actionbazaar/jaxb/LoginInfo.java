package com.actionbazaar.jaxb;

import java.io.Serializable;

public class LoginInfo implements Serializable {
	private static final long serialVersionUID = -5608703683102836637L;
	
	private String username;
	private String password;
	
	public String getUsername(){ return this.username; }
	public void setUsername(String username){ this.username = username; }

	public String getPassword(){ return this.password; }
	public void setPassword(String password){ this.password = password; }

}
