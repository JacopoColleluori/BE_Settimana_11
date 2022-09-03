package com.epicode.project.week_11.service;

import com.epicode.project.week_11.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
    Author create(Author book);
    Author update(Long id,Author book);
    void delete(Long id);
}
