package sam.learn.ejb.client;

import static j2html.TagCreator.a;
import static j2html.TagCreator.body;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.p;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j2html.tags.DomContent;

@WebServlet("/")
public class Index extends HttpServlet implements Helper {
	private static final long serialVersionUID = -2386528250434844064L;

	String[] urls = {
			"ShopperCountClient",
			"SearchFacadeClient",
			"ShoppingCartClient"
	};

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DomContent body = body().with(h1("index"))
				.with(each(Arrays.asList(urls), s -> p(a(s).withHref(s))));

		render("index", body, req, resp);
	}
}
