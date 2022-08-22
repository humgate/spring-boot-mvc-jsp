package org.humga.mvcapp.repository;

import org.humga.mvcapp.model.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {
    Collection<Book> findAll();

    Optional<Book> findById(String isbn);

    Book add(Book book);
}
