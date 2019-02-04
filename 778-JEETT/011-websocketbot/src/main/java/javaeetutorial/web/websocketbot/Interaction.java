package javaeetutorial.web.websocketbot;

import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
public interface Interaction {
	public String handle(String msg);
	
	public static class SimpleInteraction implements Interaction {
		private final Predicate<String> filter;
		private final String result;
		private final Supplier<String> resultGetter;

		private SimpleInteraction(Predicate<String> filter, String result, Supplier<String> resultGetter) {
			this.filter = filter;
			this.result = result;
			this.resultGetter = resultGetter;
		}
		public SimpleInteraction(Predicate<String> filter, String result) {
			this(filter, result, null);
		}
		public SimpleInteraction(Predicate<String> filter, Supplier<String> resultGet) {
			this(filter, null, resultGet);
		}
		
		@Override
		public String handle(String msg) {
			if(!filter.test(msg))
				return null;
			if(resultGetter != null)
				return resultGetter.get();
			
			return result;
		}
		
	}  
}
