package sam.struts.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.opensymphony.xwork2.ActionSupport;

import sam.struts.model.Person;
import sam.struts.model.State;
import sam.struts.service.EditService;
import sam.struts.service.EditServiceInMemory;

public class EditAction extends ActionSupport {
	private static final long serialVersionUID = 5132766564328737980L;
	

	private EditService editService = new EditServiceInMemory();
	
	@Valid
	private Person personBean;
	
	private String[] sports = {"football", "baseball", "basketball"};
	private String[] genders = {"male", "female", "not sure"};
	private List<State> states;
	private String[] carModelsAvailable = {"Ford", "Chrysler", "Toyota", "Nissan"};
	
	@Override
	public String execute() throws Exception {
		editService.savePerson(getPersonBean());
		return SUCCESS;
	}
	@Override
	public String input() throws Exception {
		setPersonBean(editService.getPerson());
		return INPUT;
	}
	public Person getPersonBean() {
		return personBean;
	}
	public void setPersonBean(Person person) {
		personBean = person;
	}
	public List<String> getSports() {
		return Arrays.asList(sports);
	}

	public List<String> getGenders() {
		return Arrays.asList(genders);
	}
	public List<State> getStates() {
		states = new ArrayList<>();
		states.add(new State("AZ", "Arizona"));
		states.add(new State("CA", "California"));
		states.add(new State("FL", "Florida"));
		states.add(new State("KS", "Kansas"));
		states.add(new State("NY", "New York"));

		return states;
	}
	public String[] getCarModelsAvailable() {
		return carModelsAvailable;
	}

}
