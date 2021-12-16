package ru.papont.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.papont.library.entity.Book;
import ru.papont.library.entity.BookInUse;
import ru.papont.library.entity.Client;

import java.util.Optional;

public interface BookInUseRepository extends JpaRepository<BookInUse, Long> {

    Optional<BookInUse> findByClientAndBook(Client client, Book book);
}