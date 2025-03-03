package by.sergey.cinemaservicespring.repository;


import by.sergey.cinemaservicespring.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, PagingAndSortingRepository<Film, Long> {

    List<Film> findByTitleContaining(String name);

    List<Film> findByDirectorId(Long directorId);

//    List<Film> getFilmsPage(int offset, int limit);

}
