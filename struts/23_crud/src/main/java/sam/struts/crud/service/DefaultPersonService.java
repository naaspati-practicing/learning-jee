package sam.struts.crud.service;

import sam.struts.crud.dao.InMemoryPersonDao;
import sam.struts.crud.dao.InMemoryPersonSupportDao;
import sam.struts.crud.dao.PersonDao;
import sam.struts.crud.dao.PersonSupportDao;
import sam.struts.crud.model.Country;
import sam.struts.crud.model.Person;

public class DefaultPersonService implements PersonService{
	private final PersonDao dao;
	private final PersonSupportDao supportDao;
	
	public DefaultPersonService() {
		dao = new InMemoryPersonDao();
		supportDao = new InMemoryPersonSupportDao();
	}

	@Override public Person getPerson(Integer id) {
		return dao.getPerson(id);
	}

	@Override public Iterable<Person> getAllPersons() {
		return dao.getAllPersons();
	}

	@Override public void updatePerson(Person person) {
		dao.updatePerson(person);
	}

	@Override public void insertPerson(Person person) {
		dao.insertPerson(person);
	}

	@Override public void deletePerson(Person person) {
		dao.deletePerson(person);
	}

	@Override public void deletePerson(int id) {
		dao.deletePerson(id);
	}

	@Override public Country[] getCountries() {
		return supportDao.getCountries();
	}

	@Override public String[] getCarModels() {
		return supportDao.getCarModels();
	}
	@Override public String[] getSports() {
		return supportDao.getSports();
	}
	@Override public String[] getGenders() {
		return supportDao.getGenders();
	}
}
