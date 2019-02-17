package sam.jetty.jersey;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="simple-servlet", urlPatterns="/simple")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject //not working, dont know why 
	HelloMessage msg;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write(req.getRequestURI()+"\n"+msg);
		resp.getWriter().close();
	}
}
