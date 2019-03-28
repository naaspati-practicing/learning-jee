package sam.learn.ejb.client;

import static j2html.TagCreator.body;
import static j2html.TagCreator.button;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.form;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.style;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thedeanda.lorem.LoremIpsum;

import j2html.tags.DomContent;
import sam.learn.ejb.jpa.CustomerOrderManager;
import sam.learn.ejb.jpa.entities.Address;
import sam.learn.ejb.jpa.entities.Customer;
import sam.learn.ejb.jpa.entities.CustomerOrder;

/**
 * TODO 
 * - test bulk delete, 
 * - ability to add customer, address, order
 * 
 *
 */
@WebServlet("/*")
public class Index extends HttpServlet{
	private static final long serialVersionUID = -2386528250434844064L;
	
	@EJB
	private CustomerOrderManager em;
	
	@PostConstruct
	public void init() {
		LoremIpsum lorem = new LoremIpsum();
		Random r = new Random();
		
		for (int i = 0; i < 10; i++) {
			Customer c = new Customer(lorem.getEmail(), newAddress(lorem), newAddress(lorem));
			c = em.persist(c);
			int size = r.nextInt(10);
			
			for (int j = 0; j < size; j++) {
				CustomerOrder o = new CustomerOrder();
				o.setCreationDate(new Date());
				em.persist(o);
				c.add(o);
			}
			em.merge(c);
		}
	}

	private Address newAddress(LoremIpsum lorem) {
		return new Address(lorem.getCity(), lorem.getStateFull(), lorem.getWords(3), lorem.getWords(3), lorem.getZipCode());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DomContent body = body(
				h1("Servlet CustomerOrderManagerClient at "+req.getContextPath()),
				customers()
				);
		
		try(PrintWriter pw = resp.getWriter()) {
			html(
					head(title("Servlet CustomerOrderManagerClient")),
					style("table { border:0.5px solid lightgray;padding:5px; margin:10px; }"),
					body
					)
			.render(pw);
		}
	}
	

	private DomContent customers() {
		List<Customer> customers = em.findAllCustomers();
		return form(each(customers, e -> customer(e)),
				button("add new customer").withType("submit")
				).withMethod("post"); 
	}


	private DomContent customer(Customer e) {
		return table(
				tr0("id", e.getId()),
				tr0("version", e.getVersion()),
				tr0("email", e.getEmail()),
				tr1("billing_address", address(e.getBillingAddress())),
				tr1("shipping_address", address(e.getShippingAddress())),
				tr1("orders ("+e.getCustomerOrders().size()+")", orders(e.getCustomerOrders()))
				);
	}
	

	private DomContent tr1(String title, DomContent content) {
		return tr(td(title), td(content));
	}

	private DomContent address(Address a) {
		if(a == null)
			return div();
		return table(
				tr0("id",  a.getId()),
				tr0("version",  a.getVersion()),
				tr0("state",  a.getState()),
				tr0("zipcode",  a.getZipCode()),
				tr0("city",  a.getCity()),
				tr0("street 1",  a.getStreet1()),
				tr0("street 2",  a.getStreet2())
				);
	}
	
	private DomContent tr0(String title, Object obj) {
		return tr(td(title), td(obj == null ? "" : obj.toString()));
	}
	
	private DomContent orders(List<CustomerOrder> c) {
		if(c.isEmpty())
			return div();
		
		return each(c, e -> order(e));
	}

	private DomContent order(CustomerOrder o) {
		if(o == null)
			return div();
		
		return table(
				tr0("id",  o.getId()),
				tr0("version",  o.getVersion()),
				tr0("creation_date",  o.getCreationDate()),
				tr0("status",  o.getStatus())
				);
	}
}
