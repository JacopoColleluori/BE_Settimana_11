package com.epicode.project.week_11.service;

import com.epicode.project.week_11.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
   Category create(Category category);
    Category update(Long id,Category book);
    void delete(Long id);
}
