package org.glassfish.jersey.examples.entityfiltering.filtering;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.glassfish.jersey.internal.inject.AnnotationLiteral;
import org.glassfish.jersey.message.filtering.EntityFiltering;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD })
@EntityFiltering
public @interface ProjectDetailedView {
    /**
     * Factory class for creating instances of {@code ProjectDetailedView} annotation.
     */
	public static class Factory extends AnnotationLiteral<ProjectDetailedView> implements ProjectDetailedView {
		private Factory() {
        }

        public static ProjectDetailedView newInstance() {
            return new Factory();
        }	
	}


}
