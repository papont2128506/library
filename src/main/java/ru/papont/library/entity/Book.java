package ru.papont.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.papont.library.dto.BookDto;

import javax.persistence.*;

@Data
@Entity()
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author;
    private String description;
    private String publisher;
    private String isbn;
    private Integer year;

    public Book(BookDto bookDto) {
        name = bookDto.getName();
        author = bookDto.getAuthor();
        description = bookDto.getDescription();
        publisher = bookDto.getPublisher();
        isbn = bookDto.getIsbn();
        year = bookDto.getYear();
    }
}
