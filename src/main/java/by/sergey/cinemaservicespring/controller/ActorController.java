package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.dto.ActorDto;
import by.sergey.cinemaservicespring.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @GetMapping("/addActorForm")
    public String addDirectorForm(Model model) {
        model.addAttribute("actorDto", new ActorDto());
        return "createActor";
    }

    @PostMapping("/saveActor")
    public String saveDirector(@ModelAttribute("actorDto") ActorDto actorDto) {
        actorService.saveOrUpdate(actorDto);
        return "redirect:/createFilm";
    }


}
