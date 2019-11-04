package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persistence.CategoryRepository;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.service.CategoryService;

@Controller
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allCategories(Model model) {
        model.addAttribute("categoriesList", categoryService.findAllWithoutProducts());
        return "categories";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createCategoryFrom(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("action", "create");
        return "category";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editForm(@RequestParam("id") Long id, Model model) {
        Category category = categoryService.findByIdWithProducts(id)
                .orElseThrow(() -> new IllegalStateException("Category not found"));
        model.addAttribute("category", category);
        model.addAttribute("action", "edit");
        return "category";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String createCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }
}
