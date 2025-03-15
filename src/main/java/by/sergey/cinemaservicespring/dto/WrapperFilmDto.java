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
public class WrapperFilmDto {

    private FilmDto filmDto;

    private Set<DirectorDto> allDirectors = new HashSet<>();

    private Set<ActorDto> allActors = new HashSet<>();

}
