package by.sergey.cinemaservicespring.service.impl;

import by.sergey.cinemaservicespring.dto.*;
import by.sergey.cinemaservicespring.entity.Actor;
import by.sergey.cinemaservicespring.entity.Director;
import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.repository.ActorRepository;
import by.sergey.cinemaservicespring.repository.DirectorRepository;
import by.sergey.cinemaservicespring.repository.FilmRepository;
import by.sergey.cinemaservicespring.service.ActorService;
import by.sergey.cinemaservicespring.service.DirectorService;
import by.sergey.cinemaservicespring.service.FilmService;
import by.sergey.cinemaservicespring.utils.converter.ConverterUtil;
import by.sergey.cinemaservicespring.utils.filtrs.FilmSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final DirectorService directorService;
    private final ActorService actorService;

    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final AccountServiceImpl accountServiceImpl;

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
    public Page<FilmDto> getFilmsByFilter(FilmFilterDto filmFilter) {
        try {
            int pageNumber = filmFilter.getPageNumber() - 1;
            if (pageNumber < 0) {
                pageNumber = 0;
            }

            Pageable pageable = PageRequest.of(pageNumber, filmFilter.getPageSize());

            Specification<Film> spec = Specification
                    .where((filmFilter.getTitle() != null && !filmFilter.getTitle().isEmpty()) ? FilmSpecification.hasTitle(filmFilter.getTitle()) : null)
                    .or((filmFilter.getGenre() != null && !filmFilter.getGenre().isEmpty()) ? FilmSpecification.hasGenre(filmFilter.getGenre()) : null);
            if (filmFilter.getYear() != null) {
                spec = spec.and(FilmSpecification.hasYear(filmFilter.getYear()));
            }
            if (filmFilter.getYearFrom() != null && filmFilter.getYearTo() != null) {
                spec = spec.and(FilmSpecification.hasYearBetween(filmFilter.getYearFrom(), filmFilter.getYearTo()));
            } else if (filmFilter.getYearFrom() != null) {
                spec = spec.and(FilmSpecification.hasYearGreaterThanOrEqualTo(filmFilter.getYearFrom()));
            } else if (filmFilter.getYearTo() != null) {
                spec = spec.and(FilmSpecification.hasYearLessThanOrEqualTo(filmFilter.getYearTo()));
            }

            Page<Film> filmPage = filmRepository.findAll(spec, pageable);

            if (pageNumber > filmPage.getTotalPages()) {
                pageNumber = filmPage.getTotalPages() - 1;
                pageable = PageRequest.of(pageNumber, filmFilter.getPageSize());
                filmPage = filmRepository.findAll(spec, pageable);
            }
            return filmPage.map(ConverterUtil::convertFilm);
        } catch (Exception e) {
            throw e;
        }
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
    public WrapperFilmDto createFilm (){
        WrapperFilmDto wrapperFilmDto = new WrapperFilmDto();
        wrapperFilmDto.setFilmDto(new FilmDto());
        Set<DirectorDto> directors = new HashSet<>();
        directors.addAll(directorService.getAll());
        wrapperFilmDto.setAllDirectors(directors);
        Set<ActorDto> actors = new HashSet<>();
        actors.addAll(actorService.findAll());
        wrapperFilmDto.setAllActors(actors);
        return wrapperFilmDto;
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

    @Override
    public void saveOrUpdateFilmWithActors(WrapperFilmDto wrapperFilmDto) {
        Set<Long> actorIds = wrapperFilmDto.getSelectedIdActors();
        Set<ActorDto> actorsDto = actorService.findActorsByIds(actorIds);
        wrapperFilmDto.getFilmDto().setActorsDto(actorsDto);
        saveOrUpdate(wrapperFilmDto.getFilmDto());
    }


    @Override
    public WrapperFilmDto getFilmForUpdate(Long id) {
        WrapperFilmDto wrapperFilmDto = new WrapperFilmDto();
        wrapperFilmDto.setFilmDto(get(id));
        wrapperFilmDto.setAllDirectors(directorService.getAll());
        wrapperFilmDto.setAllActors(actorService.findAll());
        Set<Long> selectedIds = wrapperFilmDto.getFilmDto().getActorsDto().stream()
                .map(ActorDto::getId)
                .collect(Collectors.toSet());
        wrapperFilmDto.setSelectedIdActors(selectedIds);
        return wrapperFilmDto;
    }

}
