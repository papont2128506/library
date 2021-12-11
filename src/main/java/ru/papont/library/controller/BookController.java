package ru.papont.library.controller;

import org.springframework.web.bind.annotation.*;
import ru.papont.library.entity.Book;
import ru.papont.library.service.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.get(id);
    }

    @PostMapping("/book")
    public Book createBook(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping("/book/{id}")
    public Book editBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "Deleted book by id " + id;
    }

}
