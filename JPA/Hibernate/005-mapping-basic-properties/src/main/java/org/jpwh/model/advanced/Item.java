package org.jpwh.model.advanced;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

@Entity
public class Item {
	@Id @GeneratedValue
	private long id;
	
	@Type(type="yes_no")
	private boolean verified;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false)
	@CreationTimestamp
	private Date createdOn;
	
	@Basic(fetch=FetchType.LAZY)
	private String description;
	
	@Basic(fetch=FetchType.LAZY)
	@Column(length=128*1024)
	private byte[] image; 
	
	@Lob
	private Blob imageBlob;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private AuctionType auctionType = AuctionType.HIGHEST_BID;
	
	@Formula("substr(description, 1,12) || '...'")
	private String shortDescription;
	
	@Formula("(select avg(b.amount) from bid b where b.item_id = id)")
	private BigDecimal averageBidAmount;
	
	@Column(name="IMPERIALWEIGHT")
	@ColumnTransformer(
			read = "IMPERIALWEIGHT/2.20462",
			write = "? * 2.20462"
			)
	private double metricWeight;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable=false, updatable=false)
	@Generated(GenerationTime.ALWAYS)
	private Date lastModified;
	
	@Column(insertable=false)
	@ColumnDefault("1.00")
	@Generated(GenerationTime.ALWAYS)
	private BigDecimal initialPrice;
	
	@Access(AccessType.PROPERTY)
	@Column(name="ITEM_NAME")
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.startsWith("AUCTION: ") ? name : "AUCTION: "+name;
	}
	public long getId() {
		return id;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Blob getImageBlob() {
		return imageBlob;
	}
	public void setImageBlob(Blob imageBlob) {
		this.imageBlob = imageBlob;
	}
	public AuctionType getAuctionType() {
		return auctionType;
	}
	public void setAuctionType(AuctionType auctionType) {
		this.auctionType = auctionType;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public BigDecimal getAverageBidAmount() {
		return averageBidAmount;
	}
	public void setAverageBidAmount(BigDecimal averageBidAmount) {
		this.averageBidAmount = averageBidAmount;
	}
	public double getMetricWeight() {
		return metricWeight;
	}
	public void setMetricWeight(double metricWeight) {
		this.metricWeight = metricWeight;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public BigDecimal getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(BigDecimal initialPrice) {
		this.initialPrice = initialPrice;
	}
}
