package sam.jpa.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id private int id;
	private Product product;
	private String name;
	
	// image_name -> image_filename
	@ElementCollection
	private Map<String, String> photos;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public Map<String, String> getPhotos() {
		return photos;
	}
	public void setPhotos(Map<String, String> photos) {
		this.photos = photos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

