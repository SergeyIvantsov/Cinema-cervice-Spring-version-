package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.FilmDto;

import java.util.List;

public interface FilmService {

    FilmDto saveOrUpdate (FilmDto filmDto);

    FilmDto get(Long id);

    List<FilmDto> getAll();

//    List<FilmDto> getFilmsForPage(int page, int pageSize);
//
//    int getTotalFilmCount();

    List<FilmDto> findByName(String search);

//    FilmDto update(Long id, FilmDto film);

    void delete(Long id);

    List<FilmDto> getFilmsByDirectorId(Long directorId);
}
