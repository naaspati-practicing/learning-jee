package sam.servlet;

import javax.persistence.criteria.*;

import sam.jpa.entity.*;
import sam.jpa.entity.Order;

public class CriteriaRestrictingExample {
	private final CriteriaBuilder cb;

	public CriteriaRestrictingExample(CriteriaBuilder cb) {
		this.cb = cb;
	}
	
	public void between() {
		CriteriaQuery<CardTransaction> q = cb.createQuery(CardTransaction.class);
		Root<CreditCard> cc = q.from(CreditCard.class);
		ListJoin<CreditCard, CardTransaction> t = cc.join(CreditCard_.transactionHistory);
		
		q.select(t)
		.where(cb.equal(cc.get(CreditCard_.customer)
				.get(Customer_.accountNum),
				321987L),
				cb.between(t.index(), 0, 9));
		
		/*
		 * SELECT t
		 * FROM CreditCard c JOIN c.transactionHistory t
		 * WHERE c.customer.accountNum = 321987 AND INDEX(t) BETWEEN 0 AND 9
		 */
	}
	public void empty() {
		CriteriaQuery<Order> q = cb.createQuery(Order.class);
		Root<Order> order = q.from(Order.class);
		
		q.where(cb.isEmpty(order.get(Order_.lineItems)))
		.select(order);
	}
	
	public void treat() {
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Order> order = q.from(Order.class);
		
		
	}
}
