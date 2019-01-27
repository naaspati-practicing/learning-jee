package org.jpwh.model.associations.onetomany.jointable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="USERS")
public class User {
	@Id @GeneratedValue
	private long id;
	
	@NotNull
	private String username;
	
	@OneToMany(mappedBy="buyer")
	private Set<Item> boughtItem = new HashSet<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Item> getBoughtItem() {
		return boughtItem;
	}

	public void setBoughtItem(Set<Item> boughtItem) {
		this.boughtItem = boughtItem;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", boughtItem=" + boughtItem + "]";
	}
}
