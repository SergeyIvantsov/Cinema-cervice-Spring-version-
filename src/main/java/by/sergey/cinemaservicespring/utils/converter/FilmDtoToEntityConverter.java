//package by.sergey.cinemaservicespring.utils.converter;
//
//import by.sergey.cinemaservicespring.dto.ActorDto;
//import by.sergey.cinemaservicespring.dto.FilmDto;
//import by.sergey.cinemaservicespring.entity.Actor;
//import by.sergey.cinemaservicespring.entity.Film;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//@RequiredArgsConstructor
//public class FilmDtoToEntityConverter implements Converter<FilmDto, Film> {
//
//    private final ActorDtoToEntityConverter actorConverter;
//    private final DirectorDtoToEntityConverter directorConverter;
//
//    @Override
//    public Film convert(FilmDto source) {
//        Set<ActorDto> actorsDto = source.getActorsDto();
//        Set<Actor> actorsConv = new HashSet<>();
//        for (ActorDto actorDto : actorsDto) {
//            actorsConv.add(actorConverter.convert(actorDto));
//        }
//        return Film.builder()
//                .id(source.getId())
//                .title(source.getTitle())
//                .description(source.getDescription())
//                .year(source.getYear())
//                .genre(source.getGenre())
//                .director(directorConverter.convert(source.getDirector()))
//                .actors(actorsConv)
//                .build();
//    }
//}
