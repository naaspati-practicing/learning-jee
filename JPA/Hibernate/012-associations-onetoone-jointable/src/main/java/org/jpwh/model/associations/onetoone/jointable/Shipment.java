package org.jpwh.model.associations.onetoone.jointable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Shipment {
	@Id @GeneratedValue
	private long id;
	
	@NotNull private Date createdOn = new Date();
	@NotNull private ShipmentState shipmentState = ShipmentState.TRANSIT;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinTable(
			name="ITEM_SHIPMENT", // required
			joinColumns=@JoinColumn(name="SHIPMENT_ID"), // Defaults to ID
			inverseJoinColumns=@JoinColumn(name="ITEM_ID", nullable=false, unique=true)
			)
	private Item auction;

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public ShipmentState getShipmentState() {
		return shipmentState;
	}

	public void setShipmentState(ShipmentState shipmentState) {
		this.shipmentState = shipmentState;
	}

	public Item getAuction() {
		return auction;
	}

	public void setAuction(Item auction) {
		this.auction = auction;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", createdOn=" + createdOn + ", shipmentState=" + shipmentState + ", auction="
				+ auction + "]";
	}
}
