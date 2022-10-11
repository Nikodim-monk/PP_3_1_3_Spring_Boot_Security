package ru.kata.spring_boot_security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.service.RoleService;
import ru.kata.spring_boot_security.service.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping()
    public String printAllUsers(Principal principal, ModelMap model) {
        User admin = userService.getByEmail(principal.getName());
        model.addAttribute("admin", admin);
        model.addAttribute("role", roleService.getRoleToSting(admin));
        model.addAttribute("user_role", userService.createUserCollection(userService.getAllUsers()));
        return "admin_panel";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user,
                                @RequestParam(value = "role", required = false) String role) {
        userService.addNewUser(user, role);
        return "redirect:/admin";
    }

    @PutMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "role", required = false) String role) {
        userService.updateUser(user, role);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.deleteUser(user.getId());
        return "redirect:/admin";
    }

}