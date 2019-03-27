package sam.learn.ejb.client;

import static j2html.TagCreator.body;
import static j2html.TagCreator.button;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.form;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.iff;
import static j2html.TagCreator.input;
import static j2html.TagCreator.li;
import static j2html.TagCreator.ul;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j2html.tags.DomContent;
import sam.learn.ejb.ShoppingCartBean;

@WebServlet("/ShoppingCartClient")
public class ShoppingCartClient extends HttpServlet implements Helper {
	private static final long serialVersionUID = 6395587307803100572L;
	
	@EJB private ShoppingCartBean bean;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}

	private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s = req.getParameter("wine_name");
		if(s != null && !s.trim().isEmpty())
			bean.addWineItem(s);
		
		List<String> items = bean.getCartItems();
		
		DomContent d = body(h1("Shopping Cart"),
				form(
						input().withName("wine_name"),
						button("add").withType("submit")
						),
				iff(items != null && !items.isEmpty(), div(
						h1("items in cart"),
						ul(each(items, e -> li(e)))
						))
				);
		
		render("Servlet ShoppingCartClient", d, req, resp);
	}
}
