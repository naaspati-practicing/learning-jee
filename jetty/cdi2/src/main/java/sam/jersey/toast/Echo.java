package sam.jersey.toast;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name="echo", urlPatterns="/echo")
public class Echo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	Message m;
	
	static final Logger logger = LoggerFactory.getLogger(Echo.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("logger working");
		resp.getWriter().print(m);
	}
}
