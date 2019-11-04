package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persistence.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getAllByCategory_Id(Long categoryId);

//    @Query("select b from Product b where b.price = (select max(a.price) from Product a)")
//    List<Product> findProductByMaxPrice();
//
//    @Query("select b from Product b where b.price = (select max(a.price) from Product a where a.category.id = :categoryId)")
//    List<Product> findProductByMaxPrice(@Param("categoryId") Long categoryId);
//
//    @Query("select b from Product b where b.price = (select min(a.price) from Product a)")
//    List<Product> findProductByMinPrice();
//
//    @Query("select b from Product b where b.price = (select min(a.price) from Product a where a.category.id = :categoryId)")
//    List<Product> findProductByMinPrice(@Param("categoryId") Long categoryId);

    @Query("select new ru.geekbrains.controller.repr.ProductRepr(p.id, p.name, p.description, p.price, p.category.id, p.category.name) " +
            "from Product p " +
            "where p.id = :id")
    Optional<ProductRepr> getProductReprById(@Param("id") Long id);

    @Query("select c " +
            "from Product c " +
            "left join fetch c.clientList p " +
            "where (c.id = :id)" +
            "order by p.id")
    Optional<Product> getProductById(@Param("id") Long id);

    @Query("select new ru.geekbrains.controller.repr.ProductRepr(p.id, p.name, p.description, p.price, p.category.id, p.category.name) " +
            "from Product p " +
            "where (:categoryId = -1L or p.category.id = :categoryId) and " +
            "(:priceFrom is null or p.price >= :priceFrom) and " +
            "(:priceTo is null or p.price <= :priceTo)" +
            "order by p.id")
    List<ProductRepr> filterProducts(@Param("categoryId") Long categoryId,
                                     @Param("priceFrom") BigDecimal priceFrom,
                                     @Param("priceTo") BigDecimal priceTo);
}
