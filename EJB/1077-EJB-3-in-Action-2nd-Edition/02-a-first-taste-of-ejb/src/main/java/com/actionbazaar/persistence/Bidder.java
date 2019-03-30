package com.actionbazaar.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="BIDDERS")
public class Bidder extends User {
	private static final long serialVersionUID = 704952257523483467L;
		
	@Column(name="CREDIT_RATING", nullable=false)
	private long creditRating;
	
	public long getCreditRating() {
		return creditRating;
	}
	public void setCreditRating(long creditRating) {
		this.creditRating = creditRating;
	}
}
