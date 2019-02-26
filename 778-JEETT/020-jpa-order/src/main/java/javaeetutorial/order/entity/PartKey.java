package javaeetutorial.order.entity;

import java.io.Serializable;
import java.util.Objects;

public class PartKey implements Serializable {
	private static final long serialVersionUID = -3654680061482836451L;
	
	private String partNumber;
	private int revision;
	
	public PartKey() { }
	
	public PartKey(String partNumber, int revision) {
		setPartNumber(partNumber);
		setRevision(revision);
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public int getRevision() {
		return revision;
	}
	public void setRevision(int revision) {
		this.revision = revision;
	}
	@Override
	public int hashCode() {
		return Objects.hash(getPartNumber(), getRevision());
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof PartKey))
			return false;
		
		PartKey other = (PartKey) obj;
		return Objects.equals(getPartNumber(), other.getPartNumber()) && getRevision() == other.getRevision();
	}
	
	
}
