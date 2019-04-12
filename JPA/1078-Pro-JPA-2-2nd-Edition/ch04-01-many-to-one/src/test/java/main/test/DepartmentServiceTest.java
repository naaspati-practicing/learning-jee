package main.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import com.thedeanda.lorem.LoremIpsum;

import ch04.examples.ejb.DepartmentService;
import ch04.examples.ejb.DepartmentServiceBean;
import ch04.examples.model.Department;
import ch04.examples.model.Employee;

public class DepartmentServiceTest extends EMFHelper {
	@Test
	public void test () throws Exception {
		EntityManager em = newEntityManager();
		DepartmentService service = new DepartmentServiceBean(em);
		LoremIpsum lorem = LoremIpsum.getInstance();
		Random r = new Random();
		
		Department[] departs = new Department[10];
		for (int i = 0; i < departs.length; i++) {
			departs[i] = service.addDepartment("depart: " + i);
			System.err.println(departs[i]);
		}
		
		System.err.println("---------------------------------------");
		ArrayList<Employee> employees = new ArrayList<>();
		
		try {
			transect(em, () -> {
				for (int i = 0; i < 1000; i++) {
					Employee e;
					employees.add(e = service.addEmployee(departs[r.nextInt(departs.length)].getId(), new Employee(lorem.getName(), (int)(r.nextDouble() * 100000)/100d)));
					System.err.println(e);
				} 
				return null;
			});
			
			Collections.shuffle(employees, r);
			assertFalse(employees.isEmpty());
			
			System.err.println(employees.size());
			
			for (Employee e : employees) {
				Employee f = service.getEmployee(e.getId());
				assertSame(e, f);
				assertSame(e.getDepartment(), f.getDepartment());
			}
			
			em.clear();
			
			for (Employee e : employees) {
				Employee f = service.getEmployee(e.getId());
				assertNotSame(e, f);
				assertNotSame(e.getDepartment(), f.getDepartment());
				
				assertEquals(e.getId(), f.getId());
				assertEquals(e.getName(), f.getName());
				assertEquals(e.getDepartment().getId(), f.getDepartment().getId());
				assertEquals(e.getDepartment().getName(), f.getDepartment().getName());
			}
			
		} finally {
			em.close();
		}
	}
}
