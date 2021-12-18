package ru.papont.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.papont.library.dto.BookDetailsDto;
import ru.papont.library.dto.BookDto;
import ru.papont.library.dto.BookSmallDto;
import ru.papont.library.entity.Book;
import ru.papont.library.mapper.BookMapper;
import ru.papont.library.service.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/book")
    public Page<BookSmallDto> getAllBooks(@RequestParam Integer page,
                                          @RequestParam Integer size,
                                          @RequestParam(required = false) String query) {
        Pageable pageable = PageRequest.of(page, size);

        final Page<Book> books = bookService.getAll(query, pageable);
        final List<BookSmallDto> dtos = books.get()
                .map(book -> bookMapper.toSmall(book))
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, books.getTotalElements());
    }

    @GetMapping("/book/{id}")
    public BookDetailsDto getBookById(@PathVariable Long id) {
        return bookMapper.toDetails(bookService.get(id));
    }

    @PostMapping("/book")
    @ResponseStatus(code = HttpStatus.CREATED)
    public BookSmallDto createBook(@Valid @RequestBody BookDto bookDto) {
        return bookMapper.toSmall(bookService.create(bookDto));
    }

    @PutMapping("/book/{id}")
    public BookSmallDto editBook(@PathVariable Long id,@Valid @RequestBody BookDto bookDto) {
        return bookMapper.toSmall(bookService.update(id, bookDto));
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "Deleted book by id " + id;
    }

}
