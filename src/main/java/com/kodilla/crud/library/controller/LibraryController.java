package com.kodilla.crud.library.controller;

import com.kodilla.crud.library.domain.*;
import com.kodilla.crud.library.mapper.BookMapper;
import com.kodilla.crud.library.mapper.BorrowingMapper;
import com.kodilla.crud.library.mapper.PublicationMapper;
import com.kodilla.crud.library.mapper.ReaderMapper;
import com.kodilla.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/library")
public class LibraryController {
    private final DbService service;
    private final BookMapper bookMapper;
    private final PublicationMapper publicationMapper;
    private final ReaderMapper readerMapper;
    private final BorrowingMapper borrowingMapper;

    @GetMapping(path = "/books")
    public ResponseEntity<List<BookDto>> getBooks() {
        List<Book> books = service.getAllBooks();
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(books));
    }

    @GetMapping(path = "/books/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) throws BookNotFoundException {
        return ResponseEntity.ok(bookMapper.mapToBookDto(service.getBook(bookId)));
    }

    @DeleteMapping(path = "/books/{bookId:\\d+}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        service.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/books")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        Book savedBook = service.saveBook(book);
        return ResponseEntity.ok(bookMapper.mapToBookDto(savedBook));
    }

    @PostMapping(path = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        service.saveBook(book);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/publications")
    public ResponseEntity<List<PublicationDto>> getPublications() {
        List<Publication> publications = service.getAllPublications();
        return ResponseEntity.ok(publicationMapper.mapToPublicationDtoList(publications));
    }

    @GetMapping(path = "/publications/{publicationId:\\d+}")
    public ResponseEntity<PublicationDto> getPublication(@PathVariable Long publicationId) throws PublicationNotFoundException {
        return ResponseEntity.ok(publicationMapper.mapToPublicationDto(service.getPublication(publicationId)));
    }

    @GetMapping(path = "/books/publicationId")
    public ResponseEntity<List<BookDto>> getBooksByPublicationId(@RequestParam(value = "publicationId") long publicationId) throws PublicationNotFoundException {
        List<Book> books = service.getBooksByPublicationId(service.getPublication(publicationId));
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(books));
    }

    @DeleteMapping(path = "/publications/{publicationId}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long publicationId) {
        service.deletePublication(publicationId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/publications")
    public ResponseEntity<PublicationDto> updatePublication(@RequestBody PublicationDto publicationDto) {
        Publication publication = publicationMapper.mapToPublication(publicationDto);
        Publication savedPublication = service.savePublication(publication);
        return ResponseEntity.ok(publicationMapper.mapToPublicationDto(savedPublication));
    }

    @PostMapping(path = "/publications", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPublication(@RequestBody PublicationDto publicationDto) {
        Publication publication = publicationMapper.mapToPublication(publicationDto);
        service.savePublication(publication);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/readers")
    public ResponseEntity<List<ReaderDto>> getReaders() {
        List<Reader> readers = service.getAllReaders();
        return ResponseEntity.ok(readerMapper.mapToReaderDtoList(readers));
    }

    @GetMapping(path = "/readers/{readerId}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable Long readerId) throws ReaderNotFoundException {
        return ResponseEntity.ok(readerMapper.mapToReaderDto(service.getReader(readerId)));
    }

    @DeleteMapping(path = "/readers/{readerId}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long readerId) {
        service.deleteReader(readerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/readers")
    public ResponseEntity<ReaderDto> updateReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        Reader savedReader = service.saveReader(reader);
        return ResponseEntity.ok(readerMapper.mapToReaderDto(savedReader));
    }

    @PostMapping(path = "/readers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        service.saveReader(reader);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/borrowings")
    public ResponseEntity<List<BorrowingDto>> getBorrowings() {
        List<Borrowing> borrowings = service.getAllBorrowings();
        return ResponseEntity.ok(borrowingMapper.mapToBorrowingDtoList(borrowings));
    }

    @GetMapping(path = "/borrowings/{borrowingId}")
    public ResponseEntity<BorrowingDto> getBorrowing(@PathVariable Long borrowingId) throws BorrowingNotFoundException {
        return ResponseEntity.ok(borrowingMapper.mapToBorrowingDto(service.getBorrowing(borrowingId)));
    }

    @PutMapping(path = "/borrowings")
    public ResponseEntity<BorrowingDto> updateBorrowing(@RequestBody BorrowingDto borrowingDto) {
        Borrowing borrowing = borrowingMapper.mapToBorrowing(borrowingDto);
        Borrowing savedBorrowing = service.saveUpdatedBorrowing(borrowing);
        return ResponseEntity.ok(borrowingMapper.mapToBorrowingDto(savedBorrowing));
    }

    @PostMapping(path = "/borrowings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBorrowing(@RequestBody BorrowingDto borrowingDto) {
        Borrowing borrowing = borrowingMapper.mapToBorrowing(borrowingDto);
        service.saveBorrowing(borrowing);
        return ResponseEntity.ok().build();
    }
}
