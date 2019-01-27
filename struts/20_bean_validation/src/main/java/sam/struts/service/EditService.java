package sam.struts.service;

import sam.struts.model.Person;

public interface EditService {
	
	
	Person getPerson() ;

	void savePerson(Person personBean);

}
