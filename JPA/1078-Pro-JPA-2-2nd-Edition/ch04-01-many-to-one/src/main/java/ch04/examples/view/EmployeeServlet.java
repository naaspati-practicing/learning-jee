package ch04.examples.view;

import static j2html.TagCreator.body;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.form;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.input;
import static j2html.TagCreator.option;
import static j2html.TagCreator.p;
import static j2html.TagCreator.select;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.th;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thedeanda.lorem.LoremIpsum;

import ch04.examples.ejb.DepartmentService;
import ch04.examples.model.Department;
import ch04.examples.model.Employee;
import j2html.tags.DomContent;

@WebServlet(EmployeeServlet.PATH_PATTERN)
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 7381139010591567968L;
	
	public static final String PATH = "/department/";
	public static final String PATH_PATTERN = "/department/*";
	
	public static final String FORM_MARKER_KEY = "form_marker";
	public static final String MARKER_INSERT_EMPLOYEE = "INSERT_EMPLOYEE";
	public static final String MARKER_INSERT_DEPARTMENT = "INSERT_DEPARTMENT";
	public static final String MARKER_INSERT_RANDOM_EMPLOYEE = "INSERT_RANDOM_EMPLOYEE";
	
	public static final String NAME = "name";
	public static final String SALARY = "salary";
	public static final String DEPARTMENT_NAME = "department_name";
	public static final String DEPARTMENT_ID = "department_id";

	@EJB
	private DepartmentService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		render(req, resp, null);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String marker = req.getParameter(FORM_MARKER_KEY);
		
		if(marker == null) {
			render(req, resp, null);
			return;
		}
		
		String error = null;
		Employee e = null;
		
		switch (marker) {
			case MARKER_INSERT_DEPARTMENT:
				service.addDepartment(req.getParameter(DEPARTMENT_NAME));
				break;
			case MARKER_INSERT_RANDOM_EMPLOYEE:
				e = new Employee(LoremIpsum.getInstance().getName(), (int)(ThreadLocalRandom.current().nextDouble() * 100000)/100d);
				break;
			case MARKER_INSERT_EMPLOYEE:
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
				break;
			default:
				throw new IllegalArgumentException();
		}
		
		if(e != null)
			service.addEmployee(Integer.parseInt(req.getParameter(DEPARTMENT_ID)), e);
		
		render(req, resp, error);
		
	}

	protected void render(HttpServletRequest req, HttpServletResponse resp, String error) throws ServletException, IOException {
		List<Employee> list = service.getAllEmployees();
		List<Department> departments = service.getAllDepartments();
		
		DomContent selectBox = select(each(departments, f -> option(f.getName()).withValue(String.valueOf(f.getId())))).withName(DEPARTMENT_ID);

		try(Writer w = resp.getWriter()) {
			html(
					head(title()),
					body(
							addDepartment(),
							insertBox(error, selectBox),
							tableBox(list)
							)).render(w);	
		}

	}
	private DomContent addDepartment() {
		return form(
				marker(MARKER_INSERT_DEPARTMENT),
				table(
				tr(td("Insert new Department, Name: "), td(input().withId("department_id").withName(DEPARTMENT_NAME).attr("required").attr("maxlength", 10)),
						td(input().withValue("Insert").withType("submit"))
						)
				).attr("cellspacing", 5)).withMethod("POST");
	}
	private DomContent tableBox(List<Employee> list) {
		DomContent h1 = h1("Employee ("+list.size()+")");
		
		if(list.isEmpty())
			return h1;

		return div(
				h1,
				table(
						tr(th("department"), th("id"), th("name"), th("salary")),
						each(list, e -> tr(td(e.getDepartment().getName()), td(String.valueOf(e.getId())), td(e.getName()), td(String.valueOf(e.getSalary()))))
						).attr("cellspacing", 10)
				);
	}
	private DomContent insertBox(String error, DomContent selectBox) {
		return div(
				h1("Insert Employee"),
				p(error == null ? "" : error).withStyle("color:red"),
				form(
						marker(MARKER_INSERT_EMPLOYEE),
						table(
						tr(td("Name: "), td(input().withId("name_id").withName(NAME).attr("required")),
								td("Salary: "), td(input().withId("salary_id").withName(SALARY).withType("number").attr("required").attr("step", String.valueOf(0.001))),
								td("department: "), td(selectBox),
								td(input().withValue("Insert").withType("submit"))
								)
						).attr("cellspacing", 5)).withMethod("POST"),
				form(
						marker(MARKER_INSERT_RANDOM_EMPLOYEE),
						table(
						tr(td("department: "), td(selectBox),
								td(input().withValue("Add Random Employee").withType("submit")))
						).attr("cellspacing", 5)
						).withMethod("POST")
				);
	}
	private DomContent marker(String s) {
		return input().withType("hidden").withName(FORM_MARKER_KEY).withValue(s);
	}

}
