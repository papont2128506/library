package ru.papont.library.mapper;

import org.mapstruct.Mapper;
import ru.papont.library.dto.ClientSmallDto;
import ru.papont.library.dto.ClientDetailsDto;
import ru.papont.library.entity.Client;

@Mapper
public interface ClientMapper {

    ClientSmallDto toSmall(Client client);

    ClientDetailsDto toDetails(Client client);
}
