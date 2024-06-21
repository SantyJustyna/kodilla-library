package com.kodilla.crud.library.mapper;

import com.kodilla.crud.library.domain.Publication;
import com.kodilla.crud.library.domain.PublicationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationMapper {
    public Publication mapToPublication(final PublicationDto publicationDto) {
        return new Publication(
                publicationDto.getId(),
                publicationDto.getTitle(),
                publicationDto.getAuthor(),
                publicationDto.getPublicationDate()
        );
    }
    public PublicationDto mapToPublicationDto(final Publication publication) {
        return new PublicationDto(
                publication.getId(),
                publication.getTitle(),
                publication.getAuthor(),
                publication.getPublicationDate()
        );
    }
    public List<PublicationDto> mapToPublicationDtoList(final List<Publication> publicationList) {
        return publicationList.stream()
                .map(this::mapToPublicationDto)
                .toList();
    }
}
