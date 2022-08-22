package org.humga.mvcapp.controller;

import org.humga.mvcapp.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.humga.mvcapp.service.BookService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /*
    * @ModelAttribute on method level indicates the purpose of the method is to add one or more model attributes.
    * This method is invoked before any @RequestMapping method invoked.
    * In our case we add msg attribute to the model.
    */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "Company inc.");
    }

    /*
     * When GET /viewBooks hits the controller, this method adds to the model passed in method parameter,
     * the attribute named "books" with value (data) which is collection of Books.
     * Then it returns view name, which is in our case is view-books.jsp, which accesses books
     * data from model and renders resulting HTML with data. Finally, it is returned as HTTP response
     */
    @GetMapping("/viewBooks")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "view-books";
    }

    /*
     * When GET /addBook hits the controller, this method adds to the model variable passed in method parameter,
     * the attribute named "book" with empty Book object (data). That empty Book needs to be added to passed model
     * parameter - to be correctly bound to book form fields in add-Book.jsp.
     * The modelAttribute parameter provided by the <form:form> binds the book attribute passed to this
     * method, to the form, which will be filled when submitting the form.
     * Then it returns view name (add-book.jsp), which form fields are populated with bindings
     * to the Book fields and resulting HTML is returned as HTTP response
     */
    @GetMapping("/addBook")
    public String addBookView(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    /*
     * When POST /addBook hits the controller, post request contains book data filled in the submitted form.
     * @ModelAttribute:
     * - creates new Book object
     * - sets all the book fields to values from request parameters matching book field names.
     * - adds created book object to model allowing views to access them
     * @ModelAttribute is optional. By default, any argument that is not a simple value type
     * (as determined by BeanUtils#isSimpleProperty) and is not resolved by any other argument resolver
     * is treated as if it were annotated with @ModelAttribute
     */
    @PostMapping("/addBook")
    public RedirectView addBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/book/addBook", true);
        Book savedBook = bookService.addBook(book);
        redirectAttributes.addFlashAttribute("savedBook", savedBook);
        redirectAttributes.addFlashAttribute("addBookSuccess", true);
        return redirectView;
    }
}