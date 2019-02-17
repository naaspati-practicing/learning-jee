package org.glassfish.jersey.examples.entityfiltering.selectable.domain;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class PhoneNumber {
	private String areaCode;
	private String number;
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
