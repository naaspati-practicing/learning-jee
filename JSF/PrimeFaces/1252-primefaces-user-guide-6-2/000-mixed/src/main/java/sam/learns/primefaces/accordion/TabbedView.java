package sam.learns.primefaces.accordion;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

@SessionScoped
@Named
public class TabbedView implements Serializable { 
	private static final long serialVersionUID = 6210878483874967450L;
	private List<Car> cars;
	
	@PostConstruct
	public void init() {
		cars = Arrays.asList(
				new Car("Y25YIH5", "Fiat", 2014, "Black", 10000, true),
		        new Car("JHF261G", "BMW", 2013, "Blue", 50000, true),
		        new Car("HSFY23H", "Ford", 2012, "Black", 35000, false),
		        new Car("GMDK353", "Volvo", 2014, "White", 40000, true),
		        new Car("345GKM5", "Jaguar", 2011, "Gray", 48000, false)
				);
	}
	public List<Car> getCars() {
		return cars;
	}
	public void onTabChange(TabChangeEvent e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tab Changed", "Active Tab: "+e.getTab().getTitle()));
	}
	public void onTabClose(TabCloseEvent e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tab Closed", "Closed Tab: "+e.getTab().getTitle()));
	}
}
