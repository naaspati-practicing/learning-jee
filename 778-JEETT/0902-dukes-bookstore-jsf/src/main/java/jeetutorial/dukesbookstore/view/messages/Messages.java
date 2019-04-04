package jeetutorial.dukesbookstore.view.messages;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Messages {
	private static final String cname = Messages.class.getName();
	
	public String get(String key, Locale locale) {
		return ResourceBundle.getBundle(cname, locale).getString(key);
	}

	public String format(String key, Locale locale, Object...params) {
		String s = get(key, locale);
		
		if(s == null || params == null || params.length == 0)
			return s;
		
		return MessageFormat.format(s, params);
	}
}
