package by.sergey.cinemaservicespring.utils.converter;

import by.sergey.cinemaservicespring.dto.DirectorDto;
import by.sergey.cinemaservicespring.entity.Director;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DirectorToDirectorDtoConverter implements Converter<Director, DirectorDto> {
    @Override
    public DirectorDto convert(Director source) {
        return   DirectorDto.builder().id(source.getId())
                .directorName(source.getDirectorName())
                .directorSurname(source.getDirectorSurname())
                .build();
    }
}
