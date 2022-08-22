package org.humga.mvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.humga.mvcapp.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/viewBooks")
    /*
    * When GET /viewBooks hits the controller, this method adds to the model passed in method parameter
    * the attribute named "books", with value (data) which is collection of Books.
    * Then it returns view name, which is in our case view-books.jsp, which gets populated with the Books
    * data from model and resulting HTML is returned as HTTP response
    */
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "view-books";
    }
}