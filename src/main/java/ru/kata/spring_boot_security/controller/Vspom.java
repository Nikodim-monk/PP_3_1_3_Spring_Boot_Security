package ru.kata.spring_boot_security.controller;

import ru.kata.spring_boot_security.entity.Role;
import ru.kata.spring_boot_security.entity.User;

import java.util.ArrayList;
import java.util.Collection;

public class Vspom {
    public static String roleSting(User elem) {
        String rs = elem.getRoles().toString();
        if (rs.contains("ROLE_ADMIN") && rs.contains("ROLE_USER")) {
            return "ADMIN USER";
        } else if (rs.contains("ROLE_ADMIN")) {
            return "ADMIN";
        } else if (rs.contains("ROLE_USER")) {
            return "USER";
        } else {
            return "";
        }
    }

    public static Collection<Role> roles(String role){
        Collection<Role> roles = new ArrayList<>();
        switch (role) {
            case "USER" -> roles.add(new Role(1, "ROLE_USER"));
            case "ADMIN" -> roles.add(new Role(2, "ROLE_ADMIN"));
            case "ADMIN USER" -> {
                roles.add(new Role(1, "ROLE_USER"));
                roles.add(new Role(2, "ROLE_ADMIN"));
            }
        }
        return roles;
    }
}
