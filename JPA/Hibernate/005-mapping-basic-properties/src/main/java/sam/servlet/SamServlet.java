package sam.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jpwh.model.advanced.Address;
import org.jpwh.model.advanced.User;

public class SamServlet extends HttpServlet {
	private static final long serialVersionUID = 8232611888100439902L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory factory = null;
		EntityManager em = null;
		
		try {
			factory = Persistence.createEntityManagerFactory("DSTest");
			em = factory.createEntityManager();
			/*
			Address d = new Address("street", "11055", "city");
			Address d2 = new Address("billing_street", "11055", "billing_city");
			
			User user = new User("sameer", d, d2);
			
			resp.getWriter().println("before persist: "+user);
			em.persist(user);
			resp.getWriter().println("after persist: "+user);
			em.getTransaction().commit();
			*/
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
		} finally {
			if(em != null)
				em.close();
			if(factory != null)
				factory.close();
		}
	}

}
