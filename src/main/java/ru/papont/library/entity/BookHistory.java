package ru.papont.library.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "book_history")
public class BookHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    @JsonIgnore
    private Book book;

    @Column(name = "in_use_from", nullable = false)
    private LocalDate inUseFrom = LocalDate.now();

    @Column(name = "in_use_to", nullable = false)
    private LocalDate inUseTo;

}
