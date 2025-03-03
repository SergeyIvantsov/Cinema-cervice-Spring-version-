package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @GetMapping("/getFilms")
    public String getFilms(Model model) {
    model.addAttribute("allFilms", filmService.getAll());
    return "Films";
    }


}
