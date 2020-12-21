package com.fis.booklibrary.casestudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fis.booklibrary.casestudy.feignclient.BookServiceFeignClient;
import com.fis.booklibrary.casestudy.model.Book;
import com.fis.booklibrary.casestudy.service.BookService;

@RestController
public class BookController implements BookServiceFeignClient  {

	@Autowired
	private BookService bookService;
	
	@Override
	public ResponseEntity<List<Book>> getBooks(){
		return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Book> getBook(@PathVariable("bookId") String bookId ){
		return new ResponseEntity<>(bookService.getBook(bookId).get(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Book> updateBook(@PathVariable("bookId") String bookId , @RequestBody Integer remainingCopies){
		return new ResponseEntity<>(bookService.updateCopiesAvailable(bookId,remainingCopies), HttpStatus.OK);
	}
}
