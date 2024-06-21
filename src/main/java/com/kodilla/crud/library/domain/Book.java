package com.kodilla.crud.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "books")
public class Book {
    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @Column(name = "status")
    private String status = "available";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id")
    private Publication publication;

    @OneToMany(
            targetEntity = Borrowing.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Borrowing> borrowings = new ArrayList<>();

    public Book(Long id, String status, Publication publication) {
        this.id = id;
        this.status = status;
        this.publication = publication;
    }
}
