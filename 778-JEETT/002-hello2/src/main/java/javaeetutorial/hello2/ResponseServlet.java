package javaeetutorial.hello2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static j2html.TagCreator.*;

@WebServlet(ResponseServlet.PATH)
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 2502327944992989713L;
	public static final String PATH = "/response";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(PrintWriter pw = resp.getWriter()) {
			String username = req.getParameter(GreetingServlet.USERNAME);
			if(!(username == null || username.isEmpty()))
				html(head(title("response")), body(h2("Hello "+username+"!"))).render(pw);
		}
	}

}
