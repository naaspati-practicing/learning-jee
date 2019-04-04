package jeetutorial.dukesbookstore.ejb.beans;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import jeetutorial.dukesbookstore.jpa.entities.Book;

@Singleton
@Startup
public class ConfigBean {
	@EJB
	private BookRequestBean bean;
	
	@PostConstruct
	public void init() {
		List<Book> list = Arrays.asList(
				new Book(201, "Duke", "",
		                "My Early Years: Growing Up on *7",
		                30.75, false, 2005, "What a cool book.", 20),
		        new Book(202, "Jeeves", "",
		                "Web Servers for Fun and Profit", 40.75, true,
		                2010, "What a cool book.", 20),
		        new Book(203, "Masterson", "Webster",
		                "Web Components for Web Developers",
		                27.75, false, 2010, "What a cool book.", 20),
		        new Book(205, "Novation", "Kevin",
		                "From Oak to Java: The Revolution of a Language",
		                10.75, true, 2008, "What a cool book.", 20),
		        new Book(206, "Thrilled", "Ben",
		                "The Green Project: Programming for Consumer Devices",
		                30.00, true, 2008, "What a cool book.", 20),
		        new Book(207, "Coding", "Happy",
		                "Java Intermediate Bytecodes", 30.95, true,
		                2010, "What a cool book.", 20)
				);
		
		bean.persist(list);
		
	}
}
