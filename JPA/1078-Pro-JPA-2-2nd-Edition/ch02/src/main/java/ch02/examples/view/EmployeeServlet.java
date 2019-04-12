package ch02.examples.view;

import static j2html.TagCreator.body;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.form;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.input;
import static j2html.TagCreator.p;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.th;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thedeanda.lorem.LoremIpsum;

import ch02.examples.model.Employee;
import ch02.examples.model.EmployeeService;
import j2html.tags.DomContent;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 7381139010591567968L;

	@EJB
	private EmployeeService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}

	protected void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Employee e = null;
		String error = null;

		if(req.getParameter("add-random") != null) 
			e = new Employee(LoremIpsum.getInstance().getName(), (int)(ThreadLocalRandom.current().nextDouble() * 100000)/100d);
		else {
			String name = req.getParameter("name");
			String salary = req.getParameter("salary");

			if(name != null || salary != null) {
				if(name == null || salary == null) {
					error = "required non null: "+(name == null ? "name"+(salary != null ? ", " : "") : "")+(salary == null ? "" : "salary");
				} else {

					try {
						name = name.trim();
						double salaryD = Double.parseDouble(salary);
						e = new Employee(name, salaryD);
					} catch (Exception e2) {
						error = e2.toString();
					}
				}
			}
		}

		if(e != null)
			service.persist(e);

		List<Employee> list = service.findAll();
		if(list == null)
			list = Collections.emptyList();

		try(Writer w = resp.getWriter()) {
			html(
					head(title("Employee ("+list.size()+")")),
					body(
							insertBox(error),
							tableBox(list)
							)).render(w);	
		}

	}
	private DomContent tableBox(List<Employee> list) {
		if(list.isEmpty())
			return h1("Employee(s)");

		return div(
				h1("Employee(s)"),
				table(
						tr(th("id"), th("name"), th("salary")),
						each(list, e -> tr(td(String.valueOf(e.getId())), td(e.getName()), td(String.valueOf(e.getSalary()))))
						)
				);
	}
	private DomContent insertBox(String error) {
		return div(
				h1("Insert"),
				p(error == null ? "" : error).withStyle("color:red"),
				form(table(
						tr(td("Name: "), td(input().withId("name_id").withName("name").attr("required")),
								td("Salary: "), td(input().withId("salary_id").withName("salary").withType("number").attr("required").attr("step", String.valueOf(0.001))),
								td(input().withValue("Insert").withType("submit"))
								)
						).attr("cellspacing", 5)),
				form(tr(td(input().withValue("true").withType("hidden").withName("add-random")),
						td(input().withValue("Add Random").withType("submit"))))
				);
	}

}
