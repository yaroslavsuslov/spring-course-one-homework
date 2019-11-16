package ru.geekbrains.persistence;

import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.persistence.entity.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> getRoleByName(String name);
}
