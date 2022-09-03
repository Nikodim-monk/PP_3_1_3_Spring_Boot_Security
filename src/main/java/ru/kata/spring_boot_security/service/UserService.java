package ru.kata.spring_boot_security.service;

import ru.kata.spring_boot_security.entity.User;

import java.util.List;

public interface UserService {

    User findByUsername(String name);

    List<User> getAllUsers();

    void addNewUser(User user);

    User getUser(long id);

    void updateUser(User user);

    void userDelete(long id);

}
