package javaeetutorial.order.entity;

import java.io.Serializable;
import java.util.Arrays;

public class LineItemKey implements Serializable {
	private static final long serialVersionUID = 5503507630439346309L;
	
	private int customerOrder;
	private int itemId;
	
	public LineItemKey() { }

	public LineItemKey(int customerOrder, int itemId) {
		this.customerOrder = customerOrder;
		this.itemId = itemId;
	}
	
	public int getCustomerOrder() {
		return customerOrder;
	}
	public void setCustomerOrder(int customerOrder) {
		this.customerOrder = customerOrder;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	@Override
	public int hashCode() {
		return Arrays.hashCode(new int[]{getCustomerOrder(), getItemId()});
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof LineItemKey))
			return false;
		LineItemKey e = (LineItemKey) obj;
		
		return this.getCustomerOrder() == e.getCustomerOrder() && this.getItemId() == e.getItemId(); 
	}

	@Override
	public String toString() {
		return "LineItemKey [customerOrder=" + customerOrder + ", itemId=" + itemId + "]";
	}
}
