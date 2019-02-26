package javaeetutorial.hello.webclient;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

import javaeetutorial.helloservice.HelloService;
import static j2html.TagCreator.*;

@WebServlet(urlPatterns="/sayHello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 5049499246062048962L;
	
	@WebServiceRef(wsdlLocation = "http://localhost:8080/page/HelloService?wsdl")
	private HelloService service;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter writer = resp.getWriter()) {
			html().withLang("en")
			.with(head(title("Servlet HelloServlet")))
			.with(body(
					h1("Servlet HelloServlet at "+req.getContextPath ()),
					p(service.getHelloPort().sayHello("world is small"))
					))
			.render(writer);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

}
