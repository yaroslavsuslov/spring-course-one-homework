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
import ru.geekbrains.service.ClientService;
import ru.geekbrains.service.ProductService;

import java.awt.color.ProfileDataException;
import java.util.Map;
import java.util.logging.LoggingMXBean;

@Controller
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;
    private final ProductService productService;

    @Autowired
    public ClientController(ClientService clientService, ProductService productService) {
        this.clientService = clientService;
        this.productService = productService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allClients(Model model) {
        model.addAttribute("clientsList", clientService.findAll());
        return "clients";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createClientForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("action", "create");
        return "client";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("client", clientService.findByIdWithProducts(id).orElseThrow(() ->
                new IllegalStateException("Client not found")));
        model.addAttribute("action", "edit");
        model.addAttribute("allProducts", productService.findAll());
        return "client";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String editForm(@ModelAttribute("client") Client clientForm, @RequestParam(name = "productId", required = false) Long id) {
        System.out.println("Parameter ID = " + id + " || " + "Client ID: " + clientForm.getId() + " || " + "clientName: " + clientForm.getName() + " || " + "client: " + clientForm);
        Client client = clientService.findByIdWithProducts(clientForm.getId()).orElseThrow(() ->
                new IllegalStateException("Product not found"));
        if (id != null && id != -1) {
            client.addProduct(productService.productReprToProduct(productService.getProductReprById(id).orElseThrow(() ->
                    new IllegalStateException("Product not found"))));
        }
        clientService.save(client);
        return "redirect:/clients";
    }


//    @RequestMapping(value = "editProduct", method = RequestMethod.POST)
//    public String addProductToClient(@RequestParam("clientid") Long clientId, @RequestParam("dropOperator") Long id) {
//        Product product = productRepository.findById(id).
//                orElseThrow(() -> new IllegalStateException("Product not found"));
//        Client client = clientRepository.findById(clientId).
//                orElseThrow(() -> new IllegalStateException("Client not found"));
//        client.addProduct(product);
//        clientRepository.save(client);
//        return "redirect:/clients";
//    }


//    @RequestMapping(value = "goods", method = RequestMethod.GET)
//    public String showClientsProducts(@RequestParam("id") Long id, Model model) {
//        model.addAttribute("client", clientService.findByIdWithProducts(id));
//        return "goods";
//    }
}
