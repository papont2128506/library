package ru.papont.library;

import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @GetMapping("/book")
    public String getAllBooks() {
        return "List all books";
    }

    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable Long id) {
        return "Book by id " + id;
    }

    @PostMapping("/book")
    public String createBook() {
        return "Create Book";
    }

    @PutMapping("/book/{id}")
    public String editBook(@PathVariable Long id) {
        return "Edit Book by id " + id;
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id) {
        return "Delete book by id " + id;
    }

}
