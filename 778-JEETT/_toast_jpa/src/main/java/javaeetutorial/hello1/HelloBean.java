package javaeetutorial.hello1;

import java.io.Serializable;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Named
@Stateless
public class HelloBean implements Serializable {
	private static final long serialVersionUID = -7186702711769675555L;
	private static final Logger LOGGER = Logger.getLogger(HelloBean.class.getName());
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<HelloEntity> getAll() {
		return em.createQuery("SELECT h From HelloEntity h").getResultList();
	}
	
	public void put(String message) {
		HelloEntity h =  new HelloEntity(message);
		em.persist(h);
	}

	public void post(HelloEntity h, String message) {
		h.setMsg(message);
		em.merge(h);
	}
	public void delete(HelloEntity h) {
		HelloEntity h2 = em.merge(h);
		em.remove(h2);
	}
}
