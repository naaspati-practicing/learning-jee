package books.bean;

import java.util.Map;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class PlayersBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlayersBean.class);
	
	private String playerName, playerSurname;

	public String getPlayerName() {
		return playerName;
	}
	public String getPlayerSurname() {
		return playerSurname;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public void setPlayerSurname(String playerSurname) {
		this.playerSurname = playerSurname;
	}
	
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String > params = fc.getExternalContext().getRequestParameterMap();
		
		LOGGER.info("Name: {} Surname: {}", params.get("playerNameParam"), params.get("playerSurnameParam"));
	}
	
	public String parametersAction() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        playerName = params.get("playerNameParam");
        playerSurname = params.get("playerSurnameParam");

        return "result";
	}
	
}
