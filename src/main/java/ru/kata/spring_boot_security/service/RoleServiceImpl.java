package ru.kata.spring_boot_security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring_boot_security.entity.Role;
import ru.kata.spring_boot_security.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository repository;

    @Override
    public Role getByRole(String role) {
        return repository.findByRole(role);
    }
    @Transactional
    public void addNewRole(Role role) {
        if(getByRole(role.getRole())==null) {
            repository.save(role);
        }

    }
}
