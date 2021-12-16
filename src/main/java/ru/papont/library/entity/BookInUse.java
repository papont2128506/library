package ru.papont.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "book_in_use")
public class BookInUse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Client client;

    @ManyToOne
    private Book book;

    @Column(name = "in_use_from", nullable = false)
    private LocalDate inUseFrom = LocalDate.now();

}