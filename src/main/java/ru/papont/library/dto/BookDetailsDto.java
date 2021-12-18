package ru.papont.library.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookDetailsDto extends BookSmallDto {
    private List<BookHistoryDto> bookHistories;
}
