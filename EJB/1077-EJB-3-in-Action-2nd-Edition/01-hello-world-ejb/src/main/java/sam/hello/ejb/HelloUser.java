package sam.hello.ejb;

import javax.ejb.Local;

@Local
public interface HelloUser {
	String sayHello(String to);
}