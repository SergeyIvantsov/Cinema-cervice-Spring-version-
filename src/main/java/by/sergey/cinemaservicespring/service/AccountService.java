package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.entity.Account;

public interface AccountService {
    void addFilmToDesireList(Long accountId, Long filmId);
    Account getAccountById(Long accountId);
}
