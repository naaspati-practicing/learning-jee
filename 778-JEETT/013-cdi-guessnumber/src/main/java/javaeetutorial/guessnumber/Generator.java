package javaeetutorial.guessnumber;

import java.io.Serializable;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import javaeetutorial.guessnumber.Annots.MaxNumber;

@ApplicationScoped
public class Generator implements Serializable {
	private static final long serialVersionUID = -8510890346586665545L;
	
	private final Random random = new Random(System.currentTimeMillis());
	private final int maxNumber = 100;
	
	public Random getRandom() {
		return random;
	}
	@Produces
	@javaeetutorial.guessnumber.Annots.Random
	int next() {
		return getRandom().nextInt(maxNumber);
	}

	@Produces
	@MaxNumber
	int max() {
		return maxNumber;
	}
	
	
	

}
