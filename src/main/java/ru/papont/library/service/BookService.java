package ru.papont.library.service;


import org.springframework.stereotype.Service;
import ru.papont.library.entity.Book;
import ru.papont.library.repository.BookRepository;

import java.util.List;
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

    public List<Book> getAll() {
        //get all books from DB
        return bookRepository.findAll();
    }

    public Book create(Book book) {
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
