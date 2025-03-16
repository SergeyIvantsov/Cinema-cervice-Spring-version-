package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.FilmDto;
import by.sergey.cinemaservicespring.dto.FilmFilterDto;
import by.sergey.cinemaservicespring.dto.WrapperFilmDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FilmService {

    FilmDto saveOrUpdate(FilmDto filmDto);

    FilmDto get(Long id);

    List<FilmDto> getAll();

    void delete(Long id);

    List<FilmDto> getFilmsByDirectorId(Long directorId);

    /*List<FilmDto> filtersFilms(String title, Integer year, String genre, Integer yearFrom, Integer yearTo, Pageable pageable);

    long getTotalFilmCount(String title, Integer year, String genre, Integer yearFrom, Integer yearTo);*/

    Page<FilmDto> getFilmsByFilter(FilmFilterDto filmFilter);

    WrapperFilmDto createFilm();

    void saveOrUpdateFilmWithActors(WrapperFilmDto wrapperFilmDto);

    WrapperFilmDto getFilmForUpdate(Long id);

}