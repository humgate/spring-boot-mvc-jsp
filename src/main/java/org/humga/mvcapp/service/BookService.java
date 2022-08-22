package org.humga.mvcapp.service;

import org.humga.mvcapp.model.Book;

import java.util.Collection;


public interface BookService {
    Collection<Book> getBooks();

    Book addBook(Book book);
}