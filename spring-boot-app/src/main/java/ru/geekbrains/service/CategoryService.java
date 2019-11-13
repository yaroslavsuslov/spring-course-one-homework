package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persistence.CategoryRepository;
import ru.geekbrains.persistence.entity.Category;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> findAllWithoutProducts() {
        return categoryRepository.findAllWithoutProducts();
    }

    @Transactional(readOnly = true)
    public Optional<Category> findByIdWithProducts(Long id) {
        return categoryRepository.findByIdWithProducts(id);
    }

    @Transactional(readOnly = true)
    public Optional<Category> findByIdWithoutProducts(Long id) {
        return categoryRepository.findByIdWithoutProducts(id);
    }

    @Transactional
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public void saveByName(Category category) {
        Long id = categoryRepository.findIdByName(category.getName()).
                orElse(null);
        if (id == null) {
            save(category);
        } else {
            category.setId(id);
            save(category);
        }
    }

    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
