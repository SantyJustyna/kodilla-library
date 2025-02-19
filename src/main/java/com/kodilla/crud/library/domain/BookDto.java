package com.kodilla.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
    private Long id;
    private Long publicationId;
    private String status;
}
