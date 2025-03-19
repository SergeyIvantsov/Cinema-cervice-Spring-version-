package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.AccountDto;

import java.util.Set;


public interface AccountService {

    String addFilmToDesireList(Long filmId);

    String addFilmToWatchedList(Long filmId);

    void deleteFilmFromDesireList(Long filmId);

    void deleteFilmFromWatchedList(Long filmId);

    AccountDto findAccountByUsername();

    Set<Long> getWatchedFilms();

    Set<Long> getDesiredFilms();

}
