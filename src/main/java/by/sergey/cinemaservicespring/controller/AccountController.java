package by.sergey.cinemaservicespring.controller;

import by.sergey.cinemaservicespring.dto.UserDto;
import by.sergey.cinemaservicespring.entity.Account;
import by.sergey.cinemaservicespring.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return "account";
    }

    @PostMapping("/addWishes")
    public String addWishes(@RequestParam("idFilm") Long idFilm) {
    accountService.addFilmToDesireList(1l, idFilm);
    return "redirect:/getAccount";
    }





}
