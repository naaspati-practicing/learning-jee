package _simple;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application { 

	public static void main(String[] args) throws IOException {
		launch(Main.class, args);
	}

	private Set<String> existing;
	private final Set<String> existing_new = new LinkedHashSet<>();
	final TextArea html = new TextArea();
	final TextArea tags = new TextArea();
	final TextArea attrs = new TextArea();

	final Button extract = new Button("extract");
	final Button save = new Button("save");
	final Text status = new Text();
	Scene scene;

	Path existingPath() {
		return Paths.get("existing");
	}

	@Override
	public void start(Stage stage) throws Exception {
		existing = Files.lines(existingPath()).collect(Collectors.toSet());

		HBox buttons = new HBox(10, extract, save, status);

		BorderPane.setMargin(buttons, new Insets(5, 10, 5, 10));

		extract.setDisable(true);
		save.setDisable(true);
		tags.setPrefColumnCount(6);
		attrs.setPrefColumnCount(6);

		tags.setEditable(false);
		attrs.setEditable(false);
		tags.setFocusTraversable(false);
		attrs.setFocusTraversable(false);

		BorderPane root = new BorderPane(wrap(html, "enter html"), null, new HBox(5, wrap(tags, "tags"), wrap(attrs, "attrs")), buttons, null);
		scene = new Scene(root);
		stage.setScene(scene);
		BorderPane.setMargin(root.getCenter(), new Insets(0, 5, 0, 5));

		extract.disableProperty().bind(html.textProperty().isEmpty());
		extract.setOnAction(e -> extract(html.getText()));
		html.textProperty().addListener(i -> {
			status.setText(null);
			save.setDisable(true);
		});

		save.setOnAction(e -> save());

		stage.show();
	}

	JSONObject attrs_json;
	JSONObject tags_json;

	final Path attrs_json_path = Paths.get("C:\\Users\\Sameer\\.vscode\\extensions\\mrmlnc.vscode-jade-snippets-1.0.1\\snippets\\JSF\\jsf_primefaces_attrs.json");
	final Path tags_json_path = attrs_json_path.resolveSibling("jsf_primefaces_tags.json");

	private void save() {
		if(attrs_json == null) {
			try {

				attrs_json = new JSONObject(new JSONTokener(Files.newBufferedReader(attrs_json_path)));
				tags_json = new JSONObject(new JSONTokener(Files.newBufferedReader(tags_json_path)));

			} catch (Exception e) {
				error(e);
				return;
			}
		}

		try {
			save(tags_json, tagsMap, tags_json_path);
			tags.clear();
			save(attrs_json, attrsMap, attrs_json_path);
			attrs.clear();
			html.clear();
		} catch (Exception e) {
			error(e);
		}
	}

	private void error(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);

		scene.setRoot(new TextArea(sw.toString()));
	}

	private <E> void save(JSONObject json, HashMap<String, E> map, Path path) throws JSONException, IOException {
		if(map.isEmpty())
			return;

		map.forEach((s,t) -> json.put(s, new JSONObject().put("prefix", s).put("body", s).put("description", "")));
		try(Writer w = Files.newBufferedWriter(path, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);) {
			json.write(w, 4, 0);	
		}

		System.out.println("saved: ("+map.size()+"): "+path.getFileName());
		existing_new.addAll(map.keySet());
		map.clear();
	}

	@Override
	public void stop() throws Exception {
		if(!existing_new.isEmpty()) {
			Files.write(existingPath(), existing_new, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			System.out.println("existing: "+existing.size() +" -> "+ (existing.size() + existing_new.size()));
		}
		super.stop();
	}

	private Node wrap(TextArea ta, String title) {
		return new VBox(3, new Text(title), ta);
	}

	private final HashMap<String, Attribute> attrsMap = new HashMap<>();
	private final HashMap<String, Element> tagsMap = new HashMap<>();
	private final StringBuilder sb = new StringBuilder();

	private void extract(String text) {
		if(text == null || text.trim().isEmpty())
			return;

		Parser parser = Parser.htmlParser();
		parser.settings(new ParseSettings(true, true));

		Document doc = parser.parseInput(text, "baseUri");
		HashMap<String, List<Element>> tags = new HashMap<>(); 
		walk(doc.children(), tags);

		attrsMap.clear();
		tagsMap.clear();

		tags.forEach((name, list) -> {
			if(name.indexOf(':') < 0 || contains(name))
				return;

			tagsMap.put(name, list.get(0));

			list.stream()
			.flatMap(e -> e.attributes().asList().stream())
			.forEach(atr -> {
				String s = atr.getKey();
				if(contains(s) || attrsMap.containsKey(s))
					return;
				attrsMap.put(s, atr);
			});
		});

		this.tags.clear();
		this.attrs.clear();

		if(attrsMap.isEmpty() && tagsMap.isEmpty()) {
			save.setDisable(true);
			status.setText("nothing found");
		} else {
			settext(tagsMap, this.tags);
			settext(attrsMap, this.attrs);
			save.setDisable(false);
			status.setText(null);
		}
	}

	private void settext(HashMap<String, ?> map, TextArea ta) {
		if(map.isEmpty()) {
			ta.clear();
			return;
		}

		sb.setLength(0);
		map.forEach((s,t) -> sb.append(s).append('\n'));
		ta.setText(sb.toString());
	}

	private boolean contains(String s) {
		return existing_new.contains(s) || existing.contains(s);
	}

	final Function<String, List<Element>> computer = s -> new ArrayList<>();

	private void walk(Elements children, HashMap<String, List<Element>> tags) {
		if(children == null || children.isEmpty())
			return;

		for (Element e : children) {
			tags.computeIfAbsent(e.tagName(), computer).add(e);
			walk(e.children(), tags);
		}
	}

}
