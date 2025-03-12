package by.sergey.cinemaservicespring.utils.filtrs;

import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.entity.Film_;
import org.springframework.data.jpa.domain.Specification;

public class FilmSpecification {

    public static Specification<Film> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get(Film_.TITLE)), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Film> hasYear(Integer year) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Film_.YEAR), year);
    }

    public static Specification<Film> hasGenre(String genre) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get(Film_.GENRE)), "%" + genre.toLowerCase() + "%");
    }

    public static Specification<Film> hasYearGreaterThanOrEqualTo(Integer yearFrom) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(Film_.YEAR), yearFrom);
    }

    public static Specification<Film> hasYearLessThanOrEqualTo(Integer yearTo) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(Film_.YEAR), yearTo);
    }

    public static Specification<Film> hasYearBetween(Integer yearFrom, Integer yearTo) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get(Film_.YEAR), yearFrom, yearTo);
    }


}
