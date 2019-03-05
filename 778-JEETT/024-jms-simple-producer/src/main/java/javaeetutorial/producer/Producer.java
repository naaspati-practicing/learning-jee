package javaeetutorial.producer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Producer extends Application {
	@Resource(lookup="java:comp/DefaultJMSConnectionFactory")
	private static ConnectionFactory confac;

	@Resource(lookup="jms/JMSQueue")
	private static Queue queue;

	@Resource(lookup="jms/JMSTopic")
	private static Topic topic;

	public static void main(String[] args) {
		launch(Producer.class, args);
	}

	private JMSContext context;
	private int count;
	private Destination destination;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("JMS Producer");
		
		if(confac == null) {
			stage.setScene(new Scene(new TextArea("@Resource(lookup=\"java:comp/DefaultJMSConnectionFactory\")\n" + 
					"	private static ConnectionFactory confac;\n" + 
					"\nNOT SET")));
			stage.show();
			return;
		}

		try {
			context = confac.createContext();
		} catch (Throwable e) {
			error(stage, "failed to init JMSContext", e);
			stage.show();
			return;
		}

		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);

		ChoiceBox<String> destchoice = new ChoiceBox<>(FXCollections.observableArrayList("queue", "topic"));
		Text sentCount = new Text();
		TextArea status = new TextArea();

		grid.addRow(0, new Text("Destination: "), destchoice);
		grid.addRow(1, new Text("sent count: "), sentCount);
		grid.addRow(2, new Text("Status"));
		grid.add(status, 0, 3, GridPane.REMAINING, GridPane.REMAINING);

		ColumnConstraints c = new ColumnConstraints();
		c.setFillWidth(true);
		c.setHgrow(Priority.ALWAYS);

		grid.getColumnConstraints().addAll(new ColumnConstraints(), c);

		RowConstraints r = new RowConstraints();
		r.setFillHeight(true);
		r.setVgrow(Priority.ALWAYS);

		grid.getRowConstraints().addAll(new RowConstraints(), new RowConstraints(), new RowConstraints(), r);

		TextField message = new TextField();
		HBox hbox = new HBox(5, new Text("message: "), message);
		HBox.setHgrow(message, Priority.ALWAYS);
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.setPadding(new Insets(5));

		BorderPane root = new BorderPane(grid);
		root.setBottom(hbox);
		root.setStyle("-fx-font-family:Consolas;-fx-padding:5");

		stage.setScene(new Scene(root));
		stage.show();

		destchoice.getSelectionModel().selectedItemProperty().addListener((p, o, n) -> {
			destination = n.equals("queue") ? queue : topic;
			status.appendText("\n-------------------------------\ndestination changed: "+destination+"\n------------------------------\n");
		});
		destchoice.getSelectionModel().select(0);

		ExecutorService exQueue = Executors.newSingleThreadExecutor();
		ExecutorService exTopic = Executors.newSingleThreadExecutor();

		message.setOnAction(e -> {
			String s = message.getText();
			if(s == null || s.isEmpty())
				return;
			
			message.clear();
			int n = count++;
			
			status.appendText("("+n+") sending: \""+s+"\"\n");
			sentCount.setText(String.valueOf(n));
			Destination dest = destination;
			
			(destination == queue ? exQueue : exTopic)
			.execute(() -> {
				context.createProducer().send(dest, n+": "+s);
				Platform.runLater(() -> status.appendText("("+n+") sent\n"));
			});
		});

		stage.setOnCloseRequest(e -> {
			if(context != null) {
				try {
					exQueue.shutdownNow();
					exTopic.shutdownNow();
					exQueue.awaitTermination(2, TimeUnit.SECONDS);
					exTopic.awaitTermination(2, TimeUnit.SECONDS);
					context.close();
					System.out.println("JMSContext closed");
				} catch (Throwable e1) {
					error(stage, "failed to close JMSContext", e1);
					e.consume();
				}
				context = null;
			}
		});
	}

	private void error(Stage stage, String title, Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		sw.append(title).append("\n");

		e.printStackTrace(pw);

		stage.setScene(new Scene(new TextArea(sw.toString())));
	}
}
