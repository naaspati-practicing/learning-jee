package javaeetutorial.rsvp.entity;

import java.util.Objects;

interface Helper {
	Long getId();
	
	default String toString0() {
		return getClass().getName()+"@"+getId();
	}
	default int hashCode0() {
		return Objects.hashCode(getId());
	}
	/** does'nt work if getId() == null
	 * 
	 */
	default boolean equals0(Object obj) {
		return obj != null && 
				getId() != null && 
				getClass().isInstance(obj) &&
				Objects.equals(((Helper)obj).getId(), getId());
	}
}
