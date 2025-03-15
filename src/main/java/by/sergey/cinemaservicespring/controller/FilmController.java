package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.dto.ActorDto;
import by.sergey.cinemaservicespring.dto.FilmDto;
import by.sergey.cinemaservicespring.dto.FilmFilterDto;
import by.sergey.cinemaservicespring.dto.WrapperFilmDto;
import by.sergey.cinemaservicespring.entity.Actor;
import by.sergey.cinemaservicespring.service.ActorService;
import by.sergey.cinemaservicespring.service.DirectorService;
import by.sergey.cinemaservicespring.service.FilmService;
import by.sergey.cinemaservicespring.utils.converter.ConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final DirectorService directorService;
    private final ActorService actorService;


    @GetMapping("/getFilms")
    public String getFilterFilms(@ModelAttribute("filmsFilter") FilmFilterDto filmFilterDto,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 Model model) {

        filmFilterDto.setPageNumber(page + 1);
        filmFilterDto.setPageSize(size);
        Page<FilmDto> filmsPage = filmService.getFilmsByFilter(filmFilterDto);
        model.addAttribute("allFilms", filmsPage);
        model.addAttribute("totalPages", filmsPage.getTotalPages());
        model.addAttribute("currentPage", filmsPage.getNumber());
        model.addAttribute("size", size);
        return "films";
    }

    @GetMapping("/createFilm")
    public String createFilm(Model model) {
        model.addAttribute("wrapperFilm", filmService.createFilm());
        return "createFilm";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("wrapperFilm") WrapperFilmDto wrapperFilmDto) {
        Set<Long> actorIds = Arrays.stream(wrapperFilmDto.getFilmDto().getActorsMass())
                .map(id -> Long.parseLong(id)) // Преобразуем строковые id в Long
                .collect(Collectors.toSet());
//todo вынести всю логику в сервис
        // Получаем актеров из базы данных по их id
        Set<ActorDto> actorsDto = new HashSet<>();
        for (Long actorId : actorIds) {
            //todo в БД передать список id (запрос через IN)
            Actor referenceById = actorService.getReferenceById(actorId);
            ActorDto actorDto = ConverterUtil.convertActor(referenceById);
            actorsDto.add(actorDto);
        }

        // Устанавливаем актеров в FilmDto
        wrapperFilmDto.getFilmDto().setActorsDto(actorsDto);

        // Сохраняем или обновляем фильм
        filmService.saveOrUpdate(wrapperFilmDto.getFilmDto());
        return "redirect:/getFilms";
    }

    @GetMapping("/updateFilm")
    public String showUpdateForm(@RequestParam("id") Long id, Model model) {
        FilmDto filmDto = filmService.get(id);
        model.addAttribute("filmDto", filmDto);
        model.addAttribute("allDirectors", directorService.getAll());
        return "updateFilm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("wrapperFilm") WrapperFilmDto wrapperFilmDto) {
        filmService.saveOrUpdate(wrapperFilmDto.getFilmDto());
        return "redirect:/getFilms";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        filmService.delete(id);
        return "redirect:/getFilms";
    }

    @GetMapping("/getFilmsByDirectorId/{directorId}")
    public String getFilmsByDirector(@PathVariable("directorId") Long directorId, Model model) {
        List<FilmDto> filmsByDirector = filmService.getFilmsByDirectorId(directorId);
        model.addAttribute("filmsByDirector", filmsByDirector);
        return "filmsByDirector";
    }





}
