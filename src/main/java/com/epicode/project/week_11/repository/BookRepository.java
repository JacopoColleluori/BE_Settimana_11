package com.epicode.project.week_11.repository;

import com.epicode.project.week_11.model.Author;
import com.epicode.project.week_11.model.Book;
import com.epicode.project.week_11.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Set<Book> findAllByCategoriesIn(Set<Category>category);
    Set<Book> findAllByAuthorsIn(Set <Author>authors );

}
