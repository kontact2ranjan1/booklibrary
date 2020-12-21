package com.fis.booklibrary.casestudy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.fis.booklibrary.casestudy.model.Book;
import com.fis.booklibrary.casestudy.repository.BookRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BookApplication implements CommandLineRunner{

	@Autowired
	private BookRepository bookRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<Book> books = new ArrayList<>();
		Book b1 = new Book("B1212","History of Amazon Valley","Ross Suarez",0,2);
		Book b2 = new Book("B4232","Language Fundamentals","H S Parkmay",5,5);
		books.add(b1);
		books.add(b2);
		
		bookRepository.saveAll(books);
	}

}
