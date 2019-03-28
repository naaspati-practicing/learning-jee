package sam.learn.ejb.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CH07_INDIVIDUAL")
@NamedQuery(name=Individual.NAMED_QUERY_FIND_ALL, query="select o from Individual o")
public class Individual extends Customer {
	private static final long serialVersionUID = 7390314113847839651L;
	public static final String NAMED_QUERY_FIND_ALL = "Individual.findAll";

	@Column(name = "CC_EXP_DATE", length = 7)
	private String ccExpDate;
	@Column(name = "CC_NUM")
	private String ccNum;
	
	public String getCcExpDate() { return ccExpDate; }
	public void setCcExpDate(String ccExpDate) { this.ccExpDate = ccExpDate; }
	
	public String getCcNum() { return ccNum; }
	public void setCcNum(String ccNum) { this.ccNum = ccNum; }
}
