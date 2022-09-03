package com.epicode.project.week_11.service;

import com.epicode.project.week_11.model.Author;
import com.epicode.project.week_11.model.Book;
import com.epicode.project.week_11.model.Category;

import java.util.List;
import java.util.Set;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book create(Book book);
    Book update(Long id,Book book);
    void delete(Long id);
    Set<Book> getBooksByAuthor(Set<Author> authors);

    Set<Book> getBooksByCategory(Set<Category> categories);
}
