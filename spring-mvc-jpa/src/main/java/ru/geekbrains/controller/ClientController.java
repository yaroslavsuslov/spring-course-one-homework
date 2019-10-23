package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persistence.ClientRepository;
import ru.geekbrains.persistence.ProductRepository;
import ru.geekbrains.persistence.entity.Client;
import ru.geekbrains.persistence.entity.Product;

@Controller
@RequestMapping("clients")
public class ClientController {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allClients(Model model) {
        model.addAttribute("clientsList", clientRepository.findAll());
        return "clients";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createClientForm(Model model) {
        model.addAttribute("category", new Client());
        model.addAttribute("action", "create");
        return "client";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("client", clientRepository.findById(id));
        model.addAttribute("action", "edit");
        model.addAttribute("allProducts", productRepository.findAll());
        return "client";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editForm(@ModelAttribute("client") Client client) {
        clientRepository.update(client);
        return "client";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createClient(@ModelAttribute("client") Client client) {
        clientRepository.create(client);
        return "redirect:/clients";
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public String addProductToClient(@ModelAttribute("client") Client client, @ModelAttribute("Product") Product product) {
           client.getProductList().add(product);
           return "client";
    }

    @RequestMapping(value = "goods", method = RequestMethod.GET)
    public String showClientsProducts(@RequestParam("id") Long id, Model model) {
        model.addAttribute("clientProducts", clientRepository.findById(id));
        return "goods";
    }
}
