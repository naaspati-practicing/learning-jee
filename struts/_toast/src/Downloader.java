import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Downloader {

	public static void start(String url) throws IOException {
		System.out.println("URL:"+url);

		try(InputStream is = Runtime.getRuntime().exec("youtube-dl --get-url "+url).getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader reader = new BufferedReader(isr);
				) {

			List<String> list = reader.lines().distinct().collect(Collectors.toList());

			if(list.size() != 1 || list.get(0).contains(" ")) {
				System.out.println("failed --get-url");
				System.out.println(String.join("\n", list));
			} else {
				System.out.println("download-url: "+list.get(0));

				new ProcessBuilder(
						"C:\\Program Files (x86)\\Internet Download Manager\\IDMan.exe",
						"/d",
						list.get(0),
						"/p",
						rootS, 
						"/n"
						).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static Path root = Paths.get("hub");
	static String rootS = root.toAbsolutePath().toString();

	public static void main(String[] args) throws IOException, InterruptedException {

		Files.createDirectories(root);

		LinkedList<String> lines = Files.lines(Paths.get(args.length == 0 ? "a.txt" : args[0])).collect(Collectors.toCollection(LinkedList::new));
		int size = lines.size();
		System.out.println("urls-count: "+size+"\n");


		if(lines.isEmpty()) {
			System.out.println("empty file");
			System.exit(0);
		}

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				Files.write(Paths.get(args.length == 0 ? "a.txt" : args[0]), lines, StandardOpenOption.TRUNCATE_EXISTING);
				System.out.println("urls-count: "+size+" -> "+lines.size()+"\n");
			} catch (IOException e) {}
		}) );

		WatchService service = null;
		start(lines.pop());

		while(!lines.isEmpty()) {
			if(service == null) {
				service = FileSystems.getDefault().newWatchService();
				root.register(service, StandardWatchEventKinds.ENTRY_CREATE);
			}

			WatchKey key =  service.take();

			for (WatchEvent<?> event: key.pollEvents()) {
				if(event.kind() == StandardWatchEventKinds.OVERFLOW)
					continue;

				Path file = (Path) event.context();
				if(!file.getFileName().toString().endsWith(".tmp")) {
					System.out.println(file+"\n");
					start(lines.pop());
				}
				key.reset();
			}
		}

		System.out.println("DONE");
		System.exit(0);
	}

}
