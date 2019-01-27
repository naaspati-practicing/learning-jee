import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

	public static void main(String[] args) throws IOException {
		Path root = Paths.get("C:\\Users\\Sameer\\servers\\");
		
		Set<Path> set1 = walk(root.resolve("jetty-distribution-9.4.11.v20180605"));
		Set<Path> set2 = walk(root.resolve("jetty-server"));
		
		Set<Path> set11 = new HashSet<>(set1);
		set11.removeAll(set2);
		
		Set<Path> set21 = new HashSet<>(set2);
		set21.removeAll(set1);
		
		set11.forEach(s -> System.out.println(s));
		
		System.out.println();
		System.out.println();
		
		set21.forEach(s -> System.out.println(s));
	}

	private static Set<Path> walk(Path dir) throws IOException {
		int n = dir.getNameCount();
		return Files.walk(dir)
				.skip(1)
				.map(p -> p.subpath(n, p.getNameCount()))
				.filter(p -> !p.startsWith("my-jetty"))
				.collect(Collectors.toSet());
	}

}
