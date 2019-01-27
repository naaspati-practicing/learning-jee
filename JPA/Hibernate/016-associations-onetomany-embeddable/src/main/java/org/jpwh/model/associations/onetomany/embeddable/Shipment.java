package org.jpwh.model.associations.onetomany.embeddable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Shipment {
	@Id @GeneratedValue
	private long id;
	
	@NotNull private Date createdON = new Date();

	public Date getCreatedON() {
		return createdON;
	}

	public void setCreatedON(Date createdON) {
		this.createdON = createdON;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", createdON=" + createdON + "]";
	}
}
