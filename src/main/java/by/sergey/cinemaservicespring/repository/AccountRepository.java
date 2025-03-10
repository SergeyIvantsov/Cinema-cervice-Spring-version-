package by.sergey.cinemaservicespring.repository;

import by.sergey.cinemaservicespring.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
//    void updateAccountByDesiredFilms (Long accountId, Long filmId);


//    Account updateWithDiseredFilms(Integer accountId, Integer filmId);
//
//    Account updateWithWatchedFilms(Integer accountId, Integer filmId);

}
