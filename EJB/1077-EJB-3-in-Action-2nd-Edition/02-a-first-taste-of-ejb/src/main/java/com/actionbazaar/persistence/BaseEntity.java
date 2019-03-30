package com.actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -6701369925871078876L;
	
	@Id
	@GeneratedValue
	@Column(name="_ID", nullable=false)
	protected long id;
	
	@Version
	protected int version;

	public long getId() { return id; }
	public void setId(long id) { this.id = id; }

	public int getVersion() { return version; }
	public void setVersion(int version) { this.version = version; }
}
