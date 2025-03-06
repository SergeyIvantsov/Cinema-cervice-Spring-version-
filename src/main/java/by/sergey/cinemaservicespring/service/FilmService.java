package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.FilmDto;
import by.sergey.cinemaservicespring.dto.FilmFilterDto;

import java.util.List;

public interface FilmService {

    FilmDto saveOrUpdate (FilmDto filmDto);

    FilmDto get(Long id);

    List<FilmDto> getAll();

//    List<FilmDto> getFilmsForPage(int page, int pageSize);
//
//    int getTotalFilmCount();

    List<FilmDto> filtersFilms(String title, Integer year, String genre);

//    List<FilmDto> filtersFilms(String title, Integer year, String genre);

    void delete(Long id);

    List<FilmDto> getFilmsByDirectorId(Long directorId);
}
