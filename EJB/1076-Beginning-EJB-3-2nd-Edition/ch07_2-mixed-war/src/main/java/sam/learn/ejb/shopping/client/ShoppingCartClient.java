package sam.learn.ejb.shopping.client;

import javax.ejb.EJB;

import sam.learn.ejb.ShoppingCartLocal;
import sam.learn.ejb.WineSearchFacadeLocal;

/**
 * FIXME 
 * 
 * incomplete
 *
 */
public class ShoppingCartClient {
	@EJB private WineSearchFacadeLocal searchFacade;
	@EJB private ShoppingCartLocal shoppingCart;
	

}
