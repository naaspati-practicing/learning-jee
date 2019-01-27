package javaeetutorial.hello.webclient;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Optional;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

import javaeetutorial.helloservice.server.Hello;
import javaeetutorial.helloservice.server.HelloService;

import static j2html.TagCreator.*;

@WebServlet(name="HelloServlet", urlPatterns="/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 7250381593778721114L;
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		 resp.setContentType("text/html;charset=UTF-8");
		 
		 html(head(title("Servlet HelloServlet")),
				 body(
				 h1("Servlet HelloServlet at ".concat(req.getContextPath())),
				 p(sayHello(Optional.ofNullable(req.getParameter("name")).orElse("Stranger")))
				 ))
		 .render(resp.getWriter());
		 
		 resp.getWriter().close();
	}
	
	private String sayHello(String name) {
		Hello hello = new HelloService().getHelloPort();
		return hello.sayHello(name);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
}
