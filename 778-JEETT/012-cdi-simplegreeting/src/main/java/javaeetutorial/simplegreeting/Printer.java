package javaeetutorial.simplegreeting;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class Printer {
	@Informal @Inject
	private Greeting greeting;
	
	private String name;
	private String salutation;
	
	public void createSalutation() {
        this.salutation = greeting.greet(name);
    }
	public Greeting getGreeting() {
		return greeting;
	}
	public void setGreeting(Greeting greeting) {
		this.greeting = greeting;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
}
