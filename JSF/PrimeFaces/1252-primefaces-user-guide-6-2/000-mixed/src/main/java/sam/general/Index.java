package sam.general;

import java.util.HashMap;
import java.util.Random;

import javax.enterprise.inject.Model;

@Model
public class Index extends HashMap<String, String> {
	private Random r = new Random();


	@Override
	public String get(Object key) {
		return key +" - "+r.nextInt();
	}
}
