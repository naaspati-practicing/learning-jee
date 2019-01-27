package books.bean;

import javax.enterprise.inject.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class PlayersBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlayersBean.class);

	private String name, surname;

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
	public void init() {
		LOGGER.info("init()");
		
		name = toUpperCase(name);
		surname = toUpperCase(surname);
	}
	private String toUpperCase(String name) {
		return name == null ? name : name.toUpperCase();
	}
}
