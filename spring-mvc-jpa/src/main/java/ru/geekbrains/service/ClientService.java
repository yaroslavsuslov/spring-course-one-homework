package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persistence.ClientRepository;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.persistence.entity.Client;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Client> findByIdWithProducts(Long id) {
        return clientRepository.findByIdWithProducts(id);
    }

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional
    public void save(Client client) {
        clientRepository.save(client);
    }
}
