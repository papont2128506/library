package ru.papont.library.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.papont.library.dto.BookDto;
import ru.papont.library.entity.Book;
import ru.papont.library.repository.BookRepository;

import java.util.Locale;
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

        return optionalBook.orElseThrow(() -> new RuntimeException("Book not found"));
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

    public Book update(Long id, Book book) {
        //get book by id and update it
        Book original = get(id);
        original.setName(book.getName());
        original.setAuthor(book.getAuthor());
        original.setDescription(book.getDescription());
        original.setIsbn(book.getIsbn());
        original.setPublisher(book.getPublisher());
        original.setYear(book.getYear());

        Book saved = bookRepository.save(original);
        return saved;
    }

    public void delete(Long id) {
        Book original = get(id);
        bookRepository.delete(original);
    }

}
