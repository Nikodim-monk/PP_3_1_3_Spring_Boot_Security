package ru.kata.spring_boot_security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring_boot_security.init.InitUser;
import ru.kata.spring_boot_security.service.UserService;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService service;

    @GetMapping("/login")
    public String printMineLoginPage() {
        service.addNewUser(InitUser.createDefaultUserInBase(),"USER");
        service.addNewUser(InitUser.createDefaultAdminInBase(),"ADMIN");
        return "mine_login";
    }
}
