package by.sergey.cinemaservicespring.service.impl;

import by.sergey.cinemaservicespring.dto.ActorDto;
import by.sergey.cinemaservicespring.dto.FilmDto;
import by.sergey.cinemaservicespring.entity.Actor;
import by.sergey.cinemaservicespring.entity.Director;
import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.repository.ActorRepository;
import by.sergey.cinemaservicespring.repository.DirectorRepository;
import by.sergey.cinemaservicespring.repository.FilmRepository;
import by.sergey.cinemaservicespring.service.FilmService;
import by.sergey.cinemaservicespring.utils.converter.ConverterUtil;
import by.sergey.cinemaservicespring.utils.filtrs.FilmSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;

    @Override
    public FilmDto get(Long id) {
        Film referenceById = filmRepository.getReferenceById(id);
        return ConverterUtil.convertFilm(referenceById);
    }

    @Override
    public List<FilmDto> getAll() {
        return filmRepository.findAll().stream()
                .map(ConverterUtil::convertFilm)
                .collect(Collectors.toList());
    }


    @Override
    public List<FilmDto> filtersFilms(String title, Integer year, String genre, Integer yearFrom, Integer yearTo, Pageable pageable) {
        Specification<Film> spec = Specification.where(
                (title != null && !title.isEmpty()) ? FilmSpecification.hasTitle(title) : null
        ).or(
                (genre != null && !genre.isEmpty()) ? FilmSpecification.hasGenre(genre) : null
        );
        if (year != null) {
            spec = spec.and(FilmSpecification.hasYear(year));
        }
        if (yearFrom != null && yearTo != null) {
            spec = spec.and(FilmSpecification.hasYearBetween(yearFrom, yearTo));
        } else if (yearFrom != null) {
            spec = spec.and(FilmSpecification.hasYearGreaterThanOrEqualTo(yearFrom));
        } else if (yearTo != null) {
            spec = spec.and(FilmSpecification.hasYearLessThanOrEqualTo(yearTo));
        }

        List<Film> films = filmRepository.findAll(spec, pageable).getContent();
        return films.stream()
                .map(ConverterUtil::convertFilm)
                .collect(Collectors.toList());
    }

    @Override
    public long getTotalFilmCount(String title, Integer year, String genre, Integer yearFrom, Integer yearTo) {
        Specification<Film> spec = Specification.where(
                (title != null && !title.isEmpty()) ? FilmSpecification.hasTitle(title) : null
        ).or(
                (genre != null && !genre.isEmpty()) ? FilmSpecification.hasGenre(genre) : null
        );
        if (year != null) {
            spec = spec.and(FilmSpecification.hasYear(year));
        }
        if (yearFrom != null && yearTo != null) {
            spec = spec.and(FilmSpecification.hasYearBetween(yearFrom, yearTo));
        } else if (yearFrom != null) {
            spec = spec.and(FilmSpecification.hasYearGreaterThanOrEqualTo(yearFrom));
        } else if (yearTo != null) {
            spec = spec.and(FilmSpecification.hasYearLessThanOrEqualTo(yearTo));
        }
        return filmRepository.count(spec);
    }


    @Override
    public FilmDto saveOrUpdate(FilmDto filmDto) {
        Director referenceById = directorRepository.getReferenceById(filmDto.getDirector().getId());
        Film film = ConverterUtil.convertFilm(filmDto);
        film.setDirector(referenceById);
        Set<Actor> actors = new HashSet<>();
        for (ActorDto actorDto : filmDto.getActorsDto()) {
            Actor actor = actorRepository.getReferenceById(actorDto.getId());
            actors.add(actor);
        }
        film.setActors(actors);


        Film save = filmRepository.save(film);
        if (film != null) {
            return ConverterUtil.convertFilm(save);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        filmRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FilmDto> getFilmsByDirectorId(Long directorId) {
        return filmRepository.findByDirectorId(directorId).stream()
                .map(ConverterUtil::convertFilm)
                .collect(Collectors.toList());
    }
}
