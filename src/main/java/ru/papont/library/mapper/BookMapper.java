package ru.papont.library.mapper;

import org.mapstruct.Mapper;
import ru.papont.library.dto.BookDetailsDto;
import ru.papont.library.dto.BookSmallDto;
import ru.papont.library.entity.Book;

@Mapper
public interface BookMapper {
    BookSmallDto toSmall(Book book);

    BookDetailsDto toDetails(Book book);
}
