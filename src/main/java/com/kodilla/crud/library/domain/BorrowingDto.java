package com.kodilla.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class BorrowingDto {
    private Long id;
    private Long bookId;
    private Long readerId;
    private LocalDate borrowed;
    private LocalDate returned;
}
