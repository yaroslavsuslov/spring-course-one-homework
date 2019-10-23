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

@Controller
@RequestMapping("categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
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
        model.addAttribute("category", categoryRepository.findById(id));
        model.addAttribute("action", "edit");
        return "category";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editForm(@ModelAttribute("category") Category category) {
        categoryRepository.update(category);
        return "category";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createCategory(@ModelAttribute("category") Category category) {
        categoryRepository.create(category);
        return "redirect:/categories";
    }
}
