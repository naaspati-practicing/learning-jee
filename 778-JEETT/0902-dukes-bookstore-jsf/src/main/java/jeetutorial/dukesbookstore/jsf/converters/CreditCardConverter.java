package jeetutorial.dukesbookstore.jsf.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesConverter("ccno")
public class CreditCardConverter implements Converter {
	public static final String CONVERSION_ERROR_MESSAGE_ID = "ConversionError";
	private static final Logger logger = LoggerFactory.getLogger(CreditCardConverter.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		logger.info("Entering CreditCardConverter.getAsObject");
		
		String raw = value;

		if(value == null || value.isEmpty() || (value = value.trim()).isEmpty())
			return null;

		int count = 0;
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if(!(c == '-' || c == ' '))
				count++;
		}
		
		if(count == value.length())
			return value;
		
		if(count == 0)
			return null;

		char[] chars = new char[count];
		count = 0;
		
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if(!(c == '-' || c == ' '))
				chars[count++] = c;
		}
		
		value = new String(chars); 
		logger.info("Converted value is \"{}\" -> \"{}\"", raw, value);
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		logger.info("Entering CreditCardConverter.getAsString");
		
		if(value == null)
			return "";
		
		String input;
		try {
			input = (String) value;
		} catch (ClassCastException e) {
			FacesMessage m = new FacesMessage(CONVERSION_ERROR_MESSAGE_ID);
			FacesContext.getCurrentInstance().addMessage(null, m);
			throw new ConverterException(m);
		}
		
		StringBuilder sb = new StringBuilder(input.length() + input.length()/4 + 1);
		int n = 0;
		
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			
			if((n != 0 && n%4 == 0))
				sb.append(' ');
			else if(c != '-' && c != ' ') {
				sb.append(c);
				n++;
			}
		}
		
		String s = sb.toString(); 
		logger.info("converted: \"{}\" -> \"{}\"", input, s);
		return s;
	}

}
