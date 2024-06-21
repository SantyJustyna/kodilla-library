package com.kodilla.crud.library.mapper;

import com.kodilla.crud.library.domain.Publication;
import com.kodilla.crud.library.domain.PublicationDto;
import com.kodilla.crud.library.domain.Reader;
import com.kodilla.crud.library.domain.ReaderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderMapper {
    public Reader mapToReader(final ReaderDto readerDto){
        return new Reader(
              readerDto.getId(),
              readerDto.getName(),
              readerDto.getSurname(),
              readerDto.getCreated()
        );
    }
    public ReaderDto mapToReaderDto(final Reader reader){
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getCreated()
        );
    }
    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(this::mapToReaderDto)
                .toList();
    }
}
