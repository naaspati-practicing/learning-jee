package org.glassfish.jersey.examples.entityfiltering.security.domain;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Restricted entity to demonstrate various security annotations.
 *
 * @author Michal Gajdos
 */
@XmlRootElement
public class RestrictedEntity {
	private String simpleField;
	private String denyAll;
	private String permitAll;
	private RestrictedSubEntity mixedField;
	
	public String getSimpleField() {
		return simpleField;
	}
	
	@DenyAll
	public String getDenyAll() {
		return denyAll;
	}
	@PermitAll
	public String getPermitAll() {
		return permitAll;
	}
	@RolesAllowed({Roles.MANAGER, Roles.USER})
	public RestrictedSubEntity getMixedField() {
		return mixedField;
	}
	
	public void setSimpleField(String simpleField) {
		this.simpleField = simpleField;
	}
	public void setDenyAll(String denyAll) {
		this.denyAll = denyAll;
	}
	public void setPermitAll(String permitAll) {
		this.permitAll = permitAll;
	}
	public void setMixedField(RestrictedSubEntity mixedField) {
		this.mixedField = mixedField;
	}
	
	public static RestrictedEntity dummyInstance() {
		final RestrictedEntity entity = new RestrictedEntity();

        entity.setSimpleField("Simple Field.");
        entity.setDenyAll("Deny All.");
        entity.setPermitAll("Permit All.");

        final RestrictedSubEntity mixedField = new RestrictedSubEntity();
        mixedField.setManagerField("Manager's Field.");
        mixedField.setUserField("User's Field.");

        entity.setMixedField(mixedField);

        return entity;
	}
	
}
