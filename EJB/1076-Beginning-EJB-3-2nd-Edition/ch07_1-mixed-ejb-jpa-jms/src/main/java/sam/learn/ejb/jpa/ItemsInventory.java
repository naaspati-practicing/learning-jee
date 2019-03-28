package sam.learn.ejb.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CH07_ITEM_INVENTORY")
@NamedQueries({
	@NamedQuery(name=ItemsInventory.NAMED_QUERY_FIND_ALL, query="select o from ItemsInventory o"),
	@NamedQuery(name=ItemsInventory.NAMED_QUERY_FIND_BY_ITEM, query="select o from ItemsInventory o where o.item = :item"),
})
public class ItemsInventory extends Items {
	private static final long serialVersionUID = -5223589622571663709L;

	public static final String NAMED_QUERY_FIND_ALL = "ItemsInventory.findAll";
	public static final String NAMED_QUERY_FIND_BY_ITEM = "ItemsInventory.findByItem";

	@Temporal(TemporalType.DATE)
	@Column(name = "STOCK_DATE")
	private Date stockDate;

	@Column(name = "WHOLESALE_PRICE")
	private float wholesalePrice;
	
	public ItemsInventory() { }

	  public ItemsInventory(int quantity, Item item, Date stockDate, float wholesalePrice) {
	    super(quantity, item);
	    setStockDate(stockDate);
	    setWholesalePrice(wholesalePrice);
	  }

	public Date getStockDate() { return stockDate; }
	public void setStockDate(Date stockDate) { this.stockDate = stockDate; }

	public float getWholesalePrice() { return wholesalePrice; }
	public void setWholesalePrice(float wholesalePrice) { this.wholesalePrice = wholesalePrice; }
}
