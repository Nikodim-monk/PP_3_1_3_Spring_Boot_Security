package ru.kata.spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.UserService;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public String printAllUsers(Principal principal, ModelMap model) {
        Map<User, String> user_role = new LinkedHashMap<>();
        List<User> users = service.getAllUsers();
        User admin = service.getByEmail(principal.getName());
        for (User elem : users) {
            user_role.put(elem, Vspom.roleSting(elem));
        }
        model.addAttribute("admin", admin);
        model.addAttribute("role", Vspom.roleSting(admin));
        model.addAttribute("user_role", user_role);
        return "admin_panel";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user,
                                @RequestParam(value = "role", required = false) String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Vspom.roles(role));
        service.addNewUser(user);
        return "redirect:/admin";
    }

    @PutMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "role", required = false) String role) {

        User userNotUpdate = service.getUser(user.getId());

        userNotUpdate.setFirstname(user.getFirstname());
        userNotUpdate.setLastname(user.getLastname());
        userNotUpdate.setAge(user.getAge());
        userNotUpdate.setEmail(user.getEmail());
        if (!user.getPassword().equals("")) {
            userNotUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (role != null) {
            userNotUpdate.setRoles(Vspom.roles(role));
        }

        service.updateUser(userNotUpdate);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        service.userDelete(user.getId());
        return "redirect:/admin";
    }
}