package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persistence.entity.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {

}
