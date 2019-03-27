package sam.learn.ejb.client;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j2html.tags.DomContent;
import sam.learn.ejb.ShopperCountBean;
import static j2html.TagCreator.*;

@WebServlet("/ShopperCountClient")
public class ShopperCountClient extends HttpServlet implements Helper {
	private static final long serialVersionUID = 6395587307803100572L;
	
	@EJB
	private ShopperCountBean bean;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}

	private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if("true".equals(req.getParameter("increament")))
			bean.increamentShopperCounter();
		else if("true".equals(req.getParameter("reset")))
			bean.resetShopperCounter();	
		
		DomContent d = body(h1("Counter: "+bean.getShopperCounter()),
				form(
						button("increament").withType("submit").withName("increament").withValue("true"),
						br(),
						button("reset").withType("submit").withName("reset").withValue("true")
						)
				);
		render("Servlet ShopperCountClient", d, req, resp);
	}
}
