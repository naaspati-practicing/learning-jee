package javaeetutorial.order.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(
			name = Vendor.FIND_BY_PARTIAL_NAME,
			query="SELECT v FROM Vendor v WHERE LOCATE(:name, v.name) > 0"
			),
	@NamedQuery(
			name = Vendor.FIND_BY_CUSTOMER_ORDER,
			query= "SELECT DISTINCT l.vendorPart.vendor FROM CustomerOrder co, IN(co.lineItems) l WHERE co.orderId = :id ORDER BY l.vendorPart.vendor.name"
	)
})
public class Vendor implements Serializable{
	private static final long serialVersionUID = 5729910209309719275L;
	public static final String FIND_BY_PARTIAL_NAME = "Vendor.FIND_BY_PARTIAL_NAME";
	public static final String FIND_BY_CUSTOMER_ORDER = "Vendor.FIND_BY_CUSTOMER_ORDER";

	@Id
	private int vendorId;
	@Column(name="vendorname")
	private String name;
	private String address;
	private String contact;
	private String phone;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="vendor")
	private Collection<VendorPart> vendorParts;

	public Vendor() {}

	public Vendor(int vendorId, 
			String name, 
			String address, 
			String contact, 
			String phone) {
		this.vendorId = vendorId;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.phone = phone;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Collection<VendorPart> getVendorParts() {
		return vendorParts;
	}
	public void setVendorParts(Collection<VendorPart> vendorParts) {
		this.vendorParts = vendorParts;
	}
	public void addVendorPart(VendorPart vp) {
		getVendorParts().add(vp);
	}
}
