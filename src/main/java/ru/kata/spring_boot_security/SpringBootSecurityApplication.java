package ru.kata.spring_boot_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kata.spring_boot_security.init.InitUser;

@SpringBootApplication
public class SpringBootSecurityApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
		InitUser.createUserAndAdmin();
	}
}
