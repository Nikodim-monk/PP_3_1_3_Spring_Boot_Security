package ru.kata.spring_boot_security.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring_boot_security.entity.Role;
import ru.kata.spring_boot_security.entity.User;
import ru.kata.spring_boot_security.init.InitUser;
import ru.kata.spring_boot_security.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository, @Lazy PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(propagation = Propagation.NEVER)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Transactional
    public void addNewUser(User user, String role) {
        if (getByEmail(user.getEmail()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(roles(role));
            repository.save(user);
        }
    }

    @Transactional(readOnly = true)
    public User getUser(long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void updateUser(User user, String role) {
            User userNotUpdate = getUser(user.getId());
            userNotUpdate.setFirstName(user.getFirstName());
            userNotUpdate.setLastName(user.getLastName());
            userNotUpdate.setAge(user.getAge());
            userNotUpdate.setEmail(user.getEmail());
            if (!user.getPassword().equals("")) {
                userNotUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (role != null) {
                userNotUpdate.setRoles(roles(role));
            }
            repository.saveAndFlush(userNotUpdate);
        }

    @Transactional
    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    public static Set<Role> roles(String role) {
        Set<Role> roles = new HashSet<>();
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

    public static String roleSting(User elem) {
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

    public static Map<User, String> cteateCollection(List<User> users) {
        Map<User, String> user_role = new LinkedHashMap<>();
        for (User elem : users) {
            user_role.put(elem, roleSting(elem));
        }
        return user_role;
    }

}
