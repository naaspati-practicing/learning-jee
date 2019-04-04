package sam.learn.jaxb;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
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
	public int getId(){ return this.id; }
	public void setId(int id){ this.id = id; }

	public String getName(){ return this.name; }
	public void setName(String name){ this.name = name; }

	public String getSurname(){ return this.surname; }
	public void setSurname(String surname){ this.surname = surname; }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}
}
