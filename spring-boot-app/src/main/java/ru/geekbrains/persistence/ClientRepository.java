package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.persistence.entity.Client;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c " +
            "from Client c " +
            "left join fetch c.productList p " +
            "where (c.id = :id)" +
            "order by p.id")
    Optional<Client> findByIdWithProducts(@Param("id") Long id);
}
