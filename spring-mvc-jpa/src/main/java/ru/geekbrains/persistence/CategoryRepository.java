package ru.geekbrains.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.persistence.entity.Category;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CategoryRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Category category) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(category);

        em.getTransaction().commit();
        em.close();
    }

    public void update(Category category) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();

        em.merge(category);

        em.getTransaction().commit();
        em.close();
    }

    public List<Category> findAll() {
        EntityManager em = sessionFactory.createEntityManager();

        List<Category> categories = em.createQuery("from Category", Category.class).getResultList();
        em.close();
        return categories;
    }

    public Category findById(Long id) {
        EntityManager em = sessionFactory.createEntityManager();

        Category category = em.find(Category.class, id);
        em.close();
        return category;
    }
}
