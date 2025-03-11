package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.entity.Account;


public interface AccountService {

    void addFilmToDesireList(Long accountId, Long filmId);

    void addFilmToWatchedList(Long accountId, Long filmId);

    void deleteFilmFromDesireList(Long accountId, Long filmId);

    void deleteFilmFromWatchedList(Long accountId, Long filmId);

    Account findAccountByUsername();

}
