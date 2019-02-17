package org.glassfish.jersey.examples.multipart.webapp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bean {
	private String value;
	
	public Bean() {
	}

	public Bean(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
