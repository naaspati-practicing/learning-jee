package sam.hibernate.entity;

import javax.persistence.Entity;

@Entity
public class Bid {
	protected Item item;

	public Item getItem() { return item; }
	public void setItem(Item item) { this.item = item; }

}
