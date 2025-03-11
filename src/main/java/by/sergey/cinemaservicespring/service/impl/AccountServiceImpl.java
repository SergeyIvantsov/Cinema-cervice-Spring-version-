package by.sergey.cinemaservicespring.service.impl;

import by.sergey.cinemaservicespring.entity.Account;
import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.entity.User;
import by.sergey.cinemaservicespring.repository.AccountRepository;
import by.sergey.cinemaservicespring.repository.FilmRepository;
import by.sergey.cinemaservicespring.repository.UserRepository;
import by.sergey.cinemaservicespring.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    @Override
    public void addFilmToDesireList(Long accountId, Long filmId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Аккаунт не найден"));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Фильм не найден"));
        if (account.getWatchedFilms().contains(film)) {
            throw new IllegalArgumentException("Вы уже посмотрели этот фильм. Он не может быть добавлен в список желаемых.");
        }
        account.getDesiredFilms().add(film);
        accountRepository.save(account);
    }

    @Override
    public void addFilmToWatchedList(Long accountId, Long filmId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Аккаунт не найден"));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Фильм не найден"));
        if (account.getDesiredFilms().contains(film)) {
            account.getDesiredFilms().remove(film);
        }
        account.getWatchedFilms().add(film);
        accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException("Аккаунт с таким ID " + accountId + " не найден"));
    }

    @Override
    public Account findAccountByUsername() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(userName);
        Account account = accountRepository.findByUser(user);
        return account;
    }



    @Override
    public void deleteFilmFromDesireList(Long accountId, Long filmId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Аккаунт не найден"));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Фильм не найден"));
        account.getDesiredFilms().remove(film);
        accountRepository.save(account);
    }

    @Override
    public void deleteFilmFromWatchedList(Long accountId, Long filmId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Аккаунт не найден"));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Фильм не найден"));
        account.getWatchedFilms().remove(film);
        accountRepository.save(account);
    }
}
