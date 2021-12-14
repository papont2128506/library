package ru.papont.library.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.papont.library.dto.BookDto;
import ru.papont.library.entity.Book;
import ru.papont.library.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public Page<Book> getAllBooks(@RequestParam Integer page,
                                  @RequestParam Integer size,
                                  @RequestParam(required = false) String query) {
        Pageable pageable = PageRequest.of(page, size);

        return bookService.getAll(query, pageable);
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.get(id);
    }

    @PostMapping("/book")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Book createBook(@Valid @RequestBody BookDto bookDto) {
        return bookService.create(bookDto);
    }

    @PutMapping("/book/{id}")
    public Book editBook(@PathVariable Long id,@Valid @RequestBody BookDto bookDto) {
        return bookService.update(id, bookDto);
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "Deleted book by id " + id;
    }

}
