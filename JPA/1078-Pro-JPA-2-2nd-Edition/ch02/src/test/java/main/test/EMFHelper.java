package main.test;

import java.lang.management.ManagementFactory;
import java.util.concurrent.Callable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class EMFHelper {
	protected static EntityManagerFactory factory;

	@BeforeAll
	public static void init() {
		if(factory != null)
			throw new IllegalStateException("already init");
			
		factory = Persistence.createEntityManagerFactory("H2PU");
		System.out.println("EntityManagerFactory: init");
		
		System.err.println(ManagementFactory.getRuntimeMXBean().getInputArguments());
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
	
	protected <E> E transect(EntityManager em, Callable<E> action) throws Exception {
		EntityTransaction t = em.getTransaction();
		t.begin();
		E e = action.call();
		t.commit();
		return e;
	}
}
