package by.sergey.cinemaservicespring.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class FilmFilterDto extends PageFilterDto{
    private String title;
    private Integer year;
    private Integer yearFrom;
    private Integer yearTo;
    private String genre;

}
