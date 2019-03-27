package sam.learn.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class ShoppingCartBean {
	private List<String> cartItems;
	
	{
		Utils.log("Construct", this);
	}

	@PostConstruct
	public void init() {
		Utils.log("POST-INIT", this);
		cartItems = new ArrayList<>();
	}
	public void addWineItem(String wine) {
		cartItems.add(wine);
	}

	public void removeWineItem(String wine) {
		cartItems.remove(wine);
	}

	public void setCartItems(List<String> cartItems) {
		this.cartItems = cartItems;
	}
	public List<String> getCartItems() {
		return cartItems;
	}
	@PreDestroy
	public void cleanup() {
		Utils.log("DESTROY", this);
		// save cartitems in DB
	}
	
	@Remove
	public void remove() {
		Utils.log("REMOVE", this);
	}
}
