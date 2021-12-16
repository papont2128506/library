package ru.papont.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.papont.library.entity.BookHistory;

public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {
}