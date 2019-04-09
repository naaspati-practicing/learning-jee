package sam.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HelloMessageTest {
	private static EntityManagerFactory factory;

	@BeforeAll
	public static void init() {
		factory = Persistence.createEntityManagerFactory("H2PU");
		System.out.println("EntityManagerFactory: init");
	}

	@AfterAll
	public static void cleanup() {
		if(factory != null)
			factory.close();
		
		factory = null;
		System.out.println("EntityManagerFactory: closed");
	}
	
	public static EntityManager newEntityManager() {
		return factory.createEntityManager();
	}

	@Test
	void test() {
		EntityManager em = newEntityManager();
		
		try {
			HelloMessage m = new HelloMessage();
			m.setMsg("msg");
			
			EntityTransaction t = em.getTransaction();
			
			System.err.println(m);
			
			t.begin();
			em.persist(m);
			t.commit();
			
			System.err.println(m);
			
			em.clear();
			
			HelloMessage m2 = em.find(HelloMessage.class, m.getId());
			assertNotSame(m, m2);
			assertEquals(m.getId(), m2.getId());
			assertEquals(m.getMsg(), m2.getMsg());
			
			System.err.println(m2);	
		} finally {
			em.close();	
		}
	}
}
