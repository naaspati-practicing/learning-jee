package sam.learn.ejb.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j2html.tags.DomContent;
import static j2html.TagCreator.*;

public interface Helper {
	default void render(String title, DomContent body, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(PrintWriter pw = resp.getWriter()) {
			html(
					head(title(title)),
					body
					)
			.render(pw);
		}
	}

}
