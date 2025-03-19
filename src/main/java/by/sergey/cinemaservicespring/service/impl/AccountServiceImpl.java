package by.sergey.cinemaservicespring.service.impl;

import by.sergey.cinemaservicespring.dto.AccountDto;
import by.sergey.cinemaservicespring.entity.Account;
import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.entity.User;
import by.sergey.cinemaservicespring.repository.AccountRepository;
import by.sergey.cinemaservicespring.repository.FilmRepository;
import by.sergey.cinemaservicespring.repository.UserRepository;
import by.sergey.cinemaservicespring.service.AccountService;
import by.sergey.cinemaservicespring.utils.converter.ConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    @Override
    public AccountDto findAccountByUsername() {
        Account account = getAccount();
        AccountDto accountDto = ConverterUtil.convertAccount(account);
        return accountDto;
    }

    @Override
    public String addFilmToDesireList(Long filmId) {
        Account account = getAccount();
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Фильм не найден"));
        if (account.getWatchedFilms().contains(film)) {
            throw new IllegalArgumentException("Вы уже посмотрели этот фильм. Он не может быть добавлен в список желаемых.");//toDo сделать кастомный exception
        }
        account.getDesiredFilms().add(film);
        accountRepository.save(account);
        return film.getTitle();
    }

    @Override
    public String addFilmToWatchedList(Long filmId) {
        Account account = getAccount();
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Фильм не найден"));
        if (account.getDesiredFilms().contains(film)) {
            account.getDesiredFilms().remove(film);
        }
        account.getWatchedFilms().add(film);
        accountRepository.save(account);
        return film.getTitle();
    }

    @Override
    public void deleteFilmFromDesireList(Long filmId) {
        Account account = getAccount();
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Фильм не найден"));
        account.getDesiredFilms().remove(film);
        accountRepository.save(account);
    }

    @Override
    public void deleteFilmFromWatchedList(Long filmId) {
        Account account = getAccount();
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Фильм не найден"));
        account.getWatchedFilms().remove(film);
        accountRepository.save(account);
    }

    @Override
    public Set<Long> getWatchedFilms() {
        Account account = getAccount();
        return account.getWatchedFilms()
                .stream()
                .map(Film::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Long> getDesiredFilms() {
        Account account = getAccount();
        return account.getDesiredFilms()
                .stream().map(Film::getId)
                .collect(Collectors.toSet());
    }

    private Account getAccount(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(userName);
        Optional<Account> acc = Optional.ofNullable(accountRepository.findByUser(user));
        return acc.get();
    }


}
