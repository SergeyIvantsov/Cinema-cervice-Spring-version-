package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.dto.DirectorDto;
import by.sergey.cinemaservicespring.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        directorService.save(directorDto);
        return "redirect:/createFilm";
    }


}
