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

import org.jpwh.model.associations.manytomany.linkentity.CategorizedItem;
import org.jpwh.model.associations.manytomany.linkentity.Category;
import org.jpwh.model.associations.manytomany.linkentity.Item;

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

			Category c1 = new Category("CAT_1");
			Category c2 = new Category("CAT_2");

			Item item1 = new Item("ITEM 1");
			Item item2 = new Item("ITEM 2");

			persist(em, c1, c2, item1, item2);

			CategorizedItem linkOne = new CategorizedItem( "johndoe", c1, item1 );
			CategorizedItem linkTwo = new CategorizedItem( "johndoe", c1, item2 );
			CategorizedItem linkThree = new CategorizedItem( "johndoe", c2, item1 );
			
			persist(em, linkOne, linkTwo, linkThree);
			t.commit();

			print(out, linkOne, linkTwo, linkThree);
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
