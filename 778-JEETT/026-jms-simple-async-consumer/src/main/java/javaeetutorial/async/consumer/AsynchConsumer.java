package javaeetutorial.async.consumer;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AsynchConsumer extends Application {
	@Resource(lookup="java:comp/DefaultJMSConnectionFactory")
	private static ConnectionFactory confac;

	@Resource(lookup="jms/JMSQueue")
	private static Queue queue;

	@Resource(lookup="jms/JMSTopic")
	private static Topic topic;

	public static void main(String[] args) {
		launch(AsynchConsumer.class, args);
	}

	private JMSContext context;
	private int count = 1;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("JMS AsynchConsumer");

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

		TextArea status = new TextArea();
		status.setFont(Font.font("Consolas"));
		status.setPadding(new Insets(5));
		
		stage.setScene(new Scene(status));
		stage.show();
		
		create("queue", queue, status);
		create("topic", topic, status);

		stage.setOnCloseRequest(e -> {
			if(context != null) {
				try {
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

	private void create(String title, Destination dest, TextArea ta) {
		JMSConsumer consumer = context.createConsumer(dest);
		consumer.setMessageListener(m -> {
			if(m instanceof TextMessage) {
				try {
					ta.appendText( title+": ("+(count++)+") "+m.getBody(String.class)+"\n");
				} catch (JMSException e) {
					ta.appendText(e+"\n");
				}
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
