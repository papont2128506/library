package ru.papont.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.papont.library.dto.BookDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private boolean available = true;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private List<BookHistory> bookHistories = new ArrayList<>();

    public Book(BookDto bookDto) {
        name = bookDto.getName();
        author = bookDto.getAuthor();
        description = bookDto.getDescription();
        publisher = bookDto.getPublisher();
        isbn = bookDto.getIsbn();
        year = bookDto.getYear();
    }
}
