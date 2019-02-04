package javaeetutorial.web.dukeetf;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Startup
@javax.ejb.Singleton
public class PriceVolumeBean implements Serializable {
	private static final long serialVersionUID = 4690082200089352896L;
	private static final Logger LOGGER = Logger.getLogger(PriceVolumeBean.class.getSimpleName());

	@Resource
	private TimerService timer;
	private volatile double price = 100d;
	private volatile int volume = 300_000;

	@PostConstruct
	private void init() {
		LOGGER.fine(() -> "INIT: "+getClass());
		timer.createIntervalTimer(1000, 1000, new TimerConfig());
	}

	@Timeout 
	void timeout() {
		ThreadLocalRandom ran = ThreadLocalRandom.current(); 
		price += ((int)(ran.nextDouble(-0.5, 0.5)*100))/100d;
		volume += ran.nextInt(-2500, 2500);

		ETFEndPoint.send(price, volume);
	}
}
