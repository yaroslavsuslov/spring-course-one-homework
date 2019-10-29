package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persistence.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

}
