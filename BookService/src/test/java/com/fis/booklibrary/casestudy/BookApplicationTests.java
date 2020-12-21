package com.fis.booklibrary.casestudy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fis.booklibrary.casestudy.model.Book;
import com.fis.booklibrary.casestudy.repository.BookRepository;
import com.fis.booklibrary.casestudy.service.BookService;

@SpringBootTest
class BookApplicationTests {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getBook() {
		Book b = new Book("B1313","Java Programming","SK",6,6);
		bookRepository.save(b);
		
		//when
		Optional<Book> book = bookService.getBook("B1313");
		
		assertThat(book.isPresent());
				
		//then
		assertThat(book.get().getBookName())
	      .isEqualTo(b.getBookName());
	}
	
	@Test
	public void getBooks() {
		Book b = new Book("B1313","Java Programming","SK",6,6);
		Book b1 = new Book("B1312","Java Programming1","SK1",6,6);
		
		List<Book> books = new ArrayList<>();
		books.add(b);
		books.add(b1);
		
		bookRepository.saveAll(books);
		
		List<Book> book = bookService.getBooks();
		
		assertThat(!book.isEmpty());
	}
	
	@Test
	public void updateBookCopiesAvailable() {
		Book b = new Book("B1313","Java Programming","SK",6,6);
		bookRepository.save(b);
		
		//Updated Book Copies Available to 4
		Book book = bookService.updateCopiesAvailable(b.getBookId(), 4);
		
		//Checking that updated count should be equal to 4
		assertThat(book.getCopiesAvailable()).isEqualTo(4) ;
		
	}
}
