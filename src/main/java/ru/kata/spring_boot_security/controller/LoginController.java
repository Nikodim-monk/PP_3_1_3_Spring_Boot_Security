package ru.kata.spring_boot_security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring_boot_security.init.InitUser;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final InitUser initUser;

    @GetMapping("/login")
    public String printMineLoginPage() {
        initUser.createDefaultUserInBase();
        initUser.createDefaultAdminInBase();
        return "mine_login";
    }
}
