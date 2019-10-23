package ru.geekbrains.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.persistence.entity.Client;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ClientRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ClientRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Client client) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(client);

        em.getTransaction().commit();
        em.close();
    }

    public void update(Client client) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();

        em.merge(client);

        em.getTransaction().commit();
        em.close();
    }

    public List<Client> findAll() {
        EntityManager em = sessionFactory.createEntityManager();

        List<Client> clients = em.createQuery("from Client", Client.class).getResultList();
        em.close();
        return clients;
    }

    public Client findById(Long id) {
        EntityManager em = sessionFactory.createEntityManager();

        Client client = em.find(Client.class, id);
        em.close();
        return client;
    }
}
