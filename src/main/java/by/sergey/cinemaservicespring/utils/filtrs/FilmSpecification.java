package by.sergey.cinemaservicespring.utils.filtrs;

import by.sergey.cinemaservicespring.entity.Film;
import org.springframework.data.jpa.domain.Specification;

public class FilmSpecification {

    public static Specification<Film> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Film> hasYear(Integer year) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("year"), year);
    }

    public static Specification<Film> hasGenre(String genre) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("genre")), "%" + genre.toLowerCase() + "%");
    }

    public static Specification<Film> hasYearGreaterThanOrEqualTo(Integer yearFrom) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("year"), yearFrom);
    }

    public static Specification<Film> hasYearLessThanOrEqualTo(Integer yearTo) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("year"), yearTo);
    }

    public static Specification<Film> hasYearBetween(Integer yearFrom, Integer yearTo) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("year"), yearFrom, yearTo);
    }


}
