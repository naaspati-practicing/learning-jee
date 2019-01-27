package sam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jpwh.model.associations.maps.Bid;
import org.jpwh.model.associations.maps.Item;

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

			Item item1 = new Item("ITEM 1");
			print(out, item1);
			persist(em, item1);
			
			Bid bid1 = new Bid(new BigDecimal("1.2"));
			Bid bid2 = new Bid(new BigDecimal("1.21"));
			bid1.setItem(item1);
			bid2.setItem(item1);
			
			persist(em, bid1, bid2);
			
			item1.getBids().put(bid1.getId(), bid1);
			item1.getBids().put(bid2.getId(), bid2);
			
			print(out, item1);
			
			t.commit();
		} catch (Exception e) {
			e.printStackTrace(out);
		} finally {
			if(em != null)
				em.close();
			if(factory != null)
				factory.close();
		}
	}

	private void print(PrintWriter out, Object...objs) {
		for (Object os : objs)
			out.println(os);
	}

	private void persist(EntityManager em, Object...entities) {
		for (Object o : entities) 
			em.persist(o);
	}

}
