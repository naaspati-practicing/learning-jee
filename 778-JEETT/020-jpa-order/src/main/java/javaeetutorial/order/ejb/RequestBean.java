package javaeetutorial.order.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaeetutorial.order.entity.CustomerOrder;
import javaeetutorial.order.entity.LineItem;
import javaeetutorial.order.entity.Part;
import javaeetutorial.order.entity.PartKey;
import javaeetutorial.order.entity.Vendor;
import javaeetutorial.order.entity.VendorPart;

@Stateful
public class RequestBean {
	private static final Logger logger = Logger.getLogger(RequestBean.class.getName());

	@PersistenceContext
	private EntityManager em;

	public void createPart(String partNumber,
			int revision,
			String description,
			java.util.Date revisionDate,
			String specification,
			Serializable drawing) {
		try {
			Part part = new Part(partNumber,
					revision,
					description,
					revisionDate,
					specification,
					drawing);
			logger.log(Level.INFO, "Created part {0}-{1}", new Object[]{partNumber, revision});
			em.persist(part);
			logger.log(Level.INFO, "Persisted part {0}-{1}", new Object[]{partNumber, revision});
		} catch (Exception ex) {
			rethrowEJB(ex);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Part> getAllParts() {
		return em.createNamedQuery(Part.FIND_ALL).getResultList();	
	}

	public void addPartToBillOfMaterial(String bomPartNumber,
			int bomRevision,
			String partNumber,
			int revision) {
		logger.info(() -> String.format("Bom Part Number: %s\nBom Revision: %s\nPart Number: %s\nPart Revision: %s\n", bomPartNumber, bomRevision, partNumber, revision)); 

		try {
			PartKey bomKey = new PartKey(bomPartNumber, bomRevision);
			Part bom = em.find(Part.class, bomKey);

			logger.info(() -> "BOM Part found: "+bom.getPartNumber());

			PartKey partKey = new PartKey(partNumber, revision);
			Part part = em.find(Part.class, partKey);

			logger.info(() -> "Part found: "+part.getPartNumber());
			bom.addPart(part);
		} catch (EJBException e) {
			rethrowEJB(e);
		}
	}
	public void createVendor(int vendorId,
			String name,
			String address,
			String contact,
			String phone) {
		try {
			em.persist(new Vendor(vendorId, name, address, contact, phone));
		} catch (Exception e) {
			rethrowEJB(e);
		}
	}

	public void createVendorPart(String partNumber,
			int revision,
			String description,
			double price,
			int vendorId) {
		try {
			PartKey pkey = new PartKey();
			pkey.setPartNumber(partNumber);
			pkey.setRevision(revision);

			Part part = em.find(Part.class, pkey);

			VendorPart vendorPart = new VendorPart(description, price, part);
			em.persist(vendorPart);

			Vendor vendor = em.find(Vendor.class, vendorId);
			vendor.addVendorPart(vendorPart);
			vendorPart.setVendor(vendor);
		} catch (Exception e) {
			rethrowEJB(e);
		}
	}

	public void createOrder(Integer orderId, char status, int discount, String shipmentInfo) {
		try {
			em.persist(new CustomerOrder(orderId, status, discount, shipmentInfo));
		} catch (Exception e) {
			rethrowEJB(e);
		}
	}

	public List<CustomerOrder> getOrders() {
		try {
			return (List<CustomerOrder>) em.createNamedQuery(CustomerOrder.FIND_ALL).getResultList();
		} catch (Exception e) {
			rethrowEJB(e);;
			return null;
		}
	}

	public void addLineItem(Integer orderId, String partNumber, int revision, int quantity) {
		try {
			CustomerOrder order = em.find(CustomerOrder.class, orderId);
			logger.info(() -> "Found order ID "+ orderId);

			PartKey pkey = new PartKey(partNumber, revision);
			Part part = em.find(Part.class, pkey);

			LineItem lineItem = new LineItem(order, quantity, part.getVendorPart());
			order.addLineItem(lineItem);
		} catch (Exception e) {
			logger.log(Level.WARNING, "Couldn''t add {0} to order ID {1}.", new Object[]{partNumber, orderId});
			rethrowEJB(e);
		}
	}

	public double getOrderPrice(Integer orderId) {
		try {
			CustomerOrder order = em.find(CustomerOrder.class, orderId);
			return order.calculateAmmount();
		} catch (Exception e) {
			return rethrowEJB(e);
		}
	}

	public void adjustOrderDiscount(int adjustment) {
		try {
			@SuppressWarnings("unchecked")
			List<CustomerOrder> orders = em.createNamedQuery( CustomerOrder.FIND_ALL).getResultList();
			orders.forEach(c -> {
				int newDis = c.getDiscount() + adjustment;
				c.setDiscount(newDis > 0 ? newDis : 0);
			});
		} catch (Exception e) {
			rethrowEJB(e);
		}
	}

	public Double getAvgPrice() {
		try {
			return (Double) em.createNamedQuery(VendorPart.FIND_AVERAGE_VENDOR_PART_PRICE).getSingleResult();
		} catch (Exception e) {
			return rethrowEJB(e);
		}
	}
	public Double getTotalPricePerVendor(int vendorId) {
		try {
			return (Double) em.createNamedQuery(VendorPart.FIND_TOTAL_VENDOR_PART_PRICE_BY_VENDOR_ID)
					.setParameter("id", vendorId)
					.getSingleResult();
		} catch (Exception e) {
			throw new EJBException(e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	public List<String> locateVendorsByPartialName(String name) {
		try {
			return (List<String>) em.createNamedQuery(Vendor.FIND_BY_PARTIAL_NAME)
					.setParameter("name", name)
					.getResultList()
					.stream()
					.map(e -> ((Vendor)e).getName())
					.collect(Collectors.toList());
		} catch (Exception e) {
			return rethrowEJB(e);
		}
	}
	public int countAllItems() {
		int count = 0;
		try {
			count = em.createNamedQuery(LineItem.FIND_ALL)
					.getResultList()
					.size();
		} catch (Exception e) {
			return rethrowEJB(e);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<LineItem> getLineItems(int orderId) {
		try {
			return em.createNamedQuery(LineItem.FIND_BY_ORDER_ID)
					.setParameter("orderId", orderId)
					.getResultList();
		} catch (Exception e) {
			return rethrowEJB(e);
		}
	}

	public void removeOrder(Integer orderId) {
		try {
			CustomerOrder order = em.find(CustomerOrder.class, orderId);
			em.remove(order);
		} catch (Exception e) {
			rethrowEJB(e);
		}
	}

	public String reportVendorsByOrder(Integer orderId) {
		try {
			StringBuilder sb = new StringBuilder();
			
			em.createNamedQuery(Vendor.FIND_BY_CUSTOMER_ORDER, Vendor.class)
					.setParameter("id", orderId)
					.getResultList()
					.forEach(v -> sb.append(v.getVendorId()).append("  ").append(v.getName()).append(' ').append(v.getContact()).append('\n'));
			
			return sb.toString();
		} catch (Exception e) {
			return rethrowEJB(e);
		}
	}

	private <E> E rethrowEJB(Exception e) {
		if(e instanceof EJBException)
			throw (EJBException)e;
		else
			throw new EJBException(e);
	}



}
