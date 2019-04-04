package jeetutorial.dukesbookstore.jsf.listeners;

import java.util.HashMap;
import java.util.Map;

public class Data {
	private final Map<String, Long> books = new HashMap<>(6);

	public Data() {
		books.put("Duke", Long.valueOf(201));
		books.put("Jeeves", Long.valueOf(202));
		books.put("Masterson", Long.valueOf(203));
		books.put("Novation", Long.valueOf(205));
		books.put("Thrilled", Long.valueOf(206));
		books.put("Coding", Long.valueOf(207));
	}

	public Long get(String key) {
		return books.get(key);
	}
}
