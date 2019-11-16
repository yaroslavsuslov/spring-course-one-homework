package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.persistence.UserRepository;
import ru.geekbrains.persistence.entity.Role;
import ru.geekbrains.persistence.entity.User;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void create(UserRepr userRepr, Set<Role> roles) {
        User user = new User();
        user.setUsername(userRepr.getUsername());
        user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public List<User> getAllUsersWithRoles() {
        return userRepository.getAllUsersWithRoles();
    }



}
