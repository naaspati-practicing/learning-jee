import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.json.JSONObject;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		StringBuilder sb = new StringBuilder();
		sb.append(LocalDateTime.now()).append('\n').append('\n');
		
		if(args.length != 0) {
			sb.append("args = [");
			for (String s : args) 
				sb.append(s).append(", ");
			sb.setLength(sb.length() - 2);
			sb.append("]\n");
		} else {
			sb.append("args = []");
		}
		
		System.getProperties().forEach((s,t) -> sb.append(s).append('\t').append(t).append("<br>"));
		System.getenv().forEach((s,t) -> sb.append(s).append('\t').append(t).append('\n'));
		
		sb.append("\n\n").append(new JSONObject().put("json", "working"));
		
		System.out.println(sb);
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("STOPPING: "+LocalDateTime.now())));
		
		/*
		 * Path p = Paths.get("printed");
		try(FileChannel fc = FileChannel.open(p, CREATE, TRUNCATE_EXISTING, WRITE)) {
			fc.write(StandardCharsets.UTF_8.encode(CharBuffer.wrap(sb)));
		}
		
		System.out.println("write: "+p.toAbsolutePath());
		System.out.println("sleeping for 5seconds");
		
		int n = 0;
		while(n < 15) {
			System.out.println("Seconds passed: "+((n++) + 1));
			Thread.sleep(1000);
		}
		 */
		
	}
}
