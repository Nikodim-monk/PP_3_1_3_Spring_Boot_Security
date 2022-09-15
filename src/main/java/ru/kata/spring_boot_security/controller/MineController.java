package ru.kata.spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.UserService;

@Controller
@RequestMapping("/mine")
public class MineController {
    @Autowired
    private UserService service;

    @GetMapping("/mL")
    public String printMineLoginPage() {
        return "mine_login";
    }

    @GetMapping("/userFor")
    @ResponseBody
    public User userFor(long id) {
        return service.getUser(id);
    }
    @PostMapping("/fff")
    public String deleteUser(@ModelAttribute("user") User user,@RequestParam(value = "role", required = false) String role) {
//        service.userDelete(id);
        return "index";
    }
}