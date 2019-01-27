package books.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@SessionScoped
public class PlayersBean implements Serializable {
	private static final long serialVersionUID = -4204558988259842642L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PlayersBean.class);
	
	static final String[] players_list = {"Nadal, Rafael (ESP)", "Djokovic, Novak (SRB)", "Ferrer, David (ESP)", "Murray, Andy (GBR)", "Del Potro, Juan Martin (ARG)"};
	private ArrayList<String> players = new ArrayList<>();
    private String player;
	
	public ArrayList<String> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}

	@PostConstruct
	public void init() {
		LOGGER.info("@PostConstruct reporting player: {} ", player);
	}
	
	public void newPlayer() {
        int nr = new Random().nextInt(4);
        player = players_list[nr];
        players.add(player);
    }
	

	
}
