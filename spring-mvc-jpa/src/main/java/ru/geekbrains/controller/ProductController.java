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

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String products(@RequestParam(name = "categoryId", required = false) Long categoryId,
                           @RequestParam(name = "priceFilter", required = false) String price,
                           Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        String[] priceFilterValues = {"min", "max", "min and max"};
        model.addAttribute("priceFilterValues", priceFilterValues);
        model.addAttribute("products", filterByCategoryAndPrice(categoryId, price));

        return "products";
    }

    private List<Product> filterByCategoryAndPrice(Long categoryId, String priceType) {
        List<Product> products = new ArrayList<>();
        if (categoryId == null || categoryId == -1) {
            if (priceType == null || priceType.equals("empty")) {
                products = productRepository.findAll();
            } else {
                switch (priceType) {
                    case ("min"):
                        products = productRepository.findProductByMinPrice();
                        break;
                    case ("max"):
                        products = productRepository.findProductByMaxPrice();
                        break;
                    case ("min and max"):
                        products = productRepository.findProductByMinPrice();
                        products.addAll(productRepository.findProductByMaxPrice());  //TODO
                        break;
                }
            }
        } else {
            if (priceType == null || priceType.equals("empty")) {
                products = productRepository.getAllByCategory_Id(categoryId);
            } else {
                switch (priceType) {
                    case ("min"):
                        products = productRepository.findProductByMinPrice(categoryId);
                        break;
                    case ("max"):
                        products = productRepository.findProductByMaxPrice(categoryId);
                        break;
                    case ("min and max"):
                        products = productRepository.findProductByMinPrice(categoryId);
                        products.addAll(productRepository.findProductByMaxPrice(categoryId));  //TODO
                        break;
                }
            }
        }

        return products;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createProductFrom(@RequestParam("categoryId") Long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new IllegalStateException("Product not found"));
        Product product = new Product();
        product.setCategory(category);
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") Product product) {
        product.setCategory(categoryRepository.findById(product.getCategoryId()).
                orElseThrow(() -> new IllegalStateException("Product not found")));
        productRepository.save(product);
        return "redirect:/categories/edit?id=" + product.getCategory().getId();
    }


}
