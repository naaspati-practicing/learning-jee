package sam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sam.jpa.entity.Message;
import sam.jpa.entity.Message_;

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
			
			EntityTransaction t = em.getTransaction();
			t.begin();
			
			Message msg = new Message("Hello Sameer");
			out.println("before persist: "+msg);
			em.persist(msg);
			
			t.commit();
			out.println("after persist: "+msg);
			out.println("----------messages: ---------");
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<String> q = cb.createQuery(String.class);
			Root<Message> root = q.from(Message.class);
			q.select(root.get(Message_.text));
			
			em.createQuery(q).getResultList().forEach(s -> out.println(s));
			
		} catch (Exception e) {
			e.printStackTrace(out);
		} finally {
			if(em != null)
				em.close();
			if(factory != null)
				factory.close();
		}
	}
	
	private void persist(EntityManager em, Object...entities) {
		for (Object o : entities) 
			em.persist(o);
	}
}
