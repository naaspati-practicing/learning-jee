package javaeetutorial.hello.appclient;

import java.util.logging.Logger;

import javax.xml.ws.WebServiceRef;

import javaeetutorial.helloservice.Hello;
import javaeetutorial.helloservice.HelloService;

public class HelloAppClient {
	@WebServiceRef(wsdlLocation = "http://localhost:8080/page/HelloService?wsdl")
	private static HelloService service;
	
	private static final Logger logger = Logger.getLogger(HelloAppClient.class.getName());
	
	public static void main(String[] args) {
		Hello hello = service.getHelloPort();
		logger.info("---------------------------- \""+hello.sayHello("jai mata di")+"\" ----------------------------");
	}
}
