package ru.geekbrains.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.persistence.entity.Product;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ProductRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Product product) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(product);

        em.getTransaction().commit();
        em.close();
    }

    public void update(Product product) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();

        em.merge(product);

        em.getTransaction().commit();
        em.close();
    }

    public List<Product> findAll() {
        EntityManager em = sessionFactory.createEntityManager();

        List<Product> product = em.createQuery("from Product", Product.class).getResultList();
        em.close();
        return product;
    }
}
