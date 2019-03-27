package sam.learn.ejb.client;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j2html.tags.DomContent;
import sam.learn.ejb.SearchFacadeBean;
import sam.learn.ejb.ShopperCountBean;
import static j2html.TagCreator.*;

@WebServlet("/SearchFacadeClient")
public class SearchFacadeClient extends HttpServlet implements Helper {
	private static final long serialVersionUID = 6395587307803100572L;

	@EJB private ShopperCountBean counter;
	@EJB private SearchFacadeBean search;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}

	private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		counter.increamentShopperCounter();
		List<String> list = search.searchWine(req.getParameter("search"));

		DomContent d = body(h1("Counter: "+counter.getShopperCounter()),
				form(
						h1("search"),
						button("Red").withType("submit").withName("search").withValue("Red"),
						br(),
						button("White").withType("submit").withName("search").withValue("White"),
						br(),
						iff(!list.isEmpty(), div(
								h1("found wines"),
								ul(each(list, e -> li(e)))
								))
						)
				);

		render("Servlet SearchFacadeClient", d, req, resp);
	}
}
