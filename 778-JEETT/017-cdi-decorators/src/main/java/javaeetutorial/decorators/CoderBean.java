package javaeetutorial.decorators;

import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Model
public class CoderBean {
	private static final Logger LOGGER = Logger.getLogger(CoderBean.class.getName());
	
	private String inputString;
	private String codedString;
	
	@Max(26) @Min(0) @NotNull
	private int transVal;
	
	private final Coder coder;

	@Inject
	public CoderBean(Coder coder) {
		this.coder = coder;
		LOGGER.info("Coder impl: "+coder.getClass().getSimpleName()+"@"+System.identityHashCode(coder));
	}
	public void encodeString() {
		setCodedString(coder.codeString(getInputString(), getTransVal()));
	}
	public void reset() {
		setInputString("");
		setTransVal(0);
	}

	public String getInputString() {
		return inputString;
	}
	public void setInputString(String inputString) {
		this.inputString = inputString;
	}
	public int getTransVal() {
		return transVal;
	}
	public void setTransVal(int transVal) {
		this.transVal = transVal;
	}
	public String getCodedString() {
		return codedString;
	}
	public void setCodedString(String codedString) {
		this.codedString = codedString;
	}

}
