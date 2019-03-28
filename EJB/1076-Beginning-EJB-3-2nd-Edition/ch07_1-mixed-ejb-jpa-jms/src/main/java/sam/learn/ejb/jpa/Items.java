package sam.learn.ejb.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name = "CH07_ITEMS")
public abstract class Items implements Serializable {
	private static final long serialVersionUID = 3527547382773118310L;

	@Id
	@Column(nullable=false)
	@GeneratedValue
	private int id;
	
	@Version
	private int version;
	
	private int quantity;
	private Item item;
	
	public Items() { }

	public Items(int quantity, Item item) {
		setQuantity(quantity);
		setItem(item);
	}

	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	
	public Item getItem() { return item; }
	public void setItem(Item item) { this.item = item; }
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getVersion() { return version; }
	public void setVersion(int version) { this.version = version; }
}
