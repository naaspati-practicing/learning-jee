package org.glassfish.jersey.examples.console.resources;

import static j2html.TagCreator.body;
import static j2html.TagCreator.each;
import static j2html.TagCreator.filter;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;

import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import j2html.tags.ContainerTag;

@Path(FormResource.PATH)
@Produces(MediaType.TEXT_HTML)
public class FormResource {
	public static final String PATH = "/form";
	@Context
	HttpHeaders headers;

	@Path("colours")
	public Colours getColors() {
		return new Colours();
	}

	@GET
	public Response getForm() {
		return Response.ok(ClassLoader.getSystemResourceAsStream("form.html"))
				.cookie(new NewCookie("date", LocalDateTime.now().toString())).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ContainerTag processForm(MultivaluedMap<String, String> formData) {
		return html(head(title("Form results")), body(h2("Hello, you entered the following information: "),
				table(each(filter(formData.entrySet(), e -> !e.getKey().equals("submit")),
						e -> tr(td(e.getKey()), td(first(e.getValue())))),
						tr(td("Cookies: " )),
						each(headers.getCookies().values(), c -> tr(td(c.getName()), td(c.getValue()))))
								.attr("border", 1)
								));
	}

	private String first(List<String> value) {
		return value == null || value.isEmpty() ? null : value.get(0);
	}
}
