package sam.learns.primefaces.auto.complete;

import java.util.concurrent.ThreadLocalRandom;

public class Theme {
	private int id;
	private String name;
	private String displayName;

	public Theme() { }
	
	public Theme(int id, String name, String displayName) {
		this.id = id;
		this.name = name;
		this.displayName = displayName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public int getImgNumber() {
		return ThreadLocalRandom.current().nextInt(1000);
	}
	@Override
	public String toString() {
		return name;
	}
}
