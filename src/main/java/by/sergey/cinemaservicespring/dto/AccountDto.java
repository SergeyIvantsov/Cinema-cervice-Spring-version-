package by.sergey.cinemaservicespring.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountDto {
    private Long id;
    private UserDto userDto;

    private Set<FilmDto> desiredFilmsDto = new HashSet<>();
    private Set<FilmDto> watchedFilmsDto = new HashSet<>();
}
