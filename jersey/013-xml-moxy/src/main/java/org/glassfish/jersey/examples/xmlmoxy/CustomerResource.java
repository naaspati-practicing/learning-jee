package org.glassfish.jersey.examples.xmlmoxy;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.examples.xmlmoxy.beans.Address;
import org.glassfish.jersey.examples.xmlmoxy.beans.Customer;
import org.glassfish.jersey.examples.xmlmoxy.beans.PhoneNumber;

@Path("/customer")
@Singleton
public class CustomerResource {
	private static final AtomicBoolean init = new AtomicBoolean();

	private Customer customer;

	@PostConstruct
	public void init() {
		if(init.get())
			throw new IllegalAccessError("already initialized");
		if(!init.compareAndSet(false, true))
			throw new IllegalAccessError("already initialized");

		customer = new Customer();
		customer.setName("Jane Doe");
		customer.setAddress(new Address("123 Any Street", "My Town"));
		customer.getPhoneNumbers().add(new PhoneNumber("work", "613-555-1111"));
		customer.getPhoneNumbers().add(new PhoneNumber("cell", "613-555-2222"));
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Customer getCustomer() {
		return customer;
	}
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
