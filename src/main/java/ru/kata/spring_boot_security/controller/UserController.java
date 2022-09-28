package ru.kata.spring_boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String printUser(Principal principal, ModelMap model) {
        model.addAttribute("user", service.getByEmail(principal.getName()));
        return "user_panel";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User userFor(@PathVariable("id") long id) {
        return service.getUser(id);
    }
}
