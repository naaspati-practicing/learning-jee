import static j2html.TagCreator.body;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.head;
import static j2html.TagCreator.hr;
import static j2html.TagCreator.html;
import static j2html.TagCreator.link;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.th;
import static j2html.TagCreator.*;
import static j2html.TagCreator.tr;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;
import j2html.tags.DomContent;
public class Main extends NanoHTTPD {

	public Main(int port) {
		super(port);
	}

	@Override
	public Response serve(IHTTPSession session) {
		if(session.getMethod() != Method.GET)
			return NanoHTTPD.newFixedLengthResponse(Status.BAD_REQUEST, NanoHTTPD.MIME_HTML, "only GET method allowed");
		if(session.getUri().equals("/exit")) {
			stop();
			return NanoHTTPD.newFixedLengthResponse("exiting");
		}

		return NanoHTTPD.newFixedLengthResponse(helloWorld());
	}

	private String helloWorld() {
		return html(
				head(
						title("Hello World"),
						link().withRel("stylesheet").withHref("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css").attr("integrity", "sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T").attr("crossorigin", "anonymous")
						),
				body(h1("Hello World")))
				.withStyle("font-size:0.8em;font-family:monospace;")
				.with(table0("PROPERIES", System.getProperties()))
				.with(table0("ENV", System.getenv()))
				.render();
	}

	@SuppressWarnings({ "rawtypes" })
	private DomContent[] table0(String title, Map map) {
		return new DomContent[]{
				hr(),
				h2(title),
				hr(),
				table(thead(tr(th("key").attr("align", "left"), th("value").attr("align", "left")))).with(rows(map)).withClass("table table-striped table-bordered")
		};
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private DomContent rows(Map map) {
		return each(map.entrySet(), (Entry s) -> tr(td(String.valueOf(s.getKey())), td(String.valueOf(s.getValue()))));
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Main main = new Main(Integer.parseInt(args[0]));
		main.start(SOCKET_READ_TIMEOUT, false);
	}
}
