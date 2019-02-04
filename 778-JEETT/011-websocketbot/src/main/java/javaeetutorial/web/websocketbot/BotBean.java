package javaeetutorial.web.websocketbot;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

import javax.inject.Named;

import javaeetutorial.web.websocketbot.Interaction.SimpleInteraction;

@Named
public class BotBean {
	private final String name = "Duke";
	
	private static Predicate<String> contains(String s) {
		return t -> t.toLowerCase().contains(s);
	}

	private static final Interaction[] INTERACTIONS = {
			new SimpleInteraction(contains("how are you"), "I'm doing great, thank you!"),
			new SimpleInteraction(contains("how old are you"), () -> String.format("I'm %d years old.", ChronoUnit.YEARS.between(LocalDate.of(1992, 1, 12), LocalDate.now()))),
			new SimpleInteraction(contains("when is your birthday"), "My birthday is on January 12th. Thanks for asking!"),
			new SimpleInteraction(contains("your favorite color"), "My favorite color is blue. What's yours?"),
	};

	private static final String DEFAULT_RESPONSE = 
			"Sorry, I did not understand what you said. "
					+ "You can ask me how I'm doing today; how old I am; or "
					+ "what my favorite color is.";

	public String respond(String msg) {
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) { }
		
		if(msg == null || msg.isEmpty())
			return DEFAULT_RESPONSE;

		msg = msg.toLowerCase();

		for (Interaction s : INTERACTIONS) {
			String m = s.handle(msg);
			if(m != null)
				return m;
		}
		
		return DEFAULT_RESPONSE;

	}
	public String getName() {
		return name;
	}
}
