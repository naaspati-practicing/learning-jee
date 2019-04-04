package jeetutorial.dukesbookstore.cdi.managedbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import jeetutorial.dukesbookstore.jpa.entities.Item;
import jeetutorial.dukesbookstore.jpa.entities.ItemCount;

@Named
@SessionScoped
public class ShoppingCartItem implements Serializable, ItemCount {
	private static final long serialVersionUID = -6498436242770285490L;
	
	private Item item;
	private int quantity;
	
	public ShoppingCartItem(Item item) {
		this.item = item;
	}
	
	public Item getItem(){ return this.item; }
	public void setItem(Item item){ this.item = item; }

	public int getQuantity(){ return this.quantity; }
	public void setQuantity(int quantity){ this.quantity = quantity; }
	
	public void increamentQuantity() {
		quantity++;
	}
	public void decreamentQuantity() {
		quantity--;
	} 
}
