package ru.kata.spring_boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MineController {
    @GetMapping("/mL")
    public String print() {
        return "mine_login";
    }
    @PostMapping("/aaa")
    public String login(Principal principal) {
        String aaa=principal.getName();
        return "mine";
    }
}