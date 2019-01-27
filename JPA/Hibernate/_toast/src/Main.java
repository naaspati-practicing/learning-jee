import static org.jsoup.helper.HttpConnection.KeyVal.create;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://hilite.me/api");
		HttpURLConnection con =  (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		Connection c = Jsoup.connect("http://hilite.me/api");
		c.data(Arrays.asList(
				create("code", "{ \"name\": \"CAT_1\", \"id\": 1, \"categorizedItems\": [] } { \"name\": \"CAT_2\", \"id\": 2, \"categorizedItems\": [] } { \"name\": \"CAT_1\", \"id\": 1, \"categorizedItems\": [ { \"item\": { \"name\": \"ITEM 1\", \"id\": 3 }, \"addedBy\": { \"id\": 5, \"username\": \"USER 1\" }, \"addedOn\": \"Sat Oct 27 14:39:07 IST 2018\" }, { \"item\": { \"name\": \"ITEM 2\", \"id\": 4 }, \"addedBy\": { \"id\": 5, \"username\": \"USER 1\" }, \"addedOn\": \"Sat Oct 27 14:39:07 IST 2018\" } ] } { \"name\": \"CAT_2\", \"id\": 2, \"categorizedItems\": [{ \"item\": { \"name\": \"ITEM 1\", \"id\": 3 }, \"addedBy\": { \"id\": 5, \"username\": \"USER 1\" }, \"addedOn\": \"Sat Oct 27 14:39:07 IST 2018\" }] }"),
				create("lexer", "JSON"),
				create("style", "manni")
				));
		
		Document doc = c.get();
		
		System.out.println(doc);
		

	}
}
