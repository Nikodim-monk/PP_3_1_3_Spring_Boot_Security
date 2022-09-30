package ru.kata.spring_boot_security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring_boot_security.service.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @GetMapping()
    public String printUser(Principal principal, ModelMap model) {
        model.addAttribute("user", service.getByEmail(principal.getName()));
        return "user_panel";
    }
}
