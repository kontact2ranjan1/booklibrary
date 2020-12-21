package com.fis.booklibrary.casestudy.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fis.booklibrary.casestudy.model.Book;

@FeignClient(name = "book-service", url = "http://book-service")
public interface BookServiceFeignClient {
    @RequestMapping(value="/books", method = RequestMethod.GET )
    ResponseEntity<List<Book>> getBooks();
 
    @RequestMapping(value="/books/{bookId}", method = RequestMethod.GET , produces = "application/json")
    ResponseEntity<Book> getBook(@PathVariable("bookId") String bookId);
    
	@RequestMapping(value="/books/{bookId}", method = RequestMethod.PUT  , produces = "application/json")
	ResponseEntity<Book> updateBook(@PathVariable("bookId") String bookId , @RequestBody Integer remainingCopies);
}