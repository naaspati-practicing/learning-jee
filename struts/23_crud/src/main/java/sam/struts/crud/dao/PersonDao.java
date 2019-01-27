package sam.struts.crud.dao;

import sam.struts.crud.model.Person;

public interface PersonDao {
	Person getPerson(Integer id);
	Iterable<Person> getAllPersons();
	void updatePerson(Person person);
	void insertPerson(Person person);
	void deletePerson(Person person);
	void deletePerson(int id);
}
