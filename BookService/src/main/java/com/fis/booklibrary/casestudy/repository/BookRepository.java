package com.fis.booklibrary.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fis.booklibrary.casestudy.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
