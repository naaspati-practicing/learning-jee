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

import org.jpwh.model.inheritance.join.BankAccount;
import org.jpwh.model.inheritance.join.CreditCard;


public class SamServlet extends HttpServlet {
	private static final long serialVersionUID = 8232611888100439902L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory factory = null;
		EntityManager em = null;
		
		try {
			factory = Persistence.createEntityManagerFactory("DSTest");
			em = factory.createEntityManager();
			
			EntityTransaction t = em.getTransaction();
			t.begin();
			
			CreditCard card = new CreditCard("owner", "cardNumber", "expMonth", "expYear");
			BankAccount account = new BankAccount("owner_AC", "account_AC", "bankname_AC", "swift_AC");
			em.persist(card);
			em.persist(account);
			t.commit();
			
			resp.getWriter().println(card);
			resp.getWriter().println(account);
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
