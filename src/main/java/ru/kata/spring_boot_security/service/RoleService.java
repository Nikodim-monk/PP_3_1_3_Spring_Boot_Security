package ru.kata.spring_boot_security.service;

import ru.kata.spring_boot_security.entity.Role;

public interface RoleService {

    Role getByRole(String role);

    void addNewRole(Role role);
}
