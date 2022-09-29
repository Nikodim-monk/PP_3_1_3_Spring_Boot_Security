package ru.kata.spring_boot_security.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String printAllUsers(Principal principal, ModelMap model) {
        Map<User, String> user_role = new LinkedHashMap<>();
        List<User> users = service.getAllUsers();
        User admin = service.getByEmail("lr1975@yandex.ru");
//        User admin = service.getByEmail(principal.getName());
        for (User elem : users) {
            user_role.put(elem, UserServiceImpl.roleSting(elem));
        }
        model.addAttribute("admin", admin);
        model.addAttribute("role", UserServiceImpl.roleSting(admin));
        model.addAttribute("user_role", user_role);
        return "admin_panel";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user,
                                @RequestParam(value = "role", required = false) String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.addNewUser(user, role);
        return "redirect:/admin";
    }

    @PutMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "role", required = false) String role) {
        if (!user.getPassword().equals("")) user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.updateUser(user, role);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        service.userDelete(user.getId());
        return "redirect:/admin";
    }

}