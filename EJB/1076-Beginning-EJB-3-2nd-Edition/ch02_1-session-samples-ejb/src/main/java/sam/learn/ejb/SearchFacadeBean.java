package sam.learn.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@Stateless
public class SearchFacadeBean {
	private HashMap<String, String> countryMap = new HashMap<>();

	public List<String> searchWine(String wineType) {
		if(wineType == null)
			return Collections.emptyList();
		
		switch (wineType) {
			case "Red":
				return Arrays.asList("Bordeaux", "Merlot", "Pinot Noir");
			case "White":
				return Collections.singletonList("Chardonnay");
			default:
				return Collections.emptyList();
		}
	}

	public SearchFacadeBean() {
		Utils.log("Construct", this);
	}

	@PostConstruct
	public void init() {
		Utils.log("POST-INIT", this);
		// countryMap is HashMap

		countryMap.put("Australia", "Sauvignon Blanc");
		countryMap.put("Australia", "Grenache");
		countryMap.put("France", "Gewurztraminer");
		countryMap.put("France", "Bordeaux");
	}

	@PreDestroy
	public void destroyObj() {
		Utils.log("DESTROY", this);
		countryMap.clear();
	}

	@AroundInvoke
	public Object timerLog(InvocationContext ctx) throws Exception {
		String beanClassName = ctx.getClass().getName();
		String businessMethodName = ctx.getMethod().getName();
		String target = beanClassName + "." + businessMethodName;
		long startTime = System.currentTimeMillis();
		Utils.logger.info("Invoking " + target);
		
		try {
			return ctx.proceed();
		} finally {
			long totalTime = System.currentTimeMillis() - startTime;
			Utils.logger.info("Exiting " + target+"\n"+"Business method " + businessMethodName + "in " + beanClassName + "takes " + totalTime
					+ "ms to execute");
		}
	}

}
