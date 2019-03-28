package sam.learn.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sam.learn.ejb.jpa.Wine;

@Stateless
public class WineSearchFacadeBean implements WineSearchFacadeLocal {
	@PersistenceContext 
	private EntityManager em;

	@Override
	public Object queryByRange(String jpql, int offset, int length) {
		Query q = em.createQuery(jpql);
		if(offset < 0)
			offset = 0;
		if(offset != 0)
			q.setFirstResult(offset);
		if(length < 0)
			length = 0;

		if(length != 0)
			q.setMaxResults(length);

		return q.getResultList();
	}

	@Override
	public <T> void persist(List<T> list) {
		list.forEach(em::persist);
	}

	@Override
	public <T> T persist(T t) {
		em.persist(t);
		return t;
	} 
	@Override
	public <T> T merge(T t) {
		return em.merge(t);
	}
	@Override
	public List<Wine> getWineFindAll() {
		return em.createNamedQuery(Wine.FIND_ALL, Wine.class).getResultList();
	}
	private List<Wine> find(String namedQuery, String key, Object value) {
		return em.createNamedQuery(namedQuery, Wine.class).setParameter(key, value).getResultList(); 
	}
	@Override
	public List<Wine> getWineFindByYear(int year) {
		return find(Wine.FIND_BY_YEAR,"year", year);
	}
	@Override
	public List<Wine> getWineFindByCountry(String country) {
		return find(Wine.FIND_BY_COUNTRY,"country", country);
	}
	@Override
	public List<Wine> getWineFindByVarietal(String varietal) {
		return find(Wine.FIND_BY_VARIETAL,"varietal", varietal);
	}
}
