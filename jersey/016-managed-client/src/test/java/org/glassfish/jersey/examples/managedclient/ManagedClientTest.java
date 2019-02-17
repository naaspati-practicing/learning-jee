package org.glassfish.jersey.examples.managedclient;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import sam.jersey.app.App;

/**
 * Jersey managed client example tests.
 *
 * @author Marek Potociar (marek.potociar at oracle.com)
 */

public class ManagedClientTest extends JerseyTest {
	@Override
	protected Application configure() {
		enable(TestProperties.DUMP_ENTITY);
		enable(TestProperties.LOG_TRAFFIC);

		App app = new App();

		// overriding ClientA base Uri property for test purposes
		app.property(ClientA.class.getName().concat(".baseUri"), getBaseUri().toString().concat(InternalResource.PATH));
		return app;
	}


	//  Uncomment to use Grizzly async client
	//  @Override
	//  protected void configureClient(ClientConfig clientConfig) {
	//      clientConfig.connector(new GrizzlyConnector(clientConfig));
	//  }

	/**
	 * Test that a connection via managed clients works properly.
	 *
	 * @throws Exception in case of test failure.
	 */
	@Test
	public void testManagedClient() throws Exception {
		WebTarget target = target().path("public").path("{name}");

		check(target, "a");
		check(target, "b");
	}

	private void check(WebTarget target, String value) {
		Response response = target.resolveTemplate("name", value).request(MediaType.TEXT_PLAIN).get();
		
		assertEquals(200, response.getStatus());
		assertEquals(value.toUpperCase(), response.readEntity(String.class));
	}
	
}
