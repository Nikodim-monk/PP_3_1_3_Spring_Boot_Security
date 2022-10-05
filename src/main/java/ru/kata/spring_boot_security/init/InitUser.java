package ru.kata.spring_boot_security.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.UserService;

@Component
@RequiredArgsConstructor
public class InitUser {
    private final UserService service;

    public void createDefaultUserInBase() {
        User user = new User("User", "Userov", 100, "user@mail.ru", "user");
        service.addNewUser(user, "USER");
    }

    public void createDefaultAdminInBase() {
        User admin = new User("Admin", "Adminov", 100, "admin@mail.ru", "admin");
        service.addNewUser(admin, "ADMIN");
    }
}

