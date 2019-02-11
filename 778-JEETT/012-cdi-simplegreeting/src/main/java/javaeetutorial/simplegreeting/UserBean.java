package javaeetutorial.simplegreeting;

import java.io.Serializable;
import java.time.LocalDate;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@SessionScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String firstName = "John";
	protected String lastName = "Doe";
	protected LocalDate dob ;
	protected String sex = "Unknown" ;
	protected String email;
	protected String serviceLevel = "medium";
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getServiceLevel() {
		return serviceLevel;
	}
	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}
	
	public void validateEmail(FacesContext fc, UIComponent toValidate, Object value) {
		String emailStr = (String) value;
        if (emailStr.indexOf("@") < 0) 
            throw new ValidatorException(new FacesMessage("Invalid email address"));
	}
    public String addConfirmedUser() {
        // This method would call a database or other service and add the 
        // confirmed user information.
        // For now, we just place an informative message in request scope
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successfully added new user"));
        return "done";
    }
}
