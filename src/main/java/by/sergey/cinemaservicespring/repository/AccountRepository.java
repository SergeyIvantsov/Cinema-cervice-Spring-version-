package by.sergey.cinemaservicespring.repository;

import by.sergey.cinemaservicespring.entity.Account;
import by.sergey.cinemaservicespring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUser(User user);
}
