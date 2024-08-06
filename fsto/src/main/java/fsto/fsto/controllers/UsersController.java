package fsto.fsto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

    //Обработка перехода на home
    @GetMapping("/users")
    public String usersMain(Model model) {
        return "users";
    }
}