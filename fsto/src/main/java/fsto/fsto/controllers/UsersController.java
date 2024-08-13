package fsto.fsto.controllers;

import fsto.fsto.model.User;
import fsto.fsto.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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

    //Обработка перехода на usersAdd
    @GetMapping("/users/add")
    public String usersAdd(Model model) {
        return "usersAdd";
    }

    @PostMapping("/users/add")
    public String newUsersAdd(@RequestParam String name, @RequestParam String surname, @RequestParam String callSign,
                              @RequestParam int age,  @RequestParam int phoneNumber,
                              @RequestParam boolean equipment, @RequestParam boolean superUser, Model model) {
        User user = new User(name, surname, callSign, age, phoneNumber, equipment, superUser);
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String userDetails(@PathVariable(value = "id") Long id, Model model) {
        if (!userRepository.existsById(id)){
            return "redirect:/users";
        }
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "userDetails";
    }

    @GetMapping("/user/{id}/edit")
    public String userEdit(@PathVariable(value = "id") Long id, Model model) {
        if (!userRepository.existsById(id)){
            return "userDetails";
        }
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "userEdit";
    }




    @PostMapping("/user/{id}/edit")
    public String userUpdate(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String surname, @RequestParam String callSign,
                              @RequestParam int age,  @RequestParam int phoneNumber,
                              @RequestParam boolean equipment, @RequestParam boolean superUser,Model model) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(name);
        user.setSurname(surname);
        user.setCallSign(callSign);
        user.setAge(age);
        user.setPhoneNumber(phoneNumber);
        user.isEquipment();
        user.isSuperUser();
        userRepository.save(user);

        return "redirect:/users"; //TODO чтобы возвращал в карточку отредаченного юзера
                                  //TODO сделать сообщение об успешном обновлении
    }


    @PostMapping("/user/{id}/remove")
    public String userDelet(@PathVariable(value = "id") Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);

        return "redirect:/users"; //TODO сделать сообщение об успешном удалении
    }

}