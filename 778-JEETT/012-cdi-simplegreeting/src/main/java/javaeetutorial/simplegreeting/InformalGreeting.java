package javaeetutorial.simplegreeting;

import java.util.Objects;

import javax.enterprise.context.Dependent;

@Informal
@Dependent
public class InformalGreeting extends Greeting {
	public String greet(String name) {
		return "Hi, "+Objects.requireNonNull(name)+"!";
	}
}
