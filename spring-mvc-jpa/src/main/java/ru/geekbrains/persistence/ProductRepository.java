package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.persistence.entity.Product;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getAllByCategory_Id(Long categoryId);

    @Query("select b from Product b where b.price = (select max(a.price) from Product a)")
    List<Product> findProductByMaxPrice();

    @Query("select b from Product b where b.price = (select max(a.price) from Product a where a.category.id = :categoryId)")
    List<Product> findProductByMaxPrice(@Param("categoryId") Long categoryId);

    @Query("select b from Product b where b.price = (select min(a.price) from Product a)")
    List<Product> findProductByMinPrice();

    @Query("select b from Product b where b.price = (select min(a.price) from Product a where a.category.id = :categoryId)")
    List<Product> findProductByMinPrice(@Param("categoryId") Long categoryId);
}
