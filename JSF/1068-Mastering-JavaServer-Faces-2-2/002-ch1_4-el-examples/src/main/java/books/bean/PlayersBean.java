package books.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PlayersBean implements Serializable {
	static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private static final List<Players> DUMMY_PLAYERS = Collections.unmodifiableList(Arrays.asList(
			players(2, "NOVAK DJOKOVIC", 26, "Belgrade, Serbia", "Monte Carlo, Monaco", 188, 80, "Boris Becker, Marian Vajda", "22.05.1987"),
			players(1, "RAFAEL NADAL", 27, "Manacor, Mallorca, Spain", "Manacor, Mallorca, Spain", 185, 85, "Toni Nadal", "03.06.1986"),
			players(7, "TOMAS BERDYCH", 28, "Valasske Mezirici, Czech", "Monte Carlo, Monaco", 196, 91, "Tomas Krupa", "17.09.1985"),
			players(8, "STANISLAS WAWRINKA", 28, "Lausanne, Switzerland", "St. Barthelemy, Switzerland", 183, 81, "Magnus Norman", "28.03.1985"),
			players(4, "ANDY MURRAY", 26, "Dunblane, Scotland", "London, England", 190, 84, "Ivan Lendl", "15.05.1987"),
			players(5, "JUAN MARTIN DEL POTRO", 25, "Tandil, Argentina", "Tandil, Argentina", 198, 97, "Franco Davin", "23.09.1988"),
			players(10, "JO-WILFRIED TSONGA", 28, "Le Mans, France", "Gingins, Switzerland", 188, 91, "Nicolas Escude & Thierry Ascione", "17.04.1985"),
			players(6, "ROGER FEDERER", 32, "Basel, Switzerland", "Bottmingen, Switzerland", 185, 85, "Stefan Edberg, Severin Luthi", "08.08.1981"),
			players(9, "RICHARD GASQUET", 27, "Beziers, France", "Neuchatel, Switzerland", 185, 75, "Sergi Bruguera and Sebastien", "18.06.1986"),
			players(3, "DAVID FERRER", 31, "Javea, Spain", "Valencia, Spain", 175, 73, "Jose Francisco Altur", "02.04.1982")
			));

	private static Players players(int ranking, String player, int age, String birthplace, String residence, int height, int weight, String coach, String born) {
		try {
			return new Players(ranking, player, (byte)age, birthplace, residence, (short)height, (byte)weight, coach, sdf.parse(born));
		} catch (ParseException e) {
			throw new RuntimeException(
					String.format("failed to create players with data: {ranking: %s, player: \"%s\", age: %s, birthplace: \"%s\", residence: \"%s\", height: %s, weight: %s, coach: \"%s\", born: \"%s\"}", ranking, player, age, birthplace, residence, height, weight, coach, born),
					e);
		}
	}  

	ArrayList<Players> dataArrayList = new ArrayList<>(DUMMY_PLAYERS);
	LinkedList<Players> dataLinkedList = new LinkedList<>(DUMMY_PLAYERS);
	HashSet<Players> dataHashSet = new HashSet<>(DUMMY_PLAYERS);                                       //hash set
	TreeSet<Players> dataTreeSet = new TreeSet<>(DUMMY_PLAYERS);                                       //tree set
	LinkedHashSet<Players> dataLinkedHashSet = new LinkedHashSet<>(DUMMY_PLAYERS);                     //linked hash set
	
	HashMap<String, Players> dataHashMap = fill(new HashMap<>());                               //hash map
    HashMap<String, Players> dataCollectionMap = fill(new HashMap<>());                         //hash map using Collection
    TreeMap<String, Players> dataTreeMap = fill(new TreeMap<>());                               //tree map
    LinkedHashMap<String, Players> dataLinkedHashMap = fill(new LinkedHashMap<>());             //linked hash map

	private <C extends Map<String, Players>> C fill(C map) {
		DUMMY_PLAYERS.forEach(p -> map.put(Integer.toString(p.getRanking()), p));
		return map;
	}

	public ArrayList<Players> getDataArrayList() {
		return dataArrayList;
	}

	public LinkedList<Players> getDataLinkedList() {
		return dataLinkedList;
	}

	public HashSet<Players> getDataHashSet() {
		return dataHashSet;
	}

	public TreeSet<Players> getDataTreeSet() {
		return dataTreeSet;
	}

	public LinkedHashSet<Players> getDataLinkedHashSet() {
		return dataLinkedHashSet;
	}

	public HashMap<String, Players> getDataHashMap() {
		return dataHashMap;
	}

	public HashMap<String, Players> getDataCollectionMap() {
		return dataCollectionMap;
	}
	public TreeMap<String, Players> getDataTreeMap() {
		return dataTreeMap;
	}
	public LinkedHashMap<String, Players> getDataLinkedHashMap() {
		return dataLinkedHashMap;
	}
	
}

