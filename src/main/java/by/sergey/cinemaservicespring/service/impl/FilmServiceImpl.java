package by.sergey.cinemaservicespring.service.impl;

import by.sergey.cinemaservicespring.dto.FilmDto;
import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.repository.FilmRepository;
import by.sergey.cinemaservicespring.service.FilmService;
import by.sergey.cinemaservicespring.utils.converter.ConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
//    private final ConverterUtil converterUtil;

//
//    public FilmDto save(FilmDto filmDto) {
//        Film film = ConverterUtil.convertFilm(filmDto);
//        Film save;
//        try {
//            save = filmRepository.save(film);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//        filmDto.setId(save.getId());
//        return filmDto;
//    }

    @Override
    public FilmDto get(Long id) {
        return ConverterUtil.convertFilm(filmRepository.getReferenceById(id));
    }

    @Override
    public List<FilmDto> getAll() {
        return filmRepository.findAll().stream()
                .map(ConverterUtil::convertFilm)
                .collect(Collectors.toList());
    }

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
    public List<FilmDto> findByName(String search) {
        return filmRepository.findByTitleContaining(search).stream()
                .map(ConverterUtil::convertFilm)
                .collect(Collectors.toList());
    }

    //    @Override
//    public FilmDto update(Long id, FilmDto filmDto) {
//        Film film = ConverterUtil.convertFilm(filmDto);
//        film.setId(id);
//        filmDto.setId(filmRepository.update(id, film).getId());
//        return filmDto;
//    }

    @Override
    public FilmDto saveOrUpdate(FilmDto filmDto) {
        Film film = ConverterUtil.convertFilm(filmDto);
        if (film != null) {
            return ConverterUtil.convertFilm(filmRepository.save(film));
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
