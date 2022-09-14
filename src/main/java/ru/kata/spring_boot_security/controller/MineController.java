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

    @GetMapping("/{id}")
    @ResponseBody
    public String aaa(@PathVariable("id") long id) {
        User user = service.getUser(id);
        return "index";
    }
    @PostMapping("/fff")
    public String fff() {
        return "index";
    }
}