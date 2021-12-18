package ru.papont.library.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientDetailsDto extends ClientSmallDto {
    private List<BookInUseDto> bookInUses;
}
