package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.entity.Account;
import by.sergey.cinemaservicespring.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/getAccount")
    public String showAccount(Model model) {
        Account account = accountService.getAccountById(1l);
        model.addAttribute("user", account.getUser());
        model.addAttribute("films", account.getDesiredFilms());
        model.addAttribute("watchedFilms", account.getWatchedFilms());
        return "account";
    }

    @PostMapping("/addWishes")
    public String addToWishes(@RequestParam("idFilm") Long idFilm) {
    accountService.addFilmToDesireList(1l, idFilm);
    return "redirect:/getFilms";
    }

    @PostMapping("/addWatches")
    public String addToWatches(@RequestParam("idWatchedFilm") Long idWatchedFilm) {
        accountService.addFilmToWatchedList(1l, idWatchedFilm);
        return "redirect:/getAccount";
    }

    @PostMapping("/delete_desired_film")
    public String deleteFromWishes(@RequestParam("idDesire") Long idDesire) {
        accountService.deleteFilmFromDesireList(1l, idDesire);
        return "redirect:/getAccount";
    }

    @PostMapping("/delete_watched_film")
    public String deleteFromWatches(@RequestParam("idWatched") Long idWatched) {
        accountService.deleteFilmFromWatchedList(1l, idWatched);
        return "redirect:/getAccount";
    }



}
