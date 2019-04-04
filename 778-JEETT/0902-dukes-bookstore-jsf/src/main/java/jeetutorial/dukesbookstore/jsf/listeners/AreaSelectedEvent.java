package jeetutorial.dukesbookstore.jsf.listeners;

import javax.faces.event.ActionEvent;

import jeetutorial.dukesbookstore.jsf.components.MapComponent;

public class AreaSelectedEvent extends ActionEvent {
	private static final long serialVersionUID = -5701372105584807243L;

	public AreaSelectedEvent(MapComponent map) {
		super(map);
	}
	
	public MapComponent getMapComponent() {
        return ((MapComponent) getComponent());
    }
}
