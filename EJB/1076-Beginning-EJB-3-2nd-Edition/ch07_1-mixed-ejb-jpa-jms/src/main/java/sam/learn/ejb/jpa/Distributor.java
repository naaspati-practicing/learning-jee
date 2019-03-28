package sam.learn.ejb.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CH07_DISTRIBUTOR")
@NamedQuery(name=Distributor.NAMED_QUERY_FIND_ALL, query="select o from Distributor o")
public class Distributor extends Customer {
	private static final long serialVersionUID = 7390314113847839651L;

	public static final String NAMED_QUERY_FIND_ALL = "Distributor.findAll";
	
	@Column(name = "COMPANY_NAME", length = 4000) private String companyName;
	
	@Enumerated
	@Column(name = "MEMBER_STATUS") 
	private MemberStatus memberStatus;
	
	private double discount;

	public String getCompanyName() { return companyName; }
	public void setCompanyName(String companyName) { this.companyName = companyName; }

	public MemberStatus getMemberStatus() { return memberStatus; }
	public void setMemberStatus(MemberStatus memberStatus) { this.memberStatus = memberStatus; }

	public double getDiscount() { return discount; }
	public void setDiscount(double discount) { this.discount = discount; }
	
	

}
