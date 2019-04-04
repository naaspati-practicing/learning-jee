package jeetutorial.dukesbookstore.jsf.components;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

import jeetutorial.dukesbookstore.model.ImageArea;

@FacesComponent("DemoArea")
public class AreaComponent extends UIOutput {
	private static enum Keys {
		ALT, COORDS, SHAPE, TARGET_IMAGE
	}
	
	public static final String FAMILY = "Area";
	
	private String get(Keys key) {
		return (String) getStateHelper().eval(key, null);
	}
	private String set(Keys key, String value) {
		return (String) getStateHelper().put(key, value);
	}
	
	public String getAlt() {
		return get(Keys.ALT);
	}

	public void setAlt(String value) {
		set(Keys.ALT, value);
	}

	public String getCoords() {
		return get(Keys.COORDS);
	}

	public void setCoords(String value) {
		set(Keys.COORDS, value);
	}
	public String getShape() {
		return get(Keys.SHAPE);
	}

	public void setShape(String value) {
		set(Keys.SHAPE, value);
	}

	public String getTargetImage() {
		return get(Keys.TARGET_IMAGE);
	}

	public void setTargetImage(String value) {
		set(Keys.TARGET_IMAGE, value);
	}
	@Override
	public String getFamily() {
		return FAMILY;
	}
	@Override
	public Object getValue() {
		Object o = super.getValue();
		if(o != null)
			return o;
		
		super.setValue(new ImageArea(getAlt(), getCoords(), getShape()));
		return super.getValue();
	}
}