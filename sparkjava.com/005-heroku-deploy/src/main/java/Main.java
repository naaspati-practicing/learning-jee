
import static spark.Spark.*;

import java.util.Arrays;
public class Main {

	public static void main(String[] args) {
		if(args.length == 0)
			throw new IllegalArgumentException("PORT not specified");
		
		try {
			port(Integer.parseInt(args[0]));
		} catch (Exception e) {
			throw new IllegalArgumentException("failed to obtain PORT: "+Arrays.toString(args));
		}
		
		get("/hello", (req, res) -> "Hello world");
	}

}
