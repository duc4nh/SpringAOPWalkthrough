package com.fdmgroup.springaopwalkthrough;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/context.xml");
		
		Book book = new Book();
		book.setTitle("Title 456");
		book.setPrice(34.99);
		
		BookCatalogue catalogue = context.getBean("bookCatalogue", BookCatalogue.class);
		catalogue.add(book);
		catalogue.updateBookPrice(book, 9.99);
		catalogue.getBook(0);
	}
}