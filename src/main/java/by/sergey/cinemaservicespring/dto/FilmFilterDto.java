package by.sergey.cinemaservicespring.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilmFilterDto {
    private String title;
    private Integer year;
    private Integer yearFrom;
    private Integer yearTo;
    private String genre;

}
