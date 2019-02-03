package javaeetutorial.bookmarks;

import javax.enterprise.inject.Model;

@Model
public class Hello {
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
