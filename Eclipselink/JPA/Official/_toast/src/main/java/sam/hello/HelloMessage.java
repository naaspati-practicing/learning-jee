package sam.hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HelloMessage {
	@Id
	@GeneratedValue
	private int id;
	
	private String msg;
	
	public int getId(){ return this.id; }
	public void setId(int id){ this.id = id; }

	public String getMsg(){ return this.msg; }
	public void setMsg(String msg){ this.msg = msg; }
	@Override
	public String toString() {
		return "HelloMessage [id=" + id + ", msg=\"" + msg + "\"]";
	}
	
	

}
