package ru.papont.library.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BookDetailsDto extends BookSmallDto {
    private List<BookHistoryDto> bookHistories;
}

