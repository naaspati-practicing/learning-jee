package sam.learns.primefaces.accordion;

import java.util.Objects;

public class Car {
	private String id;
    private String brand;
    private int year;
    private String color;
    private int price;
    private boolean sold;
    private String image;
    
    public Car(String id, String brand, int year, String color) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.color = color;
    }
     
    public Car(String id, String brand, int year, String color, int price, boolean sold) {
    	this(id, brand, year, color);
        this.price = price;
        this.sold = sold;
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isSold() {
		return sold;
	}
	public void setSold(boolean sold) {
		this.sold = sold;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(id, other.id);
	}
	
}
