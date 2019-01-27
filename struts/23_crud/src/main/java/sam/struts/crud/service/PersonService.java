package sam.struts.crud.service;

import sam.struts.crud.model.Country;
import sam.struts.crud.model.Person;

public interface PersonService {
	Person getPerson(Integer id);
	Iterable<Person> getAllPersons();
	void updatePerson(Person person);
	void insertPerson(Person person);
	void deletePerson(Person person);
	void deletePerson(int id);
	Country[] getCountries();
	String[] getCarModels();
	String[] getSports();
	String[] getGenders();
}
