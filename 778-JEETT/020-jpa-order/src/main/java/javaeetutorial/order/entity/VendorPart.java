package javaeetutorial.order.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={ @UniqueConstraint(columnNames={"partNumber", "partRevision"}) })
@NamedQueries({
	@NamedQuery(
			name= VendorPart.FIND_ALL,
			query= "SELECT vp FROM VendorPart vp ORDER BY vp.vendorPartNumber"
			),
	@NamedQuery(
			name= VendorPart.FIND_AVERAGE_VENDOR_PART_PRICE,
			query= "SELECT AVG(vp.price) FROM VendorPart vp"
			),
	@NamedQuery(
			name= VendorPart.FIND_TOTAL_VENDOR_PART_PRICE_BY_VENDOR_ID,
			query= "SELECT SUM(vp.price) FROM VendorPart vp WHERE vp.vendor.vendorId = :id"
			),

})
public class VendorPart implements Serializable {
	private static final long serialVersionUID = 5048491245538442708L;

	public static final String FIND_AVERAGE_VENDOR_PART_PRICE = "VendorPart.FIND_AVERAGE_VENDOR_PART_PRICE";
	public static final String FIND_ALL = "VendorPart.FIND_ALL";
	public static final String FIND_TOTAL_VENDOR_PART_PRICE_BY_VENDOR_ID = "VendorPart.FIND_TOTAL_VENDOR_PART_PRICE_PER_VENDOR";

	@TableGenerator(
			name="vendorPartGen",
			table="PERSISTENCE_ORDER_SEQUENCE_GENERATOR",
			pkColumnName="GEN_KEY",
			valueColumnName="GEN_VALUE",
			pkColumnValue="VENDOR_PART_ID",
			allocationSize=10
			)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="vendorPartGen")
	@Id
	private long vendorPartNumber;

	private String description;
	private double price;

	@OneToOne
	@JoinColumns({
		@JoinColumn(name="partNumber", referencedColumnName="partNumber"),
		@JoinColumn(name="partRevision", referencedColumnName="revision")
	})
	private Part part;

	@JoinColumn(name="vendorId")
	@ManyToOne
	private Vendor vendor;
	
	public VendorPart() { }

	public VendorPart(String description, double price, Part part) {
		this.description = description;
		this.price = price;
		this.part = part;
	}
	public long getVendorPartNumber() {
		return vendorPartNumber;
	}
	public void setVendorPartNumber(long vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
}
