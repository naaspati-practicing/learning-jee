package sam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jpwh.model.associations.onetomany.list.Bid;
import org.jpwh.model.associations.onetomany.list.Item;

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
			
			Item item = new Item("some item");
			em.persist(item);
			
			out.println("before persist: "+item);
			
			Collection<Bid> bids = item.getBids();
			out.println("bids class: "+bids.getClass());
			Bid b1 = new Bid(new BigDecimal("123.00"), item);
			Bid b2 = new Bid(new BigDecimal("123.00"), item);
			
			bids.add(b1);
			bids.add(b2);
			
			em.persist(b1);
			em.persist(b2);
			
			t.commit();
			
			out.println("after persist: "+item);
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
