package ru.geekbrains.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.exceptions.ResourceNotFoundException;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.service.CategoryService;

import java.util.List;

@RequestMapping("/api/category")
@RestController
public class CategoryRestController {

    private CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/all")
    public List<Category> getAllCategories() {
        return categoryService.findAllWithoutProducts();
    }

    @GetMapping(value = "/id/{id}")
    public Category getCategory(@PathVariable("id") Long id) {
        return categoryService.findByIdWithoutProducts(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCategory(@RequestBody Category category) {
        categoryService.saveByName(category);
    }

    @DeleteMapping(value = "/id/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }
}
