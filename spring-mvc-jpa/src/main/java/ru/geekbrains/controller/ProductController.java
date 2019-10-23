package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persistence.CategoryRepository;
import ru.geekbrains.persistence.ProductRepository;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.persistence.entity.Product;

@Controller
@RequestMapping("products")
public class ProductController {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createProductFrom(@RequestParam("categoryId") Long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId);
        Product product = new Product();
        product.setCategory(category);
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") Product product) {
        product.setCategory(categoryRepository.findById(product.getCategoryId()));
        productRepository.create(product);
        return "redirect:/categories/edit?id=" + product.getCategory().getId();
    }
}
