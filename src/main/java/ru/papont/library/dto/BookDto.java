package ru.papont.library.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class BookDto {

    @NotBlank(message = "is mandatory")
    private String name;

    @NotBlank(message = "is mandatory")
    private String author;
    private String description;
    private String publisher;

    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})$)[\\d-]+$")
    private String isbn;

    @Range(min = 1900, max = 2100)
    private Integer year;

}
