package by.sergey.cinemaservicespring.utils.converter;

import by.sergey.cinemaservicespring.dto.DirectorDto;
import by.sergey.cinemaservicespring.entity.Director;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DirectorDtoToEntityConverter implements Converter<DirectorDto, Director> {
    @Override
    public Director convert(DirectorDto source) {
        return   Director.builder().id(source.getId())
                .directorName(source.getDirectorName())
                .directorSurname(source.getDirectorSurname())
                .build();
    }
}
