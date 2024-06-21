package com.kodilla.crud.library.mapper;

import com.kodilla.crud.library.domain.*;
import com.kodilla.crud.library.repository.BookRepository;
import com.kodilla.crud.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingMapper {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;

    public Borrowing mapToBorrowing(final BorrowingDto borrowingDto) {
        return new Borrowing(
                borrowingDto.getId(),
                borrowingDto.getBorrowed(),
                borrowingDto.getReturned(),
                bookRepository.findById(borrowingDto.getBookId()).get(),
                readerRepository.findById(borrowingDto.getReaderId()).get()
        );
    }
    public BorrowingDto mapToBorrowingDto(final Borrowing borrowing) {
        return new BorrowingDto(
                borrowing.getId(),
                borrowing.getBook().getId(),
                borrowing.getReader().getId(),
                borrowing.getBorrowed(),
                borrowing.getReturned()
        );
    }
    public List<BorrowingDto> mapToBorrowingDtoList(final List<Borrowing> borrowingList) {
        return borrowingList.stream()
                .map(this::mapToBorrowingDto)
                .toList();
    }
}
