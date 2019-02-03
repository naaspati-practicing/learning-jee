package javaeetutorial.mood;

import static j2html.TagCreator.body;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.img;
import static j2html.TagCreator.p;
import static j2html.TagCreator.title;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j2html.tags.DomContent;


@WebServlet(urlPatterns=MoodServlet.PATH)
public class MoodServlet extends HttpServlet {
	private static final long serialVersionUID = -1197070135568629123L;
	public static final String PATH = "/report";

	public static interface Moods {
		String mood(int index);
		int indexOf(String mood0);
		int size();
		String img(int index);
		String description(int index); 
	}

	public static final Moods moods = new Moods() {
		private final String[][] array = {
				{"unknown","duke.thumbsup.gif", "Duke with thumbs-up gesture"},
				{"sleepy","duke.snooze.gif", "Duke sleeping"},
				{ "alert", "duke.waving.gif", "Duke waving"},
				{"hungry", "duke.cookies.gif", "Duke with cookies"},
				{"lethargic", "duke.handsOnHips.gif", "Duke with hands on hips"},
				{"thoughtful", "duke.pensive.gif", "Duke thinking"}
		};

		@Override
		public String mood(int index) {
			return array[index][0];
		}	
		public int indexOf(String mood) {
			for (int i = 0; i < array.length; i++) {
				if(mood(i).equals(mood))
					return i;
			}
			return -1;
		}
		@Override
		public int size() {
			return array.length;
		}
		@Override
		public String img(int index) {
			return array[index][1];
		}
		@Override
		public String description(int index) {
			return array[index][2];
		}
	};



	private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try(PrintWriter w = resp.getWriter()) {
			html().withLang("en")
			.with(head(title("Servlet MoodServlet")))
			.with(body0(req))
			.render(w);	
		}
	}
	private DomContent body0(HttpServletRequest req) {
		String mood = (String) req.getAttribute("mood");
		int index = moods.indexOf(mood);
		if(index < 0)
			index = 0;
		
		return body(
				h1("Servlet MoodServlet at "+req.getContextPath()),
				p("Duke's mood is: "+mood),
				img().withSrc("resources/images/"+moods.img(index)).withAlt(moods.description(index))
				);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		process(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}



}
