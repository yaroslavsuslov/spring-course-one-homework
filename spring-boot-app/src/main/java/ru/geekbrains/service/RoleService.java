package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.controller.exceptions.ResourceNotFoundException;
import ru.geekbrains.persistence.RoleRepository;
import ru.geekbrains.persistence.entity.Role;

@Service
@Transactional
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name).
                orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
