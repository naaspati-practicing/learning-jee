package sam.learn.ejb;

import java.util.List;

import javax.ejb.Local;

import sam.learn.ejb.jpa.Wine;

@Local
public interface WineSearchFacadeLocal {
	Object queryByRange(String jpql, int offset, int length);
	<T> void persist(List<T> list);
	<T> T persist(T t);
	<T> T merge(T t);
	List<Wine> getWineFindAll();
	List<Wine> getWineFindByYear(int year);
	List<Wine> getWineFindByCountry(String country);
	List<Wine> getWineFindByVarietal(String varietal);
}