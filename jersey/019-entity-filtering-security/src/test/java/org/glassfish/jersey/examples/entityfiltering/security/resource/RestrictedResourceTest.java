package org.glassfish.jersey.examples.entityfiltering.security.resource;

import static org.glassfish.jersey.examples.entityfiltering.security.domain.Roles.MANAGER;
import static org.glassfish.jersey.examples.entityfiltering.security.domain.Roles.USER;
import static org.glassfish.jersey.examples.entityfiltering.security.resource.RestrictedResource.PATH;
import static org.glassfish.jersey.examples.entityfiltering.security.resource.RestrictedResource.PATH_DENY_ALL;
import static org.glassfish.jersey.examples.entityfiltering.security.resource.RestrictedResource.PATH_PERMIT_ALL;
import static org.glassfish.jersey.examples.entityfiltering.security.resource.RestrictedResource.PATH_ROLES_ALLOWED;
import static org.glassfish.jersey.examples.entityfiltering.security.resource.RestrictedResource.PATH_RUNTIME_ROLES_ALLOWED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.examples.entityfiltering.security.domain.RestrictedEntity;
import org.glassfish.jersey.examples.entityfiltering.security.domain.RestrictedSubEntity;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Ignore;
import org.junit.Test;

import sam.jersey.app.App;

// FIXME -- 
// needs a ways to set role
// possibly 
public class RestrictedResourceTest extends JerseyTest {
	@Override
	protected Application configure() {
		enable(TestProperties.DUMP_ENTITY);
		enable(TestProperties.LOG_TRAFFIC);
		return new App();
	}

	private WebTarget resolve(String path ){
		return target().path(PATH).path(path);
	}
	private int requestStatus(String path ){
		return target().path(PATH).path(path).request().get().getStatus();
	}
	private <E> E get(String path, Class<E> cls ){
		return target().path(PATH).path(path).request().get(cls);
	}
	@Test
	public void testDenyAll() {
		assertEquals(requestStatus(PATH_DENY_ALL), Status.FORBIDDEN.getStatusCode());
	}

	@Test
	public void testPermitAll() {
		RestrictedEntity re = get(PATH_PERMIT_ALL, RestrictedEntity.class);
		RestrictedSubEntity rse = re.getMixedField();

		assertNotNull(re.getSimpleField());
		assertNotNull(re.getPermitAll());

		assertNull(re.getDenyAll());
		assertNull(rse);
	}


	@Ignore @Test
	public void testRolesAllowed() {
		roleTest(get(PATH_ROLES_ALLOWED, RestrictedEntity.class), MANAGER);
	}
	
	private static final String INVALID_ROLE = "invalid";

	private void roleTest(RestrictedEntity entity,  String role) {
		RestrictedSubEntity mixedField = entity.getMixedField();
		String msg = "role: "+role;

		assertNotNull(msg, entity.getSimpleField());
		assertNotNull(msg, entity.getPermitAll());
		assertNull(msg, entity.getDenyAll());
		
		if(role == INVALID_ROLE)
			assertNull(msg, mixedField);
		else {
			assertNotNull(msg, mixedField);
			assertNotNull(msg, mixedField.getUserField());
		}
		
		if(role == MANAGER)
			assertNotNull(msg, mixedField.getManagerField());
		if(role == USER)
			assertNull(msg, mixedField.getManagerField());
	}

	@Test
	public void testRuntimeRolesAllowed() {
		_testRuntimeRolesAllowed(USER);
		_testRuntimeRolesAllowed(MANAGER);
		_testRuntimeRolesAllowed(INVALID_ROLE);
	}
	private void _testRuntimeRolesAllowed(String role) {
		RestrictedEntity entity = resolve(PATH_RUNTIME_ROLES_ALLOWED).queryParam("roles", role).request().get(RestrictedEntity.class);
		roleTest(entity, role);

	}
}
