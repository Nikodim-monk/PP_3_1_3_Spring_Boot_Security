package ru.kata.spring_boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.UserService;
import ru.kata.spring_boot_security.service.UserServiceImpl;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService service;

    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String printAllUsers(Principal principal, ModelMap model) {

        User admin = service.getByEmail(principal.getName());
        model.addAttribute("admin", admin);
        model.addAttribute("role", UserServiceImpl.roleSting(admin));
        model.addAttribute("user_role", UserServiceImpl.cteateCollection(service.getAllUsers()));
        return "admin_panel";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user,
                                @RequestParam(value = "role", required = false) String role) {
        service.addNewUser(user, role);
        return "redirect:/admin";
    }

    @PutMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "role", required = false) String role) {
        service.updateUser(user, role);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        service.userDelete(user.getId());
        return "redirect:/admin";
    }

}