package sam.learn.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ShopperCountBean {
	{
		Utils.log("Construct", this);
	}
	private int shopperCounter;
	
	public void increamentShopperCounter() {
		shopperCounter++;
	}
	public int getShopperCounter() {
		return shopperCounter;
	}
	public void setShopperCounter(int shopperCounter) {
		this.shopperCounter = shopperCounter;
	}
	public void resetShopperCounter() {
		setShopperCounter(0);
	}
	
	@PostConstruct
	public void init() {
		Utils.log("POST-INIT", this);
		resetShopperCounter();
	}
	@PreDestroy
	public void cleanup() {
		Utils.log("DESTROY", this);
	}
}
