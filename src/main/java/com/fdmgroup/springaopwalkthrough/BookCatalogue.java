package com.fdmgroup.springaopwalkthrough;

import javax.annotation.Resource;

public class BookCatalogue {
	
	@Resource
	private BookWriteCommand bookWriteCommand;
	@Resource
	private BookReadCommand bookReadCommand;
	
	public void add(Book book) {
		bookWriteCommand.write(book);
	}
	
	public void updateBookPrice(Book book, double price) {
		book.setPrice(price);
		bookWriteCommand.updateBook(book);
	}
	
	public Book getBook(int id) {
		return bookReadCommand.read(id);
	}
}
