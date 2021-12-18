package ru.papont.library.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClientDetailsDto extends ClientSmallDto {
    private List<BookInUseDto> bookInUses;
}
