package javaeetutorial.sync.consumer;

import static javafx.application.Platform.exit;
import static javafx.application.Platform.runLater;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SynchConsumer extends Application {
	@Resource(lookup="java:comp/DefaultJMSConnectionFactory")
	private static ConnectionFactory confac;

	@Resource(lookup="jms/JMSQueue")
	private static Queue queue;

	@Resource(lookup="jms/JMSTopic")
	private static Topic topic;

	public static void main(String[] args) {
		launch(SynchConsumer.class, args);
	}

	private JMSContext context;
	private int count = 1;
	private JMSConsumer queueConsumer, topicConsumer;
	private AtomicReference<JMSConsumer> consumer = new AtomicReference<>();

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("JMS SynchConsumer");

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
		grid.addRow(1, new Text("received count: "), sentCount);
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
		grid.setStyle("-fx-font-family:Consolas;-fx-padding:5");

		stage.setScene(new Scene(grid));
		stage.show();

		
		destchoice.getSelectionModel().selectedItemProperty().addListener((p, o, n) -> {
			Destination d = n.equals("queue") ? queue : topic;
			
			if(d == queue && queueConsumer == null)
				queueConsumer = context.createConsumer(queue);
			if(d == topic && topicConsumer == null)
				topicConsumer = context.createConsumer(topic);

			consumer.set(d == queue ? queueConsumer : topicConsumer);
			status.appendText("\n-------------------------------\ndestination changed: "+d+"\n------------------------------\n");
		});
		
		destchoice.getSelectionModel().select(0);
		

		Thread t = new Thread(() -> {
			while(true) {
				Message m = consumer.get().receive(1000);
				if(m  != null) {
					try {
						if(m instanceof TextMessage) {
							String msg = m.getBody(String.class);
							
							runLater(() -> {
								status.appendText(msg+"\n");
								sentCount.setText(String.valueOf(count++));
							});
						} else {
							runLater(() -> exit());
							break;
						}
					} catch (JMSException e) {
						runLater(() -> status.appendText("receive failed: "+e+"\n"));
					}
				}
			}
		});

		stage.setOnCloseRequest(e -> {
			if(context != null) {
				try {
					t.interrupt();
					context.close();
					System.out.println("JMSContext closed");
				} catch (Throwable e1) {
					error(stage, "failed to close JMSContext", e1);
					e.consume();
				}
				context = null;
			}
		});
		
		t.setDaemon(true);
		t.start();
	}

	private void error(Stage stage, String title, Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		sw.append(title).append("\n");

		e.printStackTrace(pw);

		stage.setScene(new Scene(new TextArea(sw.toString())));
	}
}
