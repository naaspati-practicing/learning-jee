package sam.hello.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class HelloBeanTest {
	
	@EJB
	HelloBean hello;
	
	@SuppressWarnings("rawtypes")
	@Deployment
	public static Archive pack() {
		return ShrinkWrap.create(JavaArchive.class, "page.jar")
				.addClass(HelloBean.class);
	}

	@Test
	public void test() {
		String s = "sameer";
		
		assertEquals(hello.sayHello(s), String.format("Hello %s!", s));
		assertSame(hello.sayHello(null), "Hello Stranger!");
	}

}
