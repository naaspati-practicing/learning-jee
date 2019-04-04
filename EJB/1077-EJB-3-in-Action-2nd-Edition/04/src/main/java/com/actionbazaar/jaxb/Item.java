package com.actionbazaar.jaxb;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Item implements Serializable {
	private static final long serialVersionUID = 1506351901371363496L;
	
	private String name;
	private LocalDateTime bidStartTime;
	private LocalDateTime bidEndTime;
	private LocalDateTime creationTime;
	private double initialPrice;
	private long itemId;
	
	private List<Bid> bids;
	
	public String getName(){ return this.name; }
	public void setName(String name){ this.name = name; }

	public LocalDateTime getBidStartTime(){ return this.bidStartTime; }
	public void setBidStartTime(LocalDateTime bidStartTime){ this.bidStartTime = bidStartTime; }

	public LocalDateTime getBidEndTime(){ return this.bidEndTime; }
	public void setBidEndTime(LocalDateTime bidEndTime){ this.bidEndTime = bidEndTime; }

	public LocalDateTime getCreationTime(){ return this.creationTime; }
	public void setCreationTime(LocalDateTime creationTime){ this.creationTime = creationTime; }

	public double getInitialPrice(){ return this.initialPrice; }
	public void setInitialPrice(double initialPrice){ this.initialPrice = initialPrice; }

	public long getItemId(){ return this.itemId; }
	public void setItemId(long itemId){ this.itemId = itemId; }

	public List<Bid> getBids(){ return this.bids; }
	public void setBids(List<Bid> bids){ this.bids = bids; }
	
}
