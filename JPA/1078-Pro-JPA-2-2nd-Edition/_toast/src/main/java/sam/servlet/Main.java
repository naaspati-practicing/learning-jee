package sam.servlet;

import static j2html.TagCreator.body;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sam.jpa.entity.HelloMessage;

@WebServlet("/")
public class Main extends HttpServlet {
	private static final long serialVersionUID = -3059782130790130417L;

    private EntityManagerFactory emf;
	
	@PostConstruct
	public void main_init() {
		if(emf != null)
			throw new IllegalStateException("already initialied");
		
		emf = Persistence.createEntityManagerFactory("PostgresDS");
		System.out.println("EntityManagerFactory created: "+emf);
	}
	
	@PreDestroy
	public void main_clean() {
		if(emf != null)
			emf.close();
		emf = null;
		System.out.println("EntityManagerFactory closed");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();

		HelloMessage m = new HelloMessage();
		m.setMsg("hello world");

		em.persist(m);
		t.commit();
		
		try(Writer w = resp.getWriter();
				) {
			html(head(title("hello world")), body(
					h1(m.toString())),
					h2("EntityManagerFactory properties"),
					table(each(emf.getProperties().entrySet(), e -> tr(td(e.getKey()), td(String.valueOf(e.getValue()))))),
					h2("EntityManager properties"),
					table(each(em.getProperties().entrySet(), e -> tr(td(e.getKey()), td(String.valueOf(e.getValue())))))
					).render(w);	
		}

		em.close();
	}
}
