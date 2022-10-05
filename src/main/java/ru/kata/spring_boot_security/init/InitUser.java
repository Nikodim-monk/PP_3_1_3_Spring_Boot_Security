package ru.kata.spring_boot_security.init;

import ru.kata.spring_boot_security.entity.User;
public class InitUser {

    public static User createDefaultUserInBase() {
        return new User("User","Userov",100,"user@mail.ru","user");

    }
    public static User createDefaultAdminInBase() {
        return new User("Admin","Adminov",100,"admin@mail.ru","admin");
    }
}

