package sam.learn.jaxb;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements IUser {
	@XmlAttribute
	private int id; 
	private String name;
	private String surname;
	
	public User() { }

	public User(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
	}
	@Override public int getId(){ return this.id; }
	@Override public void setId(int id){ this.id = id; }
	
	@Override public String getName(){ return this.name; }
	@Override public void setName(String name){ this.name = name; }

	@Override public String getSurname(){ return this.surname; }
	@Override public void setSurname(String surname){ this.surname = surname; }
}
