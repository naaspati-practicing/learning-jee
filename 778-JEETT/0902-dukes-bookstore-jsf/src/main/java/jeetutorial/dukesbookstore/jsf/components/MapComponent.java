package jeetutorial.dukesbookstore.jsf.components;

import java.util.Objects;

import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;

import jeetutorial.dukesbookstore.jsf.listeners.AreaSelectedEvent;

@FacesComponent("DemoMap")
public class MapComponent extends UICommand {
	public static final String FAMILY = "Map";
	
	private enum Keys {
        CURRENT;
    }
	public String getCurrent() {
		return (String) getStateHelper().eval(Keys.CURRENT, null);
	}
	public void setCurrent(String value) {
		if(getParent() == null)
			return;
		
		String old = (String) getStateHelper().get(Keys.CURRENT);
		getStateHelper().put(Keys.CURRENT, value);
		
		if(!Objects.equals(old, value)) 
			this.queueEvent(new AreaSelectedEvent(this));
	}
	
	@Override
	public String getFamily() {
		return FAMILY;
	}
}
