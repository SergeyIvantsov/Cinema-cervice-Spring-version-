package by.sergey.cinemaservicespring.service.impl;

import by.sergey.cinemaservicespring.dto.FilmDto;
import by.sergey.cinemaservicespring.dto.FilmFilterDto;
import by.sergey.cinemaservicespring.entity.Director;
import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.repository.DirectorRepository;
import by.sergey.cinemaservicespring.repository.FilmRepository;
import by.sergey.cinemaservicespring.service.FilmService;
import by.sergey.cinemaservicespring.utils.converter.ConverterUtil;
import by.sergey.cinemaservicespring.utils.filtrs.FilmSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;

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

//    @Override
//    public List<FilmDto> filtersFilms(FilmFilterDto filmFilterDto) {
//        List<Film> films;
//        if (filmFilterDto.getTitle() != null || filmFilterDto.getYear() != null || filmFilterDto.getGenre() != null) {
//            films = filmRepository.findByTitleOrYearOrGenreContaining(filmFilterDto.getTitle(),
//                    filmFilterDto.getYear(), filmFilterDto.getGenre());
//        } else {
//            films = filmRepository.findAll();
//        }
//        return films.stream().map(ConverterUtil::convertFilm).collect(Collectors.toList());
//    }

//    @Override
//    public List<FilmDto> getFilmsForPage(int page, int pageSize) {
//        int offset = (page - 1) * pageSize;
//        List<Film> films = filmRepository.getFilmsPage(offset, pageSize);
//        return films.stream()
//                .map(ConverterUtil::convertFilm)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public int getTotalFilmCount() {
//        return filmRepository.getAll().size();
//    }


    @Override
    public List<FilmDto> filtersFilms(String title, Integer year, String genre) {
        Specification<Film> spec = Specification.where(
                (title != null && !title.isEmpty()) ? FilmSpecification.hasTitle(title) : null
        ).and(
                (year != null) ? FilmSpecification.hasYear(year) : null
        ).and(
                (genre != null && !genre.isEmpty()) ? FilmSpecification.hasGenre(genre) : null
        );
        List<Film> films = filmRepository.findAll(spec);

        return films.stream()
                .map(ConverterUtil::convertFilm)
                .collect(Collectors.toList());
    }


    @Override
    public FilmDto saveOrUpdate(FilmDto filmDto) {
        Director referenceById = directorRepository.getReferenceById(filmDto.getDirector().getId());
        Film film = ConverterUtil.convertFilm(filmDto);
        film.setDirector(referenceById);
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
