package org.glassfish.jersey.examples.entityfiltering.security.domain;

import javax.annotation.security.RolesAllowed;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Restricted sub-entity to demonstrate that security entity-filtering is transitive.
 *
 * @author Michal Gajdos
 */
@XmlRootElement
public class RestrictedSubEntity {
	private String managerField;
	private String userField;
	
	@RolesAllowed(Roles.MANAGER)
	public String getManagerField() {
		return managerField;
	}
	@RolesAllowed(Roles.USER)
	public String getUserField() {
		return userField;
	}
	public void setManagerField(String managerField) {
		this.managerField = managerField;
	}
	public void setUserField(String userField) {
		this.userField = userField;
	}
}
