package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.AccountDto;
import by.sergey.cinemaservicespring.entity.Account;

import java.util.Optional;


public interface AccountService {

    String addFilmToDesireList(Long filmId);

    String addFilmToWatchedList(Long filmId);

    void deleteFilmFromDesireList(Long filmId);

    void deleteFilmFromWatchedList(Long filmId);

    AccountDto findAccountByUsername();

}
