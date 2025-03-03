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
public class FilmDto {

    private Long id;

    private String title;

    private String description;

    private Integer year;

    private String genre;

    private DirectorDto director;

    private Set<ActorDto> actorsDto = new HashSet<>();

}
