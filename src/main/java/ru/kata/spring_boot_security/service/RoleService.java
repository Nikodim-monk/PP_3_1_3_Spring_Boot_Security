package ru.kata.spring_boot_security.service;

import ru.kata.spring_boot_security.entity.Role;
import ru.kata.spring_boot_security.entity.User;

import java.util.Set;

public interface RoleService {

    Role getByRole(String role);

    void addNewRole(Role role);

    Set<Role> getSetRoles(String role);

    String getRoleToSting(User elem);
}
