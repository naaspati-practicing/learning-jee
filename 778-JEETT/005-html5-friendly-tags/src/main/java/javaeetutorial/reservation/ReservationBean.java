package javaeetutorial.reservation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class ReservationBean implements Serializable {
	private static final long serialVersionUID = -6430868817483380077L;
	
	private String name = "";
	private String totalValue = "120.00";
	private String email = "";
	private String emailAgain = "";
	private String date = "";
	private String tickets = "1";
	private String price = "120";
	private Map<String, Object> ticketsAttrs;
	
	{
		ticketsAttrs = new HashMap<>();
		final Map<String, Object> m = ticketsAttrs;
		m.put("type", "number");
		m.put("min", "1");
		m.put("max", "4");
		m.put("required", "required");
		m.put("title", "Enter a number between 1 and 4 inclusive.");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailAgain() {
		return emailAgain;
	}
	public void setEmailAgain(String emailAgain) {
		this.emailAgain = emailAgain;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTickets() {
		return tickets;
	}
	public void setTickets(String tickets) {
		this.tickets = tickets;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Map<String, Object> getTicketsAttrs() {
		return ticketsAttrs;
	}
	public void setTicketsAttrs(Map<String, Object> ticketAttrs) {
		this.ticketsAttrs = ticketAttrs;
	}
	public void calculateTotal(AjaxBehaviorEvent e) {
		int ticketNum = parse(tickets, 1);
		int ticketPrice = parse(price, 0);
		
		this.totalValue = String.valueOf(ticketNum * ticketPrice).concat(".00");
	}
	private int parse(String s, int defaultValue) {
		s = s.trim();
		if(s.isEmpty())
			return defaultValue;
		
		return Integer.parseInt(s);
	}
	
	public void clear(AjaxBehaviorEvent event) {
        name = "";
        email = "";
        emailAgain = "";
        date = "";
        price = "120.00";
        totalValue = "120.00";
        tickets = "1";
    }

}
