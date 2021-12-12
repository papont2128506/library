package ru.papont.library.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity()
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String author;
    private String description;
    private String publisher;
    private String isbn;
    private Integer year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
