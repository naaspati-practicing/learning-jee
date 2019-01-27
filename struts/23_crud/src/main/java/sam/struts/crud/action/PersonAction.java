package sam.struts.crud.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import sam.struts.crud.model.Country;
import sam.struts.crud.model.Person;
import sam.struts.crud.service.DefaultPersonService;
import sam.struts.crud.service.PersonService;

public class PersonAction extends ActionSupport implements Preparable {
	private static final long serialVersionUID = -7775713234572866135L;
	
	private static final Logger LOG = LogManager.getLogger(PersonAction.class.getName());
    private PersonService personService = new DefaultPersonService();
    
    private Person person;
    private Iterable<Person> persons;
    private String[] sports;
    private String[] genders;
    private Country[] countries;
    private String[] carModelsAvailable;

	@Override
	public void prepare() throws Exception {
		carModelsAvailable = personService.getCarModels();
        sports = personService.getSports();
        countries = personService.getCountries();
        genders = personService.getGenders();
        LOG.info("Prepared support data for Person entity.");        
        
        if (person != null && person.getPersonId() != 0) {
            person = personService.getPerson(person.getPersonId());
            LOG.info("Preparing actual data for Person: " + person);
        }
	}
	
	/**
     * Get all persons for display in the view.
     */
    public String list() {
        persons = personService.getAllPersons();
        LOG.info("Listing persons");
        return SUCCESS;
    }
    

    /**
     * Save the state of the Person object instance field.
     */
    public String save() {
        if (person.getPersonId() == 0) {
            personService.insertPerson(person);
            LOG.info("Created new Person: " + person);
        } else {
            personService.updatePerson(person);
            LOG.info("Updated Person: " + person);
        }
        return SUCCESS;
    }
    

    /**
     * Delete from Person identified by the person
     * instance field's personId value.
     */
    public String delete() {
        personService.deletePerson(person);
        LOG.info("Deleted Person: " + person);
        return SUCCESS;
    }
    
    public Iterable<Person> getPersons() {
        return persons;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String[] getSports() {
        return sports;
    }

    public String[] getGenders() {
        return genders;
    }

    public Country[] getCountries() {
        return countries;
    }

    public String[] getCarModelsAvailable() {
        return carModelsAvailable;
    }

}
