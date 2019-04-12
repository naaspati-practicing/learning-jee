package main.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Random;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import com.thedeanda.lorem.LoremIpsum;

import ch02.examples.model.Employee;
import ch02.examples.model.EmployeeService;

public class EmployeeServiceTest extends EMFHelper {
	@Test
	public void test () throws Exception {
		EntityManager em = newEntityManager();
		EmployeeService service = new EmployeeService(em);
		LoremIpsum lorem = LoremIpsum.getInstance();
		Random r = new Random();
		
		try {
			final Employee employee = transect(em, () -> {
				Employee e = new Employee(lorem.getName(), r.nextDouble() * 1000);
				service.persist(e);
				return e;
			});
			System.err.println("Persisted: "+employee+"@"+System.identityHashCode(employee));
			
			Employee e2 = service.find(employee.getId());
			
			System.err.println("Find-1: "+e2+"@"+System.identityHashCode(e2));
			assertSame(employee, e2);
			
			em.clear();
			
			e2 = service.find(employee.getId());
			
			System.err.println("Find-2: "+e2+"@"+System.identityHashCode(e2));
			
			assertNotSame(employee, e2);
			assertEquals(employee.getId(), e2.getId());
			assertEquals(employee.getName(), e2.getName());
			assertEquals(employee.getSalary(), e2.getSalary());
			
		} finally {
			em.close();
		}
	}
}
