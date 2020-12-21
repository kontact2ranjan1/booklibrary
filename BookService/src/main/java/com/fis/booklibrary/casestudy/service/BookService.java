package com.fis.booklibrary.casestudy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fis.booklibrary.casestudy.model.Book;
import com.fis.booklibrary.casestudy.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
	
	public Optional<Book> getBook(String bookId){
		return bookRepository.findById(bookId);
	}

	public Book updateCopiesAvailable(String bookId,Integer remainingCopies) {
		Optional<Book> book = bookRepository.findById(bookId);
		
		if(!book.isPresent()) {
			//do nothing
		} else {
			book.get().setCopiesAvailable(remainingCopies);
		}
		return bookRepository.save(book.get());
	}
}
