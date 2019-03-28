package sam.learn.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sam.learn.ejb.jms.OrderProcessFacadeBean;
import sam.learn.ejb.jpa.Customer;
import sam.learn.ejb.jpa.Item;
import sam.learn.ejb.jpa.ItemsCart;

@Stateful
public class ShoppingCartBean implements ShoppingCartLocal  {
	@PersistenceContext 
	private EntityManager em;

	private Customer customer;

	@EJB private CustomerFacadeBean customerFacade;
	@EJB private OrderProcessFacadeBean orderProcessFacade;

	@Override
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public void addItem(Item item,  int quantity) {
		customer.addCartItem(new ItemsCart(quantity, item));
	}
	@Override
	public void removeWineItem(ItemsCart cartItem) {
		customer.removeCartItem(cartItem);
	}
	@Override
	public Customer findCustomer(String email) {
		return customer = customerFacade.getCustomerByEmail(email);
	}
	@Override
	public String sendOrderToOPC() {
		String result = null;
		try {
			orderProcessFacade.processOrder(customer);
			result = "Your Order has been submitted - you will be notified about the status via email";
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "An error occurred while processing your order.  Please contact Customer Service.";
		}

		return result;
	}

	@Override
	public List<ItemsCart> getCartItems() {
		return customer.getItemsCart();
	}
}
