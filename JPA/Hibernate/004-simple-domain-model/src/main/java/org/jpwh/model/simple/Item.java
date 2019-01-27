package org.jpwh.model.simple;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item {
	public Item() {}
	
	@Id @GeneratedValue
	private long id;
	
	@Version
	private long version;
	
	@NotNull
	@Size(
			min=2, 
			max=255,
			message = "Name is required, maximum 255 characters."
			)
	private String name;
	
	@Future
	private Date auctionEnd;
	private BigDecimal buyNowPrice;
	
	@Transient
	private Set<Bid> bids = new HashSet<>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Category category;

	public long getVersion() {
		return version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getAuctionEnd() {
		return auctionEnd;
	}
	public void setAuctionEnd(Date auctionEnd) {
		this.auctionEnd = auctionEnd;
	}
	public BigDecimal getBuyNowPrice() {
		return buyNowPrice;
	}
	public void setBuyNowPrice(BigDecimal buyNowPrice) {
		this.buyNowPrice = buyNowPrice;
	}
	public Set<Bid> getBids() {
		return bids;
	}
	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public long getId() {
		return id;
	} 
	public void addBid(Bid bid) {
		Objects.requireNonNull(bid, "Can't add null Bid");
		if(bid.getItem() != null)
			throw new IllegalStateException("Bid is already assigned to an Item");
		
		getBids().add(bid);
		bid.setItem(this);
	}
	public Bid placeBid(Bid currentHigestBid, BigDecimal bidAmount) {
		if(currentHigestBid == null || currentHigestBid.getAmount().compareTo(bidAmount) < 0)
			return new Bid(this, bidAmount);
		
		return null;
		
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", version=" + version + ", name=" + name + ", auctionEnd=" + auctionEnd
				+ ", buyNowPrice=" + buyNowPrice + ", bids=" + bids + ", category=" + category + "]";
	}
}
