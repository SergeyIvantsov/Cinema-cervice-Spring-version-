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
    private Integer id;
    private UserDto userDto;

    private Set<FilmDto> desiredFilms = new HashSet<>();
    private Set<FilmDto> watchedFilms = new HashSet<>();
}
