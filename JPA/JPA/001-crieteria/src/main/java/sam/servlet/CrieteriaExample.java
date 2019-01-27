package sam.servlet;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Root;

import sam.jpa.entity.Address_;
import sam.jpa.entity.ContactInfo;
import sam.jpa.entity.ContactInfo_;
import sam.jpa.entity.Customer;
import sam.jpa.entity.Customer_;
import sam.jpa.entity.Department;
import sam.jpa.entity.Department_;
import sam.jpa.entity.Employee;
import sam.jpa.entity.Employee_;
import sam.jpa.entity.Item;
import sam.jpa.entity.Item_;
import sam.jpa.entity.Order;
import sam.jpa.entity.Order_;
import sam.jpa.entity.Phone;
import sam.jpa.entity.Phone_;
import sam.jpa.entity.Product;
import sam.jpa.entity.Product_;
import sam.jpa.entity.Supplier;
import sam.jpa.entity.Supplier_;
import sam.jpa.entity.Vendor;

public class CrieteriaExample {
	private final CriteriaBuilder cb;

	public CrieteriaExample(CriteriaBuilder cb) {
		this.cb = cb;
	}

	/**
	 * join
	 */
	public CriteriaQuery<String> printerCustomerNames() {

		/*
		 * SELECT c.name
		 * FROM Customer c JOIN c.orders o JOIN o.lineItems i
		 * WHERE i.product.productType = 'printer'
		 */

		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> customer = q.from(Customer.class);
		Join<Customer, Order> orders = customer.join(Customer_.orders);
		Join<Order, Item> item = orders.join(Order_.lineItems);

		q.select(customer.get(Customer_.name))
		.where(
				cb.equal(item.get(Item_.product)
						.get(Product_.productType)
						, "printer"));

		return q;

	}
	public CriteriaQuery<String> printerCustomerNames_2_chained_join() {
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> customer = q.from(Customer.class);
		Join<Order, Item> item = customer.join(Customer_.orders).join(Order_.lineItems);

		q.select(customer.get(Customer_.name))
		.where(
				cb.equal(item.get(Item_.product)
						.get(Product_.productType)
						, "printer"));
		return q;		
	}
	public CriteriaQuery<Tuple> leftJoin() {
		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		Root<Supplier> s = q.from(Supplier.class);

		Join<Supplier, Product> p = s.join(Supplier_.products, JoinType.LEFT);
		p.on(cb.equal(p.get(Product_.status), "inStock"));
		q.groupBy(s.get(Supplier_.name));

		q.multiselect(s.get(Supplier_.name), cb.count(p));

		/*
		 * SELECT s.name, COUNT(p)
		 * FROM Suppliers s LEFT JOIN s.products p
		 * ON p.status = 'inStock'
		 * GROUP BY s.name
		 */
		return q;
	}
	public CriteriaQuery<Department> fetchJoin() {
		CriteriaQuery<Department> q = cb.createQuery(Department.class);
		Root<Department> d = q.from(Department.class);

		d.fetch(Department_.employees, JoinType.LEFT);
		q.where(cb.equal(d.get(Department_.deptno), 1))
		.select(d);

		/*
		 * SELECT d
		 * FROM Department d LEFT JOIN FETCH d.employees
		 * WHERE d.deptno = 1
		 */

		return q;

	}
	public CriteriaQuery<Vendor> pathNavigation() {
		CriteriaQuery<Vendor> q = cb.createQuery(Vendor.class);
		Root<Employee> emp = q.from(Employee.class);
		Join<ContactInfo, Phone> phone = emp
				.join(Employee_.contactInfo)
				.join(ContactInfo_.phones);
		
		q.where(cb.equal(
				emp.get(Employee_.contactInfo)
				.get(ContactInfo_.address)
				.get(Address_.zipcode)
				, "95054"))
		.select(phone.get(Phone_.vendor));

		/*
		 * SELECT p.vendor
		 * FROM Employee e JOIN e.contactInfo.phones p
		 * WHERE e.contactInfo.address.zipcode = '95054'
		 */

		return q;
	}
	public CriteriaQuery<Tuple> mapJoin() {
		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		Root<Item> item = q.from(Item.class);
		MapJoin<Item, String, String> photo = item.join(Item_.photos);
		
		q.multiselect(item.get(Item_.name), photo)
		.where(cb.like(photo.key(), "%egret%"));
		
		/*
		 * SELECT i.name, p
		 * FROM Item i JOIN i.photos p
		 * WHERE KEY(p) LIKE ‘%egret%
		 */

		return q;
	}

}
