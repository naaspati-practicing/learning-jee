package org.glassfish.jersey.examples.xmlmoxy;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.examples.xmlmoxy.beans.Customer;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Before;
import org.junit.Test;

import sam.jersey.app.App;

public class MoxyAppTest extends JerseyTest {
	private static final Logger LOGGER = Logger.getLogger(MoxyAppTest.class.getName());

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new App();
	}

	@Override
	protected void configureClient(ClientConfig config) {
		config.register(new MoxyXmlFeature(App.moxyClasses()));
	}
	
	Marshaller mars;
	StringWriter writer;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Before
	public void init() throws JAXBException {
		Map properties = new HashMap();
		properties.put(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
		mars = JAXBContextFactory.createContext(App.moxyClasses(), properties).createMarshaller();
		mars.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		writer  = new StringWriter();
	}
	
	@Test
	public void testCustomer() throws JAXBException {
		WebTarget target = target().path("customer");
		Customer customer = target.request(MediaType.APPLICATION_XML).get(Customer.class);
		
		LOGGER.info("recieved: "+toJson(customer));

		customer.setName("Tom Dooley");
		int status = target.request(MediaType.APPLICATION_XML).put(Entity.entity(customer, MediaType.APPLICATION_XML)).getStatus();

		assertEquals(204, status);

		LOGGER.info("send: "+toJson(customer));

		Customer updatedCustomer = target.request(MediaType.APPLICATION_XML).get(Customer.class);
		assertEquals(customer, updatedCustomer);

		LOGGER.info("recieved again: "+toJson(updatedCustomer));
	}

	private String toJson(Customer customer) throws JAXBException {
		writer.getBuffer().setLength(0);
		mars.marshal(customer, writer);
		return writer.toString();
	}
}
