package javaeetutorial.hello1;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HelloEntity implements Serializable {
	private static final long serialVersionUID = -992315573147156835L;

	private static final Logger LOGGER = Logger.getLogger(HelloEntity.class.getName());
	
	@Id
	@GeneratedValue
	private int id;
	private String msg;
	
	public HelloEntity() { }
	public HelloEntity(String msg) {
		this.msg = msg;
	}
	
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		LOGGER.info(() -> "setMsg(\""+msg+"\")");
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		LOGGER.info(() -> "setId(\""+id+"\")");
		this.id = id;
	}
	@Override
	public String toString() {
		return "HelloEntity [id=" + id + ", msg='" + msg + "']";
	}
}
