package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.dto.FilmDto;
import by.sergey.cinemaservicespring.dto.FilmFilterDto;
import by.sergey.cinemaservicespring.dto.WrapperFilmDto;
import by.sergey.cinemaservicespring.service.AccountService;
import by.sergey.cinemaservicespring.service.ActorService;
import by.sergey.cinemaservicespring.service.DirectorService;
import by.sergey.cinemaservicespring.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final AccountService accountService;
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
        filmService.saveOrUpdateFilmWithActors(wrapperFilmDto);
        return "redirect:/getFilms";
    }

    @GetMapping("/updateFilm")
    public String showUpdateForm(@RequestParam("id") Long id, Model model) {
        WrapperFilmDto filmForUpdate = filmService.getFilmForUpdate(id);
        model.addAttribute("wrapperFilmDto", filmForUpdate);
        return "updateFilm";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute("wrapperFilmDto") WrapperFilmDto wrapperFilmDto) {
        filmService.saveOrUpdateFilmWithActors(wrapperFilmDto);
        return "redirect:/getFilms";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        filmService.delete(id);
        return "redirect:/getFilms";
    }

    @GetMapping("/getFilmsByDirectorId/{directorId}")
    public String getFilmsByDirector(@PathVariable("directorId") Long directorId, Model model) {
        model.addAttribute("filmsByDirector", filmService.getFilmsByDirectorId(directorId));
        return "filmsByDirector";
    }





}
