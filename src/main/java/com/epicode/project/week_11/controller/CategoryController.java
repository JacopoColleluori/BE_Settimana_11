package com.epicode.project.week_11.controller;

import com.epicode.project.week_11.model.Book;
import com.epicode.project.week_11.model.Category;
import com.epicode.project.week_11.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {

    @Autowired
   private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllBooks() {
        return  ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    @PostMapping("create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<Category>(categoryService.create(category), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.update(id, category));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<String>("successfully deleted",HttpStatus.ACCEPTED);
    }
}
