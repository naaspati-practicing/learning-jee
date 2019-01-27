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

import sam.jpa.entity.Message;
import sam.jpa.entity.User;

public class Main extends HttpServlet {
	private static final long serialVersionUID = 8232611888100439902L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory factory = null;
		EntityManager em = null;
		final PrintWriter out = resp.getWriter();
		
		try {
			factory = Persistence.createEntityManagerFactory("DSTest");
			em = factory.createEntityManager();
			insert(em,out);
			// select(em,out);
		} catch (Exception e) {
			e.printStackTrace(out);
		} finally {
			if(em != null)
				em.close();
			if(factory != null)
				factory.close();
		}
	}
	
	private void select(EntityManager em, PrintWriter out) {
		out.println("------------- SELECT -----------");
		out.println(em.find(User.class, 0));
	}

	private void insert(EntityManager em, PrintWriter out) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		User user = new User();
		user.setName("sameer");
		
		Message msg = new Message("Hello Sameer");
		user.addMessage(msg);
		
		out.println("before persist: "+user);
		em.persist(user);
		t.commit();
		out.println("after persist: "+user);
	}

	private void persist(EntityManager em, Object...entities) {
		for (Object o : entities) 
			em.persist(o);
	}
}
