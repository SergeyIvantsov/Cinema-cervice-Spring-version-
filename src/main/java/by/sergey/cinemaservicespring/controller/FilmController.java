package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.dto.FilmDto;
import by.sergey.cinemaservicespring.dto.FilmFilterDto;
import by.sergey.cinemaservicespring.service.DirectorService;
import by.sergey.cinemaservicespring.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final DirectorService directorService;


    @GetMapping("/getFilms")
    public String getFilterFilms(@ModelAttribute("filmsFilter") FilmFilterDto filmFilterDto,
                                 @RequestParam(defaultValue = "0") int page,  // текущая страница
                                 @RequestParam(defaultValue = "10") int size, // количество записей на странице
                                 Model model) {
        Pageable pageable = PageRequest.of(page, size);


        List<FilmDto> filteredFilms = filmService.filtersFilms(filmFilterDto.getTitle(),
                filmFilterDto.getYear(), filmFilterDto.getGenre(), pageable);


        long totalFilms = filmService.getTotalFilmCount(filmFilterDto.getTitle(),
                filmFilterDto.getYear(), filmFilterDto.getGenre());
        int totalPages = (int) Math.ceil((double) totalFilms / size);

        model.addAttribute("allFilms", filteredFilms);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
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
    public String showUpdateForm(@RequestParam("id") Long id, Model model) {
        FilmDto filmDto = filmService.get(id);
        model.addAttribute("filmDto", filmDto);
        model.addAttribute("allDirectors", directorService.getAll());
        return "updateFilm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("filmDto") FilmDto filmDto) {
        filmService.saveOrUpdate(filmDto);
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
