package fsto.fsto.controllers;

import fsto.fsto.model.User;
import fsto.fsto.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    //Обработка перехода на users
    @GetMapping("/users")
    public String usersMain(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }
}