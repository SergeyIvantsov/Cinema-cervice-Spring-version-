package by.sergey.cinemaservicespring.utils.converter;




import by.sergey.cinemaservicespring.dto.*;
import by.sergey.cinemaservicespring.entity.*;

import java.util.HashSet;
import java.util.Set;

public class ConverterUtil {

    public static ActorDto convertActor(Actor actor) {
      return   ActorDto.builder().id(actor.getId())
              .actorName(actor.getActorName())
              .actorSurname(actor.getActorSurname()).build();
    }

    public static Actor convertActor(ActorDto actordto) {
        return   Actor.builder().id(actordto.getId())
                .actorName(actordto.getActorName())
                .actorSurname(actordto.getActorSurname()).build();
    }

    public static Director convertDirector(DirectorDto directorDto) {
        return   Director.builder().id(directorDto.getId())
                .directorName(directorDto.getDirectorName())
                .directorSurname(directorDto.getDirectorSurname())
                .build();
    }


    public static DirectorDto convertDirector(Director director) {
        return   DirectorDto.builder().id(director.getId())
                .directorName(director.getDirectorName())
                .directorSurname(director.getDirectorSurname())
                .build();
    }

    public static FilmDto convertFilm(Film film) {
        Set<Actor> actors = film.getActors();
        Set<ActorDto> actorDto = new HashSet<>();
        if (actors != null) {
            for (Actor actor : actors) {
                actorDto.add(convertActor(actor));
            }
        }
        return FilmDto.builder()
                .id(film.getId())
                .title(film.getTitle())
                .description(film.getDescription())
                .year(film.getYear())
                .genre(film.getGenre())
                .director(convertDirector(film.getDirector()))
                .actorsDto(actorDto)
                .build();
    }


    public static Film convertFilm(FilmDto dto) {
        Set<ActorDto> actorsDto = dto.getActorsDto();
        Set<Actor> actorsConv = new HashSet<>();
        for (ActorDto actorDto : actorsDto) {
            actorsConv.add(convertActor(actorDto));
        }
        return Film.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .year(dto.getYear())
                .genre(dto.getGenre())
                .director(convertDirector(dto.getDirector()))
                .actors(actorsConv)
                .build();
    }


    public static UserDto convertUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .build();
    }

    public static User convertUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .userName(userDto.getUserName())
                .build();
    }


    public static AccountDto convertAccount(Account account) {
        Set<Film> desiredFilms = account.getDesiredFilms();
        Set<FilmDto> desiredFilmsDto = new HashSet<>();
        for (Film film : desiredFilms) {
            desiredFilmsDto.add(convertFilm(film));
        }
        Set<Film> watchedFilms = account.getWatchedFilms();
        Set<FilmDto> watchedFilmsDto = new HashSet<>();
        for (Film film : watchedFilms){
            watchedFilmsDto.add(convertFilm(film));
        }
        return AccountDto.builder()
                .id(account.getId())
                .userDto(convertUser(account.getUser()))
                .desiredFilmsDto(desiredFilmsDto)
                .watchedFilmsDto(watchedFilmsDto)
                .build();
    }

    public static Account convertAccount(AccountDto accountDto) {
       Set<FilmDto> filmDtoSet = accountDto.getDesiredFilmsDto();
        Set<Film> films = new HashSet<>();
       for (FilmDto filmDto : filmDtoSet) {
           films.add(convertFilm(filmDto));
       }

       Set<FilmDto> watchedFilmsDto = accountDto.getWatchedFilmsDto();
       Set<Film> watchedFilmsSet = new HashSet<>();
       for (FilmDto filmDto : watchedFilmsDto) {
           watchedFilmsSet.add(convertFilm(filmDto));
       }
        return Account.builder()
                .id(accountDto.getId())
                .user(convertUser(accountDto.getUserDto()))
                .desiredFilms(films)
                .watchedFilms(watchedFilmsSet)
                .build();
    }

}
