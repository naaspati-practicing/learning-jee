package sam.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jpwh.model.inheritance.tableperclass.CreditCard;


public class SamServlet extends HttpServlet {
	private static final long serialVersionUID = 8232611888100439902L;
	private static Logger logger = LogManager.getLogger(SamServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory factory = null;
		EntityManager em = null;
		
		logger.info("working");
		
		try {
			factory = Persistence.createEntityManagerFactory("DSTest");
			em = factory.createEntityManager();
			
			EntityTransaction t = em.getTransaction();
			t.begin();
			
			CreditCard card = new CreditCard("owner", "cardNumber", "expMonth", "expYear");
			em.persist(card);
			t.commit();
			
			t.begin();
			long id = card.getId();
			card = em.find(CreditCard.class, id);
			em.flush();
			card = em.find(CreditCard.class, id);
			
			em.clear();
			
			card = em.find(CreditCard.class, id);
			
			t.commit();
			
			resp.getWriter().println(card);
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
