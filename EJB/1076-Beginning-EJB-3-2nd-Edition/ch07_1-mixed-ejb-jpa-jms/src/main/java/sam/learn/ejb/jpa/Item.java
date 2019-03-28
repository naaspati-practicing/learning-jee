package sam.learn.ejb.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public abstract class Item extends BaseEntity {
	private static final long serialVersionUID = -3000144525942573393L;
	
	@Column(length = 4000) private String description;
	@Column(length = 4000, nullable=false) private String name;
	
	@Column(name= "RETAIL_PRICE", nullable=false) private float retailPrice;
	
	@ManyToMany
	@JoinTable(
			name="ITEM_SUPPLIER",
			joinColumns=@JoinColumn(name="ITEM_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="SUPPLIER_ID", referencedColumnName="ID")
			)
	private List<Supplier> suppliers;

	public float getRetailPrice() { return retailPrice; }
	public void setRetailPrice(float retailPrice) { this.retailPrice = retailPrice; }

	public List<Supplier> getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	public void addSupplier(Supplier suppliers) {
		getSuppliers().add(suppliers);
	}
	public void removeSupplier(Supplier suppliers) {
		getSuppliers().remove(suppliers);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
