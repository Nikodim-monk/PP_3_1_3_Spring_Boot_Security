package ru.kata.spring_boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MineController {

    @GetMapping("/mL")
    public String printMineLoginPage() {
        return "mine_login";
    }

    @GetMapping("/hello")
    public String printIndex() {
        return "index";
    }
}