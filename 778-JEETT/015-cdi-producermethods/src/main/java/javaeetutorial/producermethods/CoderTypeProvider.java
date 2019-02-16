package javaeetutorial.producermethods;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class CoderTypeProvider {
	public CoderType[] getValues() {
		return CoderType.values();
	} 
}
