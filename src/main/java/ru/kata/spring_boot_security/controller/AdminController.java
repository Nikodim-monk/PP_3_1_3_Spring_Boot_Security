package ru.kata.spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring_boot_security.entity.Role;
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
            user_role.put(elem, roleSting(elem));
        }
        model.addAttribute("admin", admin);
        model.addAttribute("role", roleSting(admin));
        model.addAttribute("user_role", user_role);
        return "admin_panel";
    }

    @GetMapping("/{id}")
    public String printUser(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", service.getUser(id));
        return "user";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user,
                                @RequestParam(value = "role", required = false) String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles(role));
        service.addNewUser(user);
        return "redirect:/admin";
    }

    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        User userNotUpdate = service.getUser(id);
        userNotUpdate.setFirstname(user.getFirstname());
        userNotUpdate.setLastname(user.getLastname());
        userNotUpdate.setAge(user.getAge());
        userNotUpdate.setEmail(user.getEmail());
        service.updateUser(userNotUpdate);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        service.userDelete(id);
        return "redirect:/admin";
    }

    private String roleSting(User elem) {
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

    private Collection<Role> roles(String role){
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