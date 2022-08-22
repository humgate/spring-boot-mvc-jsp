package org.humga.mvcapp.repository.impl;

import org.humga.mvcapp.model.Book;
import org.humga.mvcapp.repository.BookRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component //no underlying db, so no need for @Repository, make it just @Component
public class RepositoryImpl implements BookRepository {

    private final Map<String, Book> storedBooks;

    public RepositoryImpl(Map<String, Book> storedBooks) {
        this.storedBooks = new HashMap<>();
        this.storedBooks.putAll(storedBooks);
    }

    @PostConstruct
    public void init() {
        storedBooks.put("ISBN-1", new Book("ISBN-1", "Book 1", "Book 1 Author"));
        storedBooks.put("ISBN-2", new Book("ISBN-2", "Book 2", "Book 2 Author"));
        storedBooks.put("ISBN-3", new Book("ISBN-3", "Book 3", "Book 3 Author"));
    }

    @Override
    public Collection<Book> findAll() {
        if (storedBooks.isEmpty()) {
            return Collections.emptyList();
        }

        return storedBooks.values();
    }

    @Override
    public Optional<Book> findById(String isbn) {
        return Optional.ofNullable(storedBooks.get(isbn));
    }

    @Override
    public Book add(Book book) {
        storedBooks.put(book.getIsbn(), book);
        return book;
    }
}