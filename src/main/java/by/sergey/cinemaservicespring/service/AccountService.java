package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.entity.Account;

import java.util.Optional;


public interface AccountService {

    void addFilmToDesireList(Long filmId);

    void addFilmToWatchedList(Long filmId);

    void deleteFilmFromDesireList(Long filmId);

    void deleteFilmFromWatchedList(Long filmId);

    Optional<Account> findAccountByUsername();

}
