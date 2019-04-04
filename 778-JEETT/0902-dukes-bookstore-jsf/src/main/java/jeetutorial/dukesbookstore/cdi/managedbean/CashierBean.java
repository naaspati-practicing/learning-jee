package jeetutorial.dukesbookstore.cdi.managedbean;

import java.time.LocalDate;
import java.util.Collection;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectBoolean;
import javax.faces.model.SelectItem;

import jeetutorial.dukesbookstore.ejb.beans.BookRequestBean;

@Model
public class CashierBean extends AbstractBean {
	private static final long serialVersionUID = 1211999856017969315L;

	private static final SelectItem[] newsletterItems = {
			new SelectItem("Duke's Quarterly"),
			new SelectItem("Innovator's Almanac"),
			new SelectItem("Duke's Diet and Exercise Journal"),
			new SelectItem("Random Ramblings")
	};

	@EJB private BookRequestBean bookRequestBean;

	private String name = null;
	private String creditCardNumber = null;
	private LocalDate shipDate;
	private String shippingOption = "2";
	UIOutput specialOfferText = null;
	UISelectBoolean specialOffer = null;
	UIOutput thankYou = null;
	private String stringProperty = "This is a String property";
	private String[] newsletters = {};
	
	
	public SelectItem[] getNewsletteritems() {
		return newsletterItems;
	}
	
	public String getName(){ return this.name; }
	public void setName(String name){ this.name = name; }

	public String getCreditCardNumber(){ return this.creditCardNumber; }
	public void setCreditCardNumber(String creditCardNumber){ this.creditCardNumber = creditCardNumber; }

	public LocalDate getShipDate(){ return this.shipDate; }
	public void setShipDate(LocalDate shipDate){ this.shipDate = shipDate; }

	public String getShippingOption(){ return this.shippingOption; }
	public void setShippingOption(String shippingOption){ this.shippingOption = shippingOption; }

	public UIOutput getSpecialOfferText(){ return this.specialOfferText; }
	public void setSpecialOfferText(UIOutput specialOfferText){ this.specialOfferText = specialOfferText; }

	public UISelectBoolean getSpecialOffer(){ return this.specialOffer; }
	public void setSpecialOffer(UISelectBoolean specialOffer){ this.specialOffer = specialOffer; }

	public UIOutput getThankYou(){ return this.thankYou; }
	public void setThankYou(UIOutput thankYou){ this.thankYou = thankYou; }

	public String getStringProperty(){ return this.stringProperty; }
	public void setStringProperty(String stringProperty){ this.stringProperty = stringProperty; }

	public String[] getNewsletters(){ return this.newsletters; }
	public void setNewsletters(String[] newsletters){ this.newsletters = newsletters; }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String submit() {
		// Calculate and save the ship date
		int days = Integer.parseInt(shippingOption);
		setShipDate(LocalDate.now().plusDays(days));
		
		if(getCart().getTotalPrice() > 100 && !specialOffer.isRendered()) {
			specialOfferText.setRendered(true);
			specialOffer.setRendered(true);
			
			return null;
		} else if(specialOffer.isRendered() && !thankYou.isRendered()) {
			thankYou.setRendered(true);
			return null;
		} else {
			try {
				Collection c = getCart().getItems();
				bookRequestBean.buyAll(c);
				getCart().clear();
				return PATH_BOOK_RECEIPT;
			} catch (Exception e) {
				return PATH_BOOK_ORDER_ERROR; 
			}
		}
			
				
	}


}
