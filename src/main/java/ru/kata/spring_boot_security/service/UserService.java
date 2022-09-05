package ru.kata.spring_boot_security.service;

import ru.kata.spring_boot_security.entity.User;

import java.util.List;

public interface UserService {

    User getByEmail(String email);

    List<User> getAllUsers();

    void addNewUser(User user);

    User getUser(long id);

    void updateUser(User user);

    void userDelete(long id);

}
