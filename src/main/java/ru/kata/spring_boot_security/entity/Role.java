package ru.kata.spring_boot_security.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "role")
    private String role;

    public Role(long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {

    }
}

