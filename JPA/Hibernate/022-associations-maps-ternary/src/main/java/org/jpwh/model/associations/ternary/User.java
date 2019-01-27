package org.jpwh.model.associations.ternary;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.json.JSONObject;

@Entity
@Table(name="USERS")
public class User {
	@Id @GeneratedValue
	private long id;
	
	@NotNull String username;
	
	public User() {}
	public User(String username) {this.username = username;}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getId() {
		return id;
	}
	public JSONObject toJsonObject() {
		JSONObject o = new JSONObject();
		o.put("id", id);
		o.put("username", username);
		return o;
	}
}
