package javaeetutorial.producermethods;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Model
public class CoderBean {
	private String inputString;
	private String codedString;
	
	@Max(26) @Min(0) @NotNull
	private int transVal;
	
	private CoderType coderType;
	
	@Produces
	@Chosen
	@RequestScoped
	Coder createCoder() {
		return coderType == null ? null : coderType.create();
	}
	
	@Inject @Chosen @RequestScoped
	private Coder coder;
	
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
	public CoderType getCoderType() {
		return coderType;
	}
	public void setCoderType(CoderType coderType) {
		this.coderType = coderType;
	}
}
