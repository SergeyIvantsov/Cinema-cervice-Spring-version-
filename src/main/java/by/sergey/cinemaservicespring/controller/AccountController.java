package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.entity.Account;
import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.entity.User;
import by.sergey.cinemaservicespring.service.AccountService;
import by.sergey.cinemaservicespring.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final FilmService filmService;

    @GetMapping("/getAccount")
    public String showAccountDto(Model model) {
        try {
            model.addAttribute("showAccount", accountService.findAccountByUsername());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return "account";
    }


/*    @GetMapping("/getAccount")
    public String showAccount(Model model) {
        try {
            Optional<Account> account = accountService.findAccountByUsername();
            model.addAttribute("user", account.get().getUser());
            model.addAttribute("films", account.get().getDesiredFilms());
            model.addAttribute("watchedFilms", account.get().getWatchedFilms());
            return "account";
        } catch (NoSuchElementException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }*/

    @PostMapping("/addWishes")
    public String addToWishes(@RequestParam("idFilm") Long idFilm, RedirectAttributes redirectAttributes) {
        try {
            redirectAttributes.addFlashAttribute("messageDesired", "Фильм " + "\"" + accountService.addFilmToDesireList(idFilm) + "\"" + " добавлен в список желаемых!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorDesired", e.getMessage());
        }
        return "redirect:/getFilms";
    }

    @PostMapping("/addWatches")
    public String addToWatches(@RequestParam("idWatchedFilm") Long idWatchedFilm, RedirectAttributes redirectAttributes) {
        try {
            redirectAttributes.addFlashAttribute("messageDesired", "Фильм " + "\"" +accountService.addFilmToWatchedList(idWatchedFilm) + "\"" + " добавлен в список просмотренных!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorDesired", e.getMessage());
        }
        return "redirect:/getFilms";
    }

    @PostMapping("/delete_desired_film")
    public String deleteFromWishes(@RequestParam("idDesire") Long idDesire) {
        accountService.deleteFilmFromDesireList(idDesire);
        return "redirect:/getAccount";
    }

    @PostMapping("/delete_watched_film")
    public String deleteFromWatches(@RequestParam("idWatched") Long idWatched) {
        accountService.deleteFilmFromWatchedList(idWatched);
        return "redirect:/getAccount";
    }



}
