package org.humga.mvcapp.service.impl;

import org.humga.mvcapp.model.Book;
import org.springframework.stereotype.Service;
import org.humga.mvcapp.repository.BookRepository;
import org.humga.mvcapp.service.BookService;

import java.util.Collection;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository repo;

    public BookServiceImpl(BookRepository repo) {
        this.repo = repo;
    }

    @Override
    public Collection<Book> getBooks() {
        return repo.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return repo.add(book);
    }
}
