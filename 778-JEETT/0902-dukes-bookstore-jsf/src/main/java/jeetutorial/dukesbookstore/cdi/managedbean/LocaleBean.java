package jeetutorial.dukesbookstore.cdi.managedbean;

import java.io.Serializable;
import java.util.Locale;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import jeetutorial.dukesbookstore.Utils;

@Named
@SessionScoped
public class LocaleBean implements Utils, Serializable {
	private static final long serialVersionUID = 5110847271178805864L;
		
	private Locale locale = context().getViewRoot().getLocale();
	
	private static final String LOCALE = "locale";
	
	public Locale getLocale() { return locale; }
	public void setLocale(Locale locale) { this.locale = locale; }
	
	public String getLanguage() {
		return Optional.ofNullable(sessionMap().get(LOCALE))
		.map(m -> (Locale)m)
		.map(m -> m.getLanguage())
		.orElseGet(() -> locale.getLanguage());
	}
	public void setLanguage(String lang) {
		context().getViewRoot().setLocale(new Locale(lang));
	}
}
