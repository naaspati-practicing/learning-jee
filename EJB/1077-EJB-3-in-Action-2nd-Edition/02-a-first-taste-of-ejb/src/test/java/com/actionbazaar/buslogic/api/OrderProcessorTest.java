package com.actionbazaar.buslogic.api;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.actionbazaar.buslogic.impl.OrderProcessorBean;
import com.actionbazaar.persistence.Bid;
import com.actionbazaar.persistence.Bidder;
import com.actionbazaar.persistence.Billing;
import com.actionbazaar.persistence.Item;
import com.actionbazaar.persistence.Shipping;

@RunWith(Arquillian.class)
public class OrderProcessorTest {
	
	@EJB private ItemService itemService;
	@EJB private OrderProcessor orderProcessor;
	@EJB private UserService userService;
	
	@Deployment
	public static Archive<?> pack() {
		return ShrinkWrap.create(JavaArchive.class, "foo.jar")
				.addPackage(OrderProcessor.class.getPackage())
				.addPackage(OrderProcessorBean.class.getPackage())
				.addPackage(Bid.class.getPackage())
				.addAsManifestResource("test-persistence.xml", ArchivePaths.create("persistence.xml"));
	}
	
	@Test
	public void testItemPersistence() {
		Random r = new Random();
		
		Item item = new Item();
		item.setName("IPhone 78");
		item.setInitialPrice(r.nextDouble() * 100);
		item.setBidStartDate(LocalDateTime.now());
		item.setBidEndDate(LocalDateTime.now());
		item.setCreationDate(LocalDateTime.now());
		
		itemService.addItem(item);
		
		Bidder bidder = new Bidder();
		bidder.setBirthDate(LocalDate.now());
		bidder.setCreditRating(r.nextInt());
		bidder.setFirstName("sameer");
		bidder.setLastName("veda");
		
		userService.addUser(bidder);
		
		assertNotEquals(bidder.getId(), 0);
		
		orderProcessor.setItem(item);
		orderProcessor.setBidder(bidder);
		
		
		// Get the shipping history of the test bidder
        List<Shipping> shippingChoices = orderProcessor.getShippingChoices();
        assertNotNull(shippingChoices);
        
        // Choose the first one in the list
        orderProcessor.setShipping(shippingChoices.get(0));

        // Get the billing history of the test bidder
        List<Billing> billingChoices = orderProcessor.getBillingChoices();

        // Choose the first one in the list
        orderProcessor.setBilling(billingChoices.get(0));

        // Finish the workflow and end the stateful session
        orderProcessor.placeOrder();
	}

}
