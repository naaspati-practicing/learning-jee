package sam.javamail.client;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class JavaMailClient extends Application {
	public static Session session;

	@Override
	public void init() throws Exception {
		Objects.requireNonNull(session);
	}

	private final TextField to = new TextField();
	private final TextField from = new TextField();
	private final TextField subject = new TextField();
	private final TextField username = new TextField();
	private final TextField password = new TextField();
	private final TextArea body = new TextArea();
	private final BorderPane main_root = new BorderPane();
	private final Scene scene = new Scene(main_root);
	private final Text status = new Text();

	@Override
	public void start(Stage stage) throws Exception {
		GridPane center = new GridPane();
		center.setPadding(new Insets(5));
		center.setHgap(5);
		center.setVgap(5);

		int row = 0;
		center.addRow(row++, text("user"), username);
		center.addRow(row++, text("password"), password);
		center.addRow(row++, new Text());
		center.addRow(row++, text("to"), to);
		center.addRow(row++, text("from"), from);
		center.addRow(row++, text("subject"), subject);
		center.addRow(row++, text("body"));
		center.add(body, 0, row++, GridPane.REMAINING, GridPane.REMAINING);

		ColumnConstraints c = new ColumnConstraints();
		c.setFillWidth(true);
		c.setHgrow(Priority.ALWAYS);
		c.setMaxWidth(Double.MAX_VALUE);

		center.getColumnConstraints().addAll(new ColumnConstraints(), c);

		RowConstraints r = new RowConstraints();
		r.setFillHeight(true);
		r.setVgrow(Priority.ALWAYS);
		r.setMaxHeight(Double.MAX_VALUE);

		RowConstraints r2 = new RowConstraints();

		for (int i = 0; i < row - 1; i++) 
			center.getRowConstraints().add(r2);

		center.getRowConstraints().add(r);

		main_root.setCenter(center);; 
		Button btn = new Button("send");
		btn.setOnAction(e -> send());

		Hyperlink link = new Hyperlink("about session");
		link.setOnAction(e -> aboutSession());
		Pane pane = new Pane();
		pane.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(pane, Priority.ALWAYS);

		HBox bottom = new HBox(10, link, status,pane, btn);
		bottom.setPadding(new Insets(5));
		bottom.setAlignment(Pos.CENTER_RIGHT);

		main_root.setBottom(bottom);
		main_root.setStyle("-fx-font-family:monospace");

		stage.setTitle("SMTP client");
		stage.setScene(scene);
		stage.show();
	}

	private WeakReference<BorderPane> waboutSession = new WeakReference<BorderPane>(null);

	private void aboutSession() {
		BorderPane root = waboutSession.get();
		if(root == null) {
			TextArea ta = new TextArea();
			root = createRoot(ta, null);
			waboutSession = new WeakReference<BorderPane>(root);
			BorderPane.setMargin(ta, new Insets(5));

			StringBuilder sb = new StringBuilder();
			Formatter fm = new Formatter(sb);
			String format = "%-10s%s\n";
			fm.format(format, "class: ", session.getClass());
			fm.format(format, "providers: ", Arrays.toString(session.getProviders()));
			fm.format(format, "debug: ", session.getDebug());
			String format2 = "%-10s%-10s%s\n";
			session.getProperties().forEach((s,t) -> fm.format(format2, "", s,t));

			fm.close();
			ta.setText(sb.toString());
		}

		scene.setRoot(root);
	}

	private BorderPane createRoot(Node center, Node bottom) {
		Button back = new Button("back");
		back.setOnAction(e -> scene.setRoot(main_root));

		BorderPane pane = new BorderPane(center);
		if(bottom != null)
			pane.setBottom(bottom);

		pane.setTop(back);
		BorderPane.setMargin(back, new Insets(5));

		return pane;
	}

	private void send() {
		String to = this.to.getText();
		String from = this.from.getText();
		String subject = this.subject.getText();
		String username = this.username.getText();
		String password = this.password.getText();
		String body = this.body.getText();

		CompletableFuture.runAsync(() -> {
			try {
				MimeMessage m = new MimeMessage(session);
				m.setText(body);
				m.setSubject(subject);

				m.setFrom(from);
				m.setRecipients(RecipientType.TO, to);
				Transport.send(m, username, password); 
				Platform.runLater(() -> status.setText("sent"));
			} catch (Exception e) {
				Platform.runLater(() -> {
					String s = new JSONObject()
							.put("to", to)
							.put("from", from)
							.put("subject", subject)
							.put("username", username)
							.put("password", password)
							.put("body", body)
							.toString(4);

					showError("failed to send: \n"+s, e);
				});
			}
		});
	}

	private void showError(String s, Exception e) {
		StringWriter sw = new StringWriter();
		sw.append(s);
		sw.append("\n-----------------------------------------\n\nstack trace: \n----------------\n");
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);

		scene.setRoot(createRoot(new TextArea(sw.toString()), null));
	}

	private Node text(String string) {
		Text t = new Text(string);
		t.setTextAlignment(TextAlignment.RIGHT);
		return t;
	}


}
