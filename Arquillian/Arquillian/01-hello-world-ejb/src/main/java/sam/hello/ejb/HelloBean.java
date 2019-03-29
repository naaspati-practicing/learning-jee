package sam.hello.ejb;

import javax.ejb.Stateless;

@Stateless
public class HelloBean {
	
	public String sayHello(String to) {
		String s = to == null || to.isEmpty() ? "Hello Stranger!" : "Hello "+to+"!";
		System.out.println(s);
		return s;
	}
}
