package com.epicode.project.week_11.service;

import com.epicode.project.week_11.exception.CatalogueException;
import com.epicode.project.week_11.model.Author;
import com.epicode.project.week_11.model.Book;
import com.epicode.project.week_11.model.Category;
import com.epicode.project.week_11.repository.AuthorRepository;
import com.epicode.project.week_11.repository.BookRepository;
import com.epicode.project.week_11.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new CatalogueException("Book not found");
        }
        return book.get();
    }

    @Override
    public Book create(Book book) {
        List<Author> authors = new ArrayList<>();
        List<Category> categories = new ArrayList<>();

        //Check if author and category does actually exist

        for (Author author : book.getAuthors()) {
            //check authors
            if (!authorRepository.existsByNameAndSurname(author.getName(), author.getSurname())) {

                throw new CatalogueException("Author/Authors doesn't/don't exists in Database");
            } else {
                authors.add(authorRepository.getAuthorByNameAndSurname(author.getName(), author.getSurname()));
            }
        }
        for (Category category : book.getCategories()) {
            //check categories
            if (!categoryRepository.existsByName(category.getName())) {
                throw new CatalogueException("Category/Categories doesn't/don't exists in Database");
            } else {
                categories.add(categoryRepository.getCategoryByName(category.getName()));
            }
        }
        //let's referencing the authors and categories inside the book
        book.setCategories(categories);
        book.setAuthors(authors);

        //let's create the book
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book book) {
        if (!bookRepository.existsById(id)) {
            throw new CatalogueException("Book not found ");
        }
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new CatalogueException("book not found" );
        }
        bookRepository.deleteById(id);
    }

    @Override
    public Set<Book> getBooksByAuthor(Set<Author>authors) {
         Set<Author> authorsList = new HashSet<>();
        for(Author author : authors)   {
         if(!authorRepository.existsByNameAndSurname(author.getName(), author.getSurname())){
             throw new CatalogueException("Author/Authors doesn't/don't exists in Database");
         }
         else{
             authorsList.add(authorRepository.getAuthorByNameAndSurname(author.getName(), author.getSurname()));
            }
        }
        return bookRepository.findAllByAuthorsIn(authorsList);
    }

    @Override
    public Set<Book> getBooksByCategory(Set<Category>categories) {
        Set<Category> categoriesList = new HashSet<>();
        for(Category category : categories)   {
            if(!categoryRepository.existsByName(category.getName())){
                throw new CatalogueException("Category/Categories doesn't/don't exists in Database");
            }
            else{
                categoriesList.add(categoryRepository.getCategoryByName(category.getName()));
            }
        }
        return bookRepository.findAllByCategoriesIn(categoriesList);
    }
}
