package com.actionbazaar.persistence;

public class Shipping extends BaseEntity {
	private static final long serialVersionUID = -3132167062701814748L;
	
	private double cost;
	
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

}
