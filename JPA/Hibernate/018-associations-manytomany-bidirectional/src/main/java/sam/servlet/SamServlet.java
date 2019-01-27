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

import org.jpwh.model.associations.manytomany.bidirectional.Category;
import org.jpwh.model.associations.manytomany.bidirectional.Item;

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
			
			c1.getItems().add(item1);
			item1.getCategories().add(c1);
			
			c2.getItems().add(item2);
			item2.getCategories().add(c2);
			
			em.persist(c1);
			em.persist(c2);
			t.commit();
			
			out.println(c1);
			out.println(c2);
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
