package sam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sam.entity.Item;

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
			CriteriaBuilder cb = em.getCriteriaBuilder();

			example1(cb, em);

		} catch (Exception e) {
			e.printStackTrace(out);
		} finally {
			if(em != null)
				em.close();
			if(factory != null)
				factory.close();
		}
	}

	private void example1(CriteriaBuilder cb, EntityManager em) {
		//JPQL
		Query query = em.createQuery("select i from Item i");

		// ------------------------------------------

		// CriteriaQuery
		CriteriaQuery<Item> criteria = cb.createQuery(Item.class);
		Root<Item> root = criteria.from(Item.class);

		criteria.select(root);

		// ------------------------------------------
		// CriteriaQuery
		CriteriaQuery<Item> criteria2 = cb.createQuery(Item.class);
		criteria2.select(criteria2.from(Item.class));

		// ------------------------------------------
		// CriteriaQuery
		CriteriaQuery<Item> criteria3 = cb.createQuery(Item.class);
		criteria3.select(criteria3.from(em.getMetamodel().entity(Item.class)));

	}
	private void example2(CriteriaBuilder cb, EntityManager em) {
		//JPQL
		Query query = em.createQuery("select bd from BillingDetails bd where type(bd) = CreditCard");

		// ------------------------------------------

		// CriteriaQuery
		CriteriaQuery<Item> criteria = cb.createQuery(Item.class);
		Root<Item> root = criteria.from(Item.class);

		criteria.select(root);
	}

	private void persist(EntityManager em, Object...entities) {
		for (Object o : entities) 
			em.persist(o);
	}

}
