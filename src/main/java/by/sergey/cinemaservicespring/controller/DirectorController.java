package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.dto.DirectorDto;
import by.sergey.cinemaservicespring.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;

    @GetMapping("/addDirectorForm")
    public String addDirectorForm(Model model) {
        model.addAttribute("directorDto", new DirectorDto());
        return "createDirector";
    }

    @PostMapping("/saveDirector")
    public String saveDirector(@ModelAttribute("directorDto") DirectorDto directorDto) {
        directorService.saveOrUpdate(directorDto);
        return "redirect:/createFilm";
    }

    @GetMapping("/getDirectors")
    public String showAllDirectors(Model model) {
        model.addAttribute("allDirectors", directorService.getAll());
        return "directors";
    }

    @GetMapping("/addUpdateForm")
    public String updateDirectorForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("directorUpdate", directorService.get(id));
        return "updateDirector";
    }

    @PostMapping("/updateDirector")
    public String updateDirector(@ModelAttribute("directorUpdate") DirectorDto directorDto) {
        directorService.saveOrUpdate(directorDto);
        return "redirect:/getDirectors";
    }

    @PostMapping("/deleteDirector")
    public String deleteDirector(@RequestParam("id") Long id) {
        directorService.delete(id);
        return "redirect:/getDirectors";
    }



}
