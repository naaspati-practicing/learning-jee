package sam.struts.service;

import sam.struts.model.Person;

/**
 * Implement Services needed to edit and save
 * a Person object's state.  In this implementation
 * the Person object's state is stored in memory
 * @author brucephillips
 *
 */

public class EditServiceInMemory implements EditService {
	
	private static Person person ;
	private static String [] carModels = {"Ford","Nissan"};

	static {
		person = new Person();
		person.setFirstName("Bruce");
		person.setLastName("Phillips");
		person.setEmail("bphillips@ku.edu");
		person.setSport("basketball");
		person.setGender("not sure");
		person.setResidency("KS");
		person.setOver21(true);		
		person.setCarModels( carModels);	
		person.setPhoneNumber("123-456-9999");
	}

	
	public Person getPerson() {
		return EditServiceInMemory.person;
	}
	public void savePerson(Person personBean) {
		person.setFirstName(personBean.getFirstName() );
		person.setLastName(personBean.getLastName() );
		person.setSport(personBean.getSport() );
		person.setGender( personBean.getGender() );
		person.setResidency( personBean.getResidency() );
		person.setOver21( personBean.isOver21() );
		person.setCarModels(personBean.getCarModels() );
		person.setEmail( personBean.getEmail() );
		person.setPhoneNumber( personBean.getPhoneNumber() );
	}

}
