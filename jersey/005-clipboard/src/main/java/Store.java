import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.ws.rs.Path;

@Singleton
public class Store {
	private static final Logger LOGGER = Logger.getLogger(Store.class.getName());

	private final Map<Integer, ClipboardData> data = new ConcurrentHashMap<>();
	private ClipboardData current;
	
	public Map<Integer, ClipboardData> getData() {
		return Collections.unmodifiableMap(data);
	}
	public void put(ClipboardData c) {
		data.put(c.getId(), c);
		LOGGER.info(() -> "STORED: "+c);
		current = c;
	}
	public ClipboardData getCurrent() {
		return current;
	}
	public void setCurrent(ClipboardData current) {
		this.current = current;
	}
	public boolean isCurrentEmpty() {
		return current == null || current.isEmpty();
	}
	public void remove(int id) {
		ClipboardData c = data.remove(id); 
		if(c != null)
			LOGGER.info(() -> "REMOVED: "+c);
	}
	public void clear() {
		int n = data.size();
		if(n == 0) return;
		data.clear();
		LOGGER.info(() -> "REMOVED: "+n+" ClipboardData(s)");
	}
}
