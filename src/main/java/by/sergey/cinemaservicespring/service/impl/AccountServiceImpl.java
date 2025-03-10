package by.sergey.cinemaservicespring.service.impl;

import by.sergey.cinemaservicespring.entity.Account;
import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.repository.AccountRepository;
import by.sergey.cinemaservicespring.repository.FilmRepository;
import by.sergey.cinemaservicespring.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final FilmRepository filmRepository;

    @Override
    public void addFilmToDesireList(Long accountId, Long filmId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        // Получаем фильм по ID
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Film not found"));

        // Добавляем фильм в список просмотренных
        account.getDesiredFilms().add(film);

        // Сохраняем обновленный аккаунт
        accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get();
        }else
            return null; //toDo нельзя возвращать null
    }
}
