package com.kodilla.crud.library.service;

import com.kodilla.crud.library.controller.BookNotFoundException;
import com.kodilla.crud.library.controller.BorrowingNotFoundException;
import com.kodilla.crud.library.controller.PublicationNotFoundException;
import com.kodilla.crud.library.controller.ReaderNotFoundException;
import com.kodilla.crud.library.domain.Book;
import com.kodilla.crud.library.domain.Borrowing;
import com.kodilla.crud.library.domain.Publication;
import com.kodilla.crud.library.domain.Reader;
import com.kodilla.crud.library.repository.BookRepository;
import com.kodilla.crud.library.repository.BorrowingRepository;
import com.kodilla.crud.library.repository.PublicationRepository;
import com.kodilla.crud.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    private final BookRepository bookRepository;
    private final BorrowingRepository borrowingRepository;
    private final PublicationRepository publicationRepository;
    private final ReaderRepository readerRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByPublicationId(Publication publication) {
        Long publicationId = publication.getId();
        List<Book> books = bookRepository.findAll().stream()
                .filter(n ->n.getPublication().getId().equals(publicationId))
                .toList();
        return books;
    }

    public Book getBook(final Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(final Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    public Borrowing getBorrowing(final Long borrowingId) throws BorrowingNotFoundException {
        return borrowingRepository.findById(borrowingId).orElseThrow(BorrowingNotFoundException::new);
    }

    public Borrowing saveBorrowing(final Borrowing borrowing) {
        borrowing.getBook().setStatus("borrowed");
        return borrowingRepository.save(borrowing);
    }

    public Borrowing saveUpdatedBorrowing(final Borrowing borrowing) {
        borrowing.getBook().setStatus("available");
        return borrowingRepository.save(borrowing);
    }

    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    public Publication getPublication(final Long publicationId) throws PublicationNotFoundException {
        return publicationRepository.findById(publicationId).orElseThrow(PublicationNotFoundException::new);
    }

    public Publication savePublication(final Publication publication) {
        return publicationRepository.save(publication);
    }

    public void deletePublication(final Long publicationId) {
        publicationRepository.deleteById(publicationId);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReader(final Long readerId) throws ReaderNotFoundException {
        return readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public void deleteReader(final Long readerId) {
        readerRepository.deleteById(readerId);
    }
}
