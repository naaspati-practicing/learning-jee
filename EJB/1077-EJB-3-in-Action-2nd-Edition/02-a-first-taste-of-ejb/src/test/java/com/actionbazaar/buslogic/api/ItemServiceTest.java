package com.actionbazaar.buslogic.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDateTime;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.actionbazaar.buslogic.impl.EmHelper;
import com.actionbazaar.buslogic.impl.ItemServiceBean;
import com.actionbazaar.buslogic.impl.OrderProcessorBean;
import com.actionbazaar.persistence.Bid;
import com.actionbazaar.persistence.Item;

@RunWith(Arquillian.class)
public class ItemServiceTest {
	
	@EJB
	private ItemService itemService;
	
	@Deployment
	public static Archive<?> pack() {
		return ShrinkWrap.create(JavaArchive.class, "foo.jar")
				.addClasses(
						OrderProcessor.class, OrderProcessorBean.class,
		                ItemService.class, ItemServiceBean.class,
		                EmHelper.class)
				.addPackage(Bid.class.getPackage())
				.addAsManifestResource("test-persistence.xml", ArchivePaths.create("persistence.xml"));
	}
	
	@Test
	public void testItemPersistence() {
		Item item = new Item();
		item.setName("Apple IIGS");
		item.setInitialPrice(45);
		item.setBidStartDate(LocalDateTime.now());
		item.setBidEndDate(LocalDateTime.now());
		item.setCreationDate(LocalDateTime.now());
		
		assertEquals(item.getId(), 0); 
		itemService.addItem(item);
		
		assertNotEquals(item.getId(), 0);
		System.out.println("new id: "+item.getId());
	}

}
