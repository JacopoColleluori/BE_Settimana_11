package com.epicode.project.week_11.service;

import com.epicode.project.week_11.exception.CatalogueException;
import com.epicode.project.week_11.model.Category;
import com.epicode.project.week_11.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
   private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new CatalogueException("category not found");
        }
        return category.get();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        if(!categoryRepository.existsById(id)){
            throw new CatalogueException("category not found");
        }
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        if(!categoryRepository.existsById(id)){
            throw new CatalogueException("category not found");
        }
        categoryRepository.deleteById(id);
    }

}
