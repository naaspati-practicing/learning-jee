package sam.struts.crud.dao;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sam.struts.crud.model.Person;

public class InMemoryPersonDao implements PersonDao {
	private static final Logger LOG = LogManager.getLogger(InMemoryPersonSupportDao.class.getSimpleName());
	private static final LinkedHashMap<Integer, Person> persons = new LinkedHashMap<>();
	private static final AtomicInteger IDS = new AtomicInteger(1);

	static {
		persons.put(1,new Person(1, "Bruce", "Phillips", "basketball", "male", InMemoryPersonSupportDao.getCountry("US"), true, new String[]{"Ford", "Nissan"}, "bphillips@ku.edu", "123-456-9999"));
		persons.put(2,new Person(2, "Antonio", "Sanchez", "mtb", "male", InMemoryPersonSupportDao.getCountry("ES"), true, new String[]{"Toyota", "Seat"}, "asanchez@correo-e.es", "555-999-8888"));
	}

	@Override
	public Person getPerson(Integer id) {
		return persons.get(id);
	}
	@Override
	public Iterable<Person> getAllPersons() {
		return Collections.unmodifiableCollection(persons.values());
	}

	@Override
	public void updatePerson(Person person) {
		if(person == null)
			return;
		persons.compute(person.getPersonId(), (id, oldPerson) -> {
			person.setCountry(InMemoryPersonSupportDao.getCountry(person.getCountry().getCountryId()));
			return person;
		});
	}

	@Override
	public void insertPerson(Person person) {
		int id = IDS.incrementAndGet();
		person.setPersonId(id);
		person.setCountry(InMemoryPersonSupportDao.getCountry(person.getCountry().getCountryId()));
		persons.put(id, person);
	}

	@Override
	public void deletePerson(Person person) {
		deletePerson(person.getPersonId());
	}

	@Override
	public void deletePerson(int id) {
		persons.remove(id);
	}

}
