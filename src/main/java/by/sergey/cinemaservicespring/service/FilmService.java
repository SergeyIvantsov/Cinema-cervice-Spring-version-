package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.FilmDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilmService {

    FilmDto saveOrUpdate(FilmDto filmDto);

    FilmDto get(Long id);

    List<FilmDto> getAll();

//    List<FilmDto> getFilmsForPage(int page, int pageSize);
//
//    int getTotalFilmCount();

//старый
//    List<FilmDto> filtersFilms(String title, Integer year, String genre);

//    List<FilmDto> filtersFilms(String title, Integer year, String genre);

    void delete(Long id);

    List<FilmDto> getFilmsByDirectorId(Long directorId);

    List<FilmDto> filtersFilms(String title, Integer year, String genre, Pageable pageable);

    long getTotalFilmCount(String title, Integer year, String genre);
}