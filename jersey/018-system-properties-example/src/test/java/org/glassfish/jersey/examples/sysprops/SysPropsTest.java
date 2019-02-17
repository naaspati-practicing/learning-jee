package org.glassfish.jersey.examples.sysprops;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Before;
import org.junit.Test;

import sam.jersey.app.App;

public class SysPropsTest extends JerseyTest  {
	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		return new App();
	}
	@Override
	protected void configureClient(ClientConfig config) {
		config.register(PropertiesReader.class);
		config.register(PropertiesWriter.class);
	}
	
	PropertyNamesResource propertyNamesResource;
	@Before
	public void init() {
		propertyNamesResource = WebResourceFactory.newResource(PropertyNamesResource.class, target());
	}
	
	@Test
	public void testPropertyNames() {
		Set<String> names = propertyNamesResource.getPropertyNames();
		assertEquals(System.getProperties().keySet(), names);
	}

    @Test
    public void testGetProperty() {
        assertEquals(System.getProperty("java.home"), propertyNamesResource.getProperty("java.home").get());
    }
    @Test
    public void testSetProperty() {
    	propertyNamesResource.getProperty("test").set("this is a test");
        assertEquals(System.getProperty("test"), propertyNamesResource.getProperty("test").get());
    }
}
