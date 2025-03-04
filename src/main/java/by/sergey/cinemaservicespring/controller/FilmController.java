package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.dto.DirectorDto;
import by.sergey.cinemaservicespring.dto.FilmDto;
import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.service.DirectorService;
import by.sergey.cinemaservicespring.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final DirectorService directorService;

    @GetMapping("/getFilms")
    public String getFilms(Model model) {
        model.addAttribute("allFilms", filmService.getAll());
        return "films";
    }

    @GetMapping("/saveFilm")
    public String toSaveFilm(Model model) {
        model.addAttribute("addFilm", new FilmDto());
        model.addAttribute("allDirectors", directorService.getAll());
        return "saveFilm";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("addFilm") FilmDto filmDto) {
        filmService.saveOrUpdate(filmDto);
        return "redirect:/getFilms";

    }
}
