package by.sergey.cinemaservicespring.repository;


import by.sergey.cinemaservicespring.dto.FilmFilterDto;
import by.sergey.cinemaservicespring.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, PagingAndSortingRepository<Film, Long>, JpaSpecificationExecutor<Film> {

//    List<Film> findByTitleOrYearOrGenreContaining(String title, Integer year, String genre);

    List<Film> findByDirectorId(Long directorId);

//    List<Film> getFilmsPage(int offset, int limit);

}
