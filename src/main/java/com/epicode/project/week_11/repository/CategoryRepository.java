package com.epicode.project.week_11.repository;

import com.epicode.project.week_11.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);
    Category getCategoryByName(String name);
}
