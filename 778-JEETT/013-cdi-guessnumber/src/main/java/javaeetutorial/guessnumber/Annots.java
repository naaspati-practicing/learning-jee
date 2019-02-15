package javaeetutorial.guessnumber;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import javax.ws.rs.RuntimeType;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;

public interface Annots {
	@Target({TYPE, METHOD, PARAMETER, FIELD})
	@Retention(RUNTIME)
	@Documented
	@Qualifier
	public static @interface Random {}
	
	@Target({TYPE, METHOD, PARAMETER, FIELD})
	@Retention(RUNTIME)
	@Documented
	@Qualifier
	public static @interface MaxNumber {}
}
