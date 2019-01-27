package books.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class PlayersBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlayersBean.class);

	private String name, surname;
	private FacesContext facesContext;

	public String getName() {
		LOGGER.info("getName(): "+name);
		return name;
	}
	public void setName(String name) {
		LOGGER.info("setName({}): ",name);
		this.name = name;
	}
	public String getSurname() {
		LOGGER.info("getSurname(): "+surname);
		return surname;
	}
	public void setSurname(String surname) {
		LOGGER.info("setSurname({}): ",surname);
		this.surname = surname;
	}
	
	@PostConstruct
	public void init() {
		facesContext = FacesContext.getCurrentInstance();
	}
	
	
	public String validateData() {
		if(Math.random() < 0.5)
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Player name/surname validation failed.",""));
		
		LOGGER.info("validateData()");
		return "index";
	}
}
