package ru.kata.spring_boot_security.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kata.spring_boot_security.entity.Role;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.RoleService;
import ru.kata.spring_boot_security.service.UserService;

@Component
@RequiredArgsConstructor
public class InitUser {
    private final UserService service;
    private final RoleService roleService;

    public void createDefaultUsersAndRolesInBase() {
        roleService.addNewRole(new Role("ROLE_USER"));
        roleService.addNewRole(new Role("ROLE_ADMIN"));
        service.addNewUser(new User(
                "user", "user", 100, "user@mail.ru", "user"), "USER");
        service.addNewUser(new User(
                "admin", "admin", 100, "admin@mail.ru", "admin"), "ADMIN");
    }
}

