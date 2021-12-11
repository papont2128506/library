package ru.papont.library.service;


import org.springframework.stereotype.Service;
import ru.papont.library.entity.Book;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    public Book get(Long id) {
        //TODO: get book by id from DB
        return new Book();
    }

    public List<Book> getAll() {
        //TODO: get all books from DB
        return Collections.emptyList();
    }

    public Book create(Book book) {
        //TODO: save book to DB
        return book;
    }

    public Book update(Long id, Book book) {
        //TODO: get book by id and update it
        return book;
    }

    public void delete(Long id) {
        //TODO: delete book by id from DB

    }

}
