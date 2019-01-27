package javaeetutorial.helloservice;

import java.text.MessageFormat;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Hello {
	@WebMethod
	public String sayHello(String name) {
		return MessageFormat.format("Hello, {0}.", name);
	}

}
