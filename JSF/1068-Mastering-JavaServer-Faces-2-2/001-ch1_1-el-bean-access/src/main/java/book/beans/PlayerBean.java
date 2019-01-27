package book.beans;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import book.pojos.PlayerDetails;
import book.pojos.PlayerMoreDetails;



@Model
public class PlayerBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerBean.class);
	
	public enum Plays {
		LEFT, RIGHT
	}
	
	private boolean show_prize, show_racquet;
	private String facebook;
	private String playerName = "Rafael";
	private String playerSurname = "Nadal";
	
	private PlayerDetails playerDetails;
	private Plays play = Plays.LEFT;
	
	private static final String[] titles_2013_static;
	
	static {
		try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("titles_2013.txt");
				InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr);) {
			titles_2013_static = br.lines().filter(s -> !s.isEmpty()).toArray(String[]::new);
			LOGGER.info("loaded: titles_2013.txt");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private String[] titles_2013 = Arrays.copyOf(titles_2013_static, titles_2013_static.length);
	private int prize = 60941937;
	
	private List<String> finals_2013;
	private Map<String, String> matchfacts;
	
	public PlayerBean() {
		finals_2013 = new ArrayList<>();
		finals_2013.add("Beijing");
		finals_2013.add("ATP World Tour Masters 1000 Monte Carlo");
        finals_2013.add("Vina del Mar");
        
        matchfacts = new HashMap<>();
        matchfacts.put("Aces", "127");
        matchfacts.put("Double Faults", "107");
        matchfacts.put("1st Serve", "70%");
		
		this.playerDetails = new PlayerDetails();
		PlayerDetails p = playerDetails;
		p.setBirthday(LocalDate.of(1986, Month.JUNE, 3));
		p.setBirthplace("Manacor, Mallorca, Spain");
		p.setResidence("Manacor, Mallorca, Spain");
		p.setHeight(185);
		p.setWeight(85);
		
		PlayerMoreDetails pd = new PlayerMoreDetails();
		pd.setCoach("Toni Nadal");
		pd.setTurnedpro(2001);
		pd.setWebsite("http://www.rafaelnadal.com");
		
		p.setMoreDetails(pd);
	}
	

    public boolean isShow_racquet() {
        return show_racquet;
    }

    public boolean isShow_prize() {
        return show_prize;
    }

    public int getPrize() {
        return prize;
    }

    public String getFacebook() {
        return facebook;
    }

    public String[] getTitles_2013() {
        return titles_2013;
    }

    public List<String> getFinals_2013() {
        return finals_2013;
    }

    public Map<String, String> getMatchfacts() {
        return matchfacts;
    }

    public PlayerDetails getPlayerDetails() {
        return playerDetails;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerSurname() {
        return playerSurname;
    }

    public String getHonorific() {
        return "Mr. ";
    }

    public Plays getPlay() {
        return play;
    }

    public void vamosRafa_1() {
        System.out.println("Vamos Rafa!");
    }

    public String vamosRafa_2() {
        return "Vamos Rafa!";
    }

    public void vamosRafa_3(String text) {
        System.out.println(text);
    }

    public String vamosRafa_4(String name, String surname) {
        return "Vamos " + name + " " + surname + "!";
    }

    public String vamosRafa_5() {
        return "vamos";
    }

    public void showPrizeMoney() {
        this.show_prize = true;
    }

    public void hidePrizeMoney() {
        this.show_prize = false;
    }

    public void showHideRacquetPicture(ValueChangeEvent e) {
        if (e.getNewValue() == Boolean.TRUE) {
            this.show_racquet = true;
        } else {
            this.show_racquet = false;
        }
    }

    public void playerListener(ActionEvent e) {
        LOGGER.info("playerListener method called ...");
    }

    public void playerDone() {
        LOGGER.info("playerDone method called ...");        
    }
	
	
	
}
