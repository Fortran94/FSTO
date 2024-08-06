package fsto.fsto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //Обработка перехода на home
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    //Обработка перехода на users
    @GetMapping("/games")
    public String games(Model model) {
        model.addAttribute("title", "Игры");
        return "games";
    }









}