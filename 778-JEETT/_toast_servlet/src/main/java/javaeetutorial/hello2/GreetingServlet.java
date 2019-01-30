package javaeetutorial.hello2;

import static j2html.TagCreator.body;
import static j2html.TagCreator.form;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.img;
import static j2html.TagCreator.input;
import static j2html.TagCreator.p;
import static j2html.TagCreator.title;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j2html.tags.ContainerTag;

@WebServlet(GreetingServlet.PATH)
public class GreetingServlet extends HttpServlet {
	private static final long serialVersionUID = -2730995148878787716L;
	public static final String PATH = "/greeting";
	public static final String USERNAME = "username";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter(USERNAME);
		if(username == null || username.isEmpty()) {
			writeIndex(resp);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(ResponseServlet.PATH);
			rd.include(req, resp);
		}
	}

	private void writeIndex(HttpServletResponse resp) throws IOException {
		try(PrintWriter pw = resp.getWriter()) {
			ContainerTag html = html()
					.withLang("en")
					.with(head(title("Servlet Hello") ))
					.with(body()
							.attr("bgcolor", "white")
							.with(
									img().withSrc("resources/images/duke.waving.gif").withAlt("Duke waving his hand"),
									form().withMethod("get")
									.with(
											h2("Hello, my name is Duke. What's yours?"),
											input().withTitle("My name is: ").withType("text").withName(USERNAME).attr("size", 25),
											p(),
											input().withType("submit").withValue("Submit").withAction(""),
											input().withType("reset").withValue("Reset")
											)
									)
							);


			html.render(pw);
		}
	}
}
