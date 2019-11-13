package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.persistence.entity.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select new Category(c.id, c.name, c.description) from Category c")
    List<Category> findAllWithoutProducts();

    @Query("select distinct c " +
            "from Category c " +
            "left join fetch c.products p " +
            "where c.id = :id")
    Optional<Category> findByIdWithProducts(@Param("id") Long id);

    @Query("select c.id from Category c where c.name = :name")
    Optional<Long> findIdByName(@Param("name") String name);

    @Query("select new Category(c.id, c.name, c.description) from Category c where c.id = :id")
    Optional<Category> findByIdWithoutProducts(@Param("id") Long id);
}
