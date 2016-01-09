package com.fdmgroup.springaopwalkthrough;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // code that sets up a given observer-observable relationship. Connect code to be ran and trigger for that code
public class CatalogueLogger {

	private Logger log = Logger.getLogger(CatalogueLogger.class);
	
	// Define a pointcut (which we want to trigger some advice)
	// * means any thing (basic wild-card)
	// .. can only go in arguments, any number of any type
	@Before("execution(public void com.fdmgroup.springaopwalkthrough.BookCatalogue.add(Book))")
	public void logNewBook(JoinPoint joinPoint) {
		
		Object firstArgument = joinPoint.getArgs()[0];
		String title = null;
		if(firstArgument instanceof Book) {
			Book book = (Book) firstArgument;
			title = book.getTitle();
		}
		
		log.info("Before new book named " + title + " is added");
	}
	
	@Before("execution(public void com.fdmgroup.springaopwalkthrough.BookCatalogue.add(Book)) && args(book)")
	public void logNewBookAlte(Book book) {
		log.info("Before new book named " + book.getTitle() + " is added");
	}
	
	@Before("execution(public void com.fdmgroup.springaopwalkthrough.BookCatalogue.updateBookPrice(Book, double)) && args(book, price)")
	public void logUpdateBookPpice(Book book, double price) {
		log.info("Before: Book named " + book.getTitle() + " has been updated to price " + price);
	}
	
	// Define a pointcut we can reuse
	@Pointcut("execution(public Book com.fdmgroup.springaopwalkthrough.BookCatalogue.getBook(int))")
	public void getBookPointCut() {}
	
	// Reuse that pointcut for a number of different pieces of Advice
	@Before("getBookPointCut()")
	public void logGetBook() {
		log.info("Before get book");
	}
	@After("getBookPointCut()")
	public void logGetBook2() {
		log.info("AFter get book");
	}
	@AfterReturning("getBookPointCut()")
	public void logGetBook3() {
		log.info("AfterReturning get book");
	}
	 
}
