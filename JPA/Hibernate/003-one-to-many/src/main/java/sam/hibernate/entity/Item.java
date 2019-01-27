package sam.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

public class Item {
	protected Set<Bid> bids = new HashSet<>();

	public Set<Bid> getBids() { return bids; }
	public void setBids(Set<Bid> bids) { this.bids = bids; }

}
 