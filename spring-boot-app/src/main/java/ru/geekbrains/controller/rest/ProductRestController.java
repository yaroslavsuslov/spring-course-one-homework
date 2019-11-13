package ru.geekbrains.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.persistence.entity.Product;
import ru.geekbrains.service.ProductService;

import java.util.List;

@RequestMapping("/api/product")
@RestController
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping(value = "/id/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.productReprToProduct(productService.getProductReprById(id));
    }

    @GetMapping(value = "/category/id/{id}")
    public List<Product> getAllProductsByCategoryId(@PathVariable long id) {
        return productService.getAllByCategory_Id(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody Product product) {
        productService.saveByName(product);
    }

    @DeleteMapping(value = "/id/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.delete(id);
    }

}
