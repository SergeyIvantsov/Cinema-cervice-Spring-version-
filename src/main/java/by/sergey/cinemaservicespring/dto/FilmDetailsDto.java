package by.sergey.cinemaservicespring.dto;


import by.sergey.cinemaservicespring.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilmDetailsDto {
    private Long id;

    private String country;

    private Integer duration;

    private Double budget;

    private Float rating;

    private Integer priceForMonth;

    private Integer priceForYear;

    private Film film;


}
