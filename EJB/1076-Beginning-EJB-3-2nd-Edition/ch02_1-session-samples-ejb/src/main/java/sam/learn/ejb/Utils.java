package sam.learn.ejb;

import java.util.logging.Logger;

public class Utils {
	static final Logger logger = Logger.getGlobal();
	
	public static void log(String msg, Object context) {
		logger.info(msg+": "+context.getClass()+"@"+System.identityHashCode(context)+", "+Thread.currentThread().getStackTrace()[2]);
	}
}
