package ru.kata.spring_boot_security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring_boot_security.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {

    User getByEmail(String email);

    List<User> getAllUsers();

    void addNewUser(User user, String role);

    User getUser(long id);

    void updateUser(User user, String role);

    void deleteUser(long id);

    Map<User, String> createUserCollection(List<User> users);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
