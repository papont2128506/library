package ru.papont.library.entity;

import lombok.Data;

@Data
public class Book {
    private String name;
    private String author;
    private String description;
    private String publisher;
    private String isbn;
    private Integer year;

}
