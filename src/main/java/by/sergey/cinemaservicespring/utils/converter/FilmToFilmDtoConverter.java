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
//public class FilmToFilmDtoConverter implements Converter<Film, FilmDto> {
//
//    private final ActorToActorDtoConverter actorConverter;
//    private final DirectorToDirectorDtoConverter directorConverter;
//
//    @Override
//    public FilmDto convert(Film source) {
//        Set<Actor> actors = source.getActors();
//        Set<ActorDto> actorDto = new HashSet<>();
//        if (actors != null) {
//            for (Actor actor : actors) {
//                actorDto.add(actorConverter.convert(actor));
//            }
//        }
//        return FilmDto.builder()
//                .id(source.getId())
//                .title(source.getTitle())
//                .description(source.getDescription())
//                .year(source.getYear())
//                .genre(source.getGenre())
//                .director(directorConverter.convert(source.getDirector()))
//                .actorsDto(actorDto)
//                .build();
//    }
//
//}
