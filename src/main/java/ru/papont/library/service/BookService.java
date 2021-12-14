package ru.papont.library.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.papont.library.exceptions.NotFoundException;
import ru.papont.library.dto.BookDto;
import ru.papont.library.entity.Book;
import ru.papont.library.repository.BookRepository;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book get(Long id) {
        //get book by id from DB
        Optional<Book> optionalBook = bookRepository.findById(id);

        return optionalBook.orElseThrow(() -> new NotFoundException("Book not found"));
    }

    public Page<Book> getAll(String query, Pageable pageable) {
        if (query != null) {
            return bookRepository.findByQuery("%" + query.toLowerCase() + "%", pageable);
        }
        return bookRepository.findAll(pageable);
    }

    public Book create(BookDto bookDto) {
        Book book = new Book(bookDto);
        return bookRepository.save(book);
    }

    public Book update(Long id, @Valid BookDto bookDto) {
        //get book by id and update it
        Book original = get(id);
        original.setName(bookDto.getName());
        original.setAuthor(bookDto.getAuthor());
        original.setDescription(bookDto.getDescription());
        original.setIsbn(bookDto.getIsbn());
        original.setPublisher(bookDto.getPublisher());
        original.setYear(bookDto.getYear());

        Book saved = bookRepository.save(original);
        return saved;
    }

    public void delete(Long id) {
        Book original = get(id);
        bookRepository.delete(original);
    }

}
