package jeetutorial.dukesbookstore.jpa.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="BOOKS")
@NamedQuery(name=Book.NAMED_QUERY_FIND_ALL, query="SELECT e FROM Book e ORDER BY e.id")
public class Book implements Serializable, Item {
	private static final long serialVersionUID = -7580892794135499326L;

	public static final String NAMED_QUERY_FIND_ALL = "Book.findAll";

	@Id @GeneratedValue 
	private long id;

	@Version
	private int version;

	private String surname;
	private String firstname;
	private String title;
	private double price;
	private boolean onsale;
	private int calendarYear;
	private String description;
	private int inventory;
	
	public Book() { }

    public Book(long id, String surname, String firstname, String title,
            double price, boolean onsale, int calendarYear,
            String description, int inventory) {
    	
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
        this.title = title;
        this.price = price;
        this.onsale = onsale;
        this.calendarYear = calendarYear;
        this.description = description;
        this.inventory = inventory;
    }

	public long getId(){ return this.id; }
	public void setId(long id){ this.id = id; }

	public int getVersion(){ return this.version; }
	public void setVersion(int version){ this.version = version; }

	public String getSurname(){ return this.surname; }
	public void setSurname(String surname){ this.surname = surname; }

	public String getFirstname(){ return this.firstname; }
	public void setFirstname(String firstname){ this.firstname = firstname; }

	public String getTitle(){ return this.title; }
	public void setTitle(String title){ this.title = title; }

	@Override
	public double getPrice(){ return this.price; }
	public void setPrice(double price){ this.price = price; }

	public boolean isOnsale(){ return this.onsale; }
	public void setOnsale(boolean onsale){ this.onsale = onsale; }

	public int getCalendarYear(){ return this.calendarYear; }
	public void setCalendarYear(int calendarYear){ this.calendarYear = calendarYear; }

	public String getDescription(){ return this.description; }
	public void setDescription(String description){ this.description = description; }

	public int getInventory(){ return this.inventory; }
	public void setInventory(int inventory){ this.inventory = inventory; }
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", version=" + version + ", " + (surname != null ? "surname=" + surname + ", " : "")
				+ (firstname != null ? "firstname=" + firstname + ", " : "")
				+ (title != null ? "title=" + title + ", " : "") + "price=" + price + ", onsale=" + onsale
				+ ", calendarYear=" + calendarYear + ", "
				+ (description != null ? "description=" + description + ", " : "") + "inventory=" + inventory + "]";
	}


}
