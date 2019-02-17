package org.glassfish.jersey.examples.xmlmoxy.beans;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class PhoneNumber {
	@XmlAttribute
	private String type;
	@XmlValue
	private String value;
	
	public PhoneNumber() { }
	public PhoneNumber(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		return Objects.hash(type, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		return Objects.equals(type, other.type) && Objects.equals(value, other.value);
	}
	@Override
	public String toString() {
		return "PhoneNumber [type=" + type + ", value=" + value + "]";
	}
}
