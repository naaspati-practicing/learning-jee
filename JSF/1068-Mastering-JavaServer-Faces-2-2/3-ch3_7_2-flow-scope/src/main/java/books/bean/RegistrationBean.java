package books.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@Named
@FlowScoped("registration")
public class RegistrationBean implements Serializable {
	private static final long serialVersionUID = -4876232435767498875L;
	
	private String name, surname;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getReturnValue() {
		return "/done";
	}
	public String registrationAction() {
		if(Math.random() < 0.5) {
			name = "";
			surname = "";
			FacesContext.getCurrentInstance()
			.addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "failed registration", ""));
			
			return "registration";
		} else 
			return "confirm";
	}
}
