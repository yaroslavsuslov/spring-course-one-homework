package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persistence.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> getUserByUsername(String username);

    @Query("select distinct u " +
            "from User u " +
            "left join fetch u.roles r " +
            "order by u.id")
    List<User> getAllUsersWithRoles();
}
