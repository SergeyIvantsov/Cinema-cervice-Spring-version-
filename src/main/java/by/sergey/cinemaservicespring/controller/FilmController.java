package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.dto.FilmDto;
import by.sergey.cinemaservicespring.service.DirectorService;
import by.sergey.cinemaservicespring.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/createFilm")
    public String createFilm(Model model) {
        model.addAttribute("addFilm", new FilmDto());
        model.addAttribute("allDirectors", directorService.getAll());
        return "createFilm";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("addFilm") FilmDto filmDto) {
        filmService.saveOrUpdate(filmDto);
        return "redirect:/getFilms";
    }


    @GetMapping("/updateFilm")
    public String update(@RequestParam("id") Long id, Model model) {
        FilmDto filmDto = filmService.get(id);
        model.addAttribute("filmDto", filmDto);
        model.addAttribute("allDirectors", directorService.getAll());
        return "updateFilm"; // Возвращаем название страницы с обновленным фильмом
    }

//    @GetMapping("/updateFilm")
//    public String update(Model model) {
//        Long id =(Long) model.getAttribute("id");
//        FilmDto filmDto = filmService.get(id);
//        model.addAttribute("filmDto", filmDto);
//        return "redirect:/update";
//    }

    @PostMapping("/update")
    public String update(@ModelAttribute("filmDto") FilmDto filmDto) {
        filmService.saveOrUpdate(filmDto);
        return "redirect:/getFilms";
    }




}
