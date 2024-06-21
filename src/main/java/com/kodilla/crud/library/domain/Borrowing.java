package com.kodilla.crud.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "borrowings")
public class Borrowing {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "borrowed_on")
    private LocalDate borrowed;

    @Column(name = "returned_on")
    private LocalDate returned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    public Borrowing(Long id, LocalDate borrowed, Book book, Reader reader) {
        this.id = id;
        this.borrowed = borrowed;
        this.book = book;
        this.reader = reader;
    }
}
