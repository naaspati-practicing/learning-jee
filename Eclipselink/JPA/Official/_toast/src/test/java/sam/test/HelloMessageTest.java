package sam.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import sam.hello.HelloMessage;

class HelloMessageTest extends EMFHelper {
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
