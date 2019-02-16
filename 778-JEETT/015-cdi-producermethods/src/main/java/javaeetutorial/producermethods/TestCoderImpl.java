package javaeetutorial.producermethods;

import javax.enterprise.inject.Alternative;

@Alternative
public class TestCoderImpl implements Coder {
	@Override
	public String codeString(String s, int tval) {
		return String.format("{\"inputString\":\"%s\", \"shiftValue\":%d}", s, tval);
	}

}
