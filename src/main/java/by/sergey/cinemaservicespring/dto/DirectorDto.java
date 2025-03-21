package by.sergey.cinemaservicespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DirectorDto {

    private Long id;

    private String directorName;

    private String directorSurname;

}
