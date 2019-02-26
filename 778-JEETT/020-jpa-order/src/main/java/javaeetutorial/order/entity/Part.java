package javaeetutorial.order.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@IdClass(PartKey.class)
@Entity
@Table(name="PERSISTENCE_ORDER_PART")
@SecondaryTable(
		name="PERSISTENCE_ORDER_PART_DETAIL",
		pkJoinColumns = {
				@PrimaryKeyJoinColumn(name="partNumber", referencedColumnName="partNumber"),
				@PrimaryKeyJoinColumn(name="revision", referencedColumnName="revision"),
		} )

@NamedQuery(name=Part.FIND_ALL, query="SELECT p FROM Part p ORDER BY p.partNumber")
public class Part implements Serializable {
	private static final long serialVersionUID = 1884107473021021756L;
	public static final String FIND_ALL = "Part.FIND_ALL"; 

	@Id @Column(nullable=false)
	private String partNumber;
	
	@Id @Column(nullable=false)
	private int revision;
	
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date revisionDate;
	
	@Lob
	private Serializable drawing;
	
	@Lob
	private String specification;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="BOMPARTNUMBER", referencedColumnName="partNumber"),
		@JoinColumn(name="BOMREVISION", referencedColumnName="revision")
	})
	private Part bomPart;
	
	@OneToMany(mappedBy="bomPart")
	private List<Part> parts;
	
	@OneToOne(mappedBy="part")
	private VendorPart vendorPart;

    public Part() {}
    
    public Part(String partNumber,
            int revision,
            String description,
            Date revisionDate,
            String specification,
            Serializable drawing) {
    	
        this.partNumber = partNumber;
        this.revision = revision;
        this.description = description;
        this.revisionDate = revisionDate;
        this.specification = specification;
        this.drawing = drawing;
        this.parts = new ArrayList<>();
    }

	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}

	public Serializable getDrawing() {
		return drawing;
	}

	public void setDrawing(Serializable drawing) {
		this.drawing = drawing;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Part getBomPart() {
		return bomPart;
	}

	public void setBomPart(Part bomPart) {
		this.bomPart = bomPart;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}
	public void addPart(Part part) {
		getParts().add(part);
		part.setBomPart(this);
	}
	
	public VendorPart getVendorPart() {
		return vendorPart;
	}

	public void setVendorPart(VendorPart vendorPart) {
		this.vendorPart = vendorPart;
	}
}
