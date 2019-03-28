package sam.learn.ejb;

import java.util.List;

import javax.ejb.Local;

import sam.learn.ejb.jpa.Customer;
import sam.learn.ejb.jpa.Item;
import sam.learn.ejb.jpa.ItemsCart;

@Local
public interface ShoppingCartLocal {
	Customer getCustomer();
	void addItem(Item item, int quantity);
	void removeWineItem(ItemsCart cartItem);
	Customer findCustomer(String email);
	String sendOrderToOPC();
	List<ItemsCart> getCartItems();

}