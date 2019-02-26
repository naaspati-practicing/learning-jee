package javaeetutorial.helloservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Hello {
	private final String message = "Hello, " ;
	
	@WebMethod
	public String sayHello(String name) {
		return message + name + "!";
	}

}
