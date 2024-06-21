package com.kodilla.crud.library.repository;

import com.kodilla.crud.library.domain.Book;
import com.kodilla.crud.library.domain.Publication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PublicationRepository extends CrudRepository<Publication, Long> {
    @Override
    List<Publication> findAll();

    @Override
    Publication save(Publication publication);

    @Override
    Optional<Publication> findById(Long id);

    @Override
    void deleteById(Long id);
}
