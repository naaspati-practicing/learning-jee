package javaeetutorial.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.json.Json;

@Decorator
public class CoderDecorator implements Coder{
	private final Coder coder;

	@Inject
	public CoderDecorator(@Delegate @Any Coder coder) {
		this.coder = coder;
	}

	@Override
	public String codeString(String s, int tval) {
		return Json.createObjectBuilder()
				.add("input", s)
				.add("encoded_to", coder.codeString(s, tval))
				.add("initial_length", s.length())
				.build()
				.toString();
	}



}
