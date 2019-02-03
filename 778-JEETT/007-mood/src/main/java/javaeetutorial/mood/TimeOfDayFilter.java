package javaeetutorial.mood;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		  filterName="TimeOfDayFilter",
		  urlPatterns="/*",
		  initParams= {@WebInitParam(name="mood", value="awake")}
		)
public class TimeOfDayFilter implements Filter {
	String mood = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		mood = filterConfig.getInitParameter("mood");
	}
	
	private static final int[][] hours = {
			{1,2,3,4,5,6,23,24},
			{7,13,18},
			{8,9,10,12,14,16,17},
			{11,15},
			{19,20, 21},
			{22}
	};
	private static final String[] moods = {
			"sleepy",
			"hungry",
			"alert",
			"in need of coffee",
			"thoughtful",
			"lethargic"
	};
	
	private static final String[] mood_at_hour = new String[24];
	static {
		
		for (int i = 0; i < hours.length; i++) {
			int[] h = hours[i];
			String mood = moods[i];
			
			for (int j : h) {
				j = j - 1;
				if(mood_at_hour[j] != null)
					throw new IllegalStateException("expected null at: "+j+", found \""+mood_at_hour[j]+"\"");
				
				mood_at_hour[j] = mood;
			}
		} 
		
		for (int i = 0; i < mood_at_hour.length; i++) {
			if(mood_at_hour[i] == null)
				throw new IllegalStateException("expected a value at: "+i);
		}
	}
	
	static int n = 0;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// request.setAttribute("mood", MoodServlet.moods.mood((int) ((System.currentTimeMillis()/1000)%MoodServlet.moods.size())));
		request.setAttribute("mood", mood_at_hour[LocalDateTime.now().getHour()]);
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() { }

}
