package ru.papont.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.papont.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("select b from Book b where lower(b.name) like :query " +
            "or lower(b.author) like :query " +
            "or lower(b.description) like :query " +
            "or lower(b.isbn) like :query")
    Page<Book> findByQuery(String query, Pageable pageable);
}