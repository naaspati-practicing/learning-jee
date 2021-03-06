package sam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jpwh.model.associations.onetomany.embeddable.Address;
import org.jpwh.model.associations.onetomany.embeddable.Shipment;
import org.jpwh.model.associations.onetomany.embeddable.User;

public class SamServlet extends HttpServlet {
	private static final long serialVersionUID = 8232611888100439902L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory factory = null;
		EntityManager em = null;
		final PrintWriter out = resp.getWriter();
	
		try {
			factory = Persistence.createEntityManagerFactory("DSTest");
			em = factory.createEntityManager();
			
			EntityTransaction t = em.getTransaction();
			t.begin();
			
			User user = new User();
			user.setUsername("username");
			Address address = new Address("street", "zipco", "city");
			user.setShippingAddress(address);
			
			Shipment item = new Shipment();
			Shipment item2 = new Shipment();
			
			address.getDeliveries().add(item);
			address.getDeliveries().add(item2);
			
			out.println("before persist: "+user);
			
			em.persist(user);
			em.persist(item2);
			em.persist(item);
			t.commit();
			
			out.println("after persist: "+user);
		} catch (Exception e) {
			e.printStackTrace(out);
		} finally {
			if(em != null)
				em.close();
			if(factory != null)
				factory.close();
		}
	}

}
