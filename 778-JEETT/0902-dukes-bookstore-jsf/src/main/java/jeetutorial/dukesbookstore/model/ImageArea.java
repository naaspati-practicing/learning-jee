package jeetutorial.dukesbookstore.model;

import java.io.Serializable;

public class ImageArea implements Serializable {
	private static final long serialVersionUID = 9041808811740293845L;
	
	private String alt;
	private String coords;
	private String shape;
	
	public ImageArea(String alt, String coords, String shape) {
		setAlt(alt);
		setCoords(coords);
		setShape(shape);
	}
	
	public ImageArea() { }
	
	public String getAlt(){ return this.alt; }
	public void setAlt(String alt){ this.alt = alt; }

	public String getCoords(){ return this.coords; }
	public void setCoords(String coords){ this.coords = coords; }

	public String getShape(){ return this.shape; }
	public void setShape(String shape){ this.shape = shape; }
	
}
