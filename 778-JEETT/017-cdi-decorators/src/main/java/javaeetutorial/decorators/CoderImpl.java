package javaeetutorial.decorators;

public class CoderImpl implements Coder {
	@Logged
	@Override
	public String codeString(String s, int tval) {
		char[] chars = new char[s.length()];
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int cplus = c + tval;
			
			if(c == ' ')
				cplus = ' ';
			if(c >= 'A' && c <= 'Z') { //uppercase
				if(cplus > 'Z')
					cplus -= 26;
			} else if(c >= 'a' && c <= 'z') { //lowercase
				if(cplus > 'z')
					cplus -= 26;
			} else { // punctuation, etc.
				cplus = c;
			}
			chars[i] = (char) cplus;
		}
		
		return new String(chars);
	}

}
