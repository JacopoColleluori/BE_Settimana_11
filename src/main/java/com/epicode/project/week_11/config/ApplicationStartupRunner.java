package com.epicode.project.week_11.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.epicode.project.week_11.model.Author;
import com.epicode.project.week_11.model.Book;
import com.epicode.project.week_11.model.Category;
import com.epicode.project.week_11.repository.*;
import com.epicode.project.week_11.security.model.Role;
import com.epicode.project.week_11.security.model.Roles;
import com.epicode.project.week_11.security.model.User;
import com.epicode.project.week_11.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        initAuthor();
        initCategory();
        initBook();


        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

        Role role = new Role();
        Role role2 = new Role();

        role2.setRoleName(Roles.ROLE_USER);
        role.setRoleName(Roles.ROLE_ADMIN);

        User user = new User();
        User user2 = new User();
        Set<Role> rolesAdmin = new HashSet<>();
        Set<Role> rolesUser = new HashSet<>();
        rolesAdmin.add(role);
        rolesUser.add(role2);

        user2.setUserName("user");
        user2.setPassword(bCrypt.encode("user"));
        user2.setEmail("user@gmail.com");
        user2.setRoles(rolesUser);
        user2.setActive(true);

        user.setUserName("admin");
        user.setPassword(bCrypt.encode("admin"));
        user.setEmail("admin@domain.com");
        user.setRoles(rolesAdmin);
        user.setActive(true);


        roleRepository.save(role2);
        roleRepository.save(role);

        userRepository.save(user);
        userRepository.save(user2);

    }

    private Category initCategory() {
        Category category = new Category();
        category.setName("Horror");
        Category category1 = new Category();
        category1.setName("Romance");
        categoryRepository.save(category);
        categoryRepository.save(category1);
        log.info("Category saved: {}", category.getName());
        return category;
    }

    private Author initAuthor() {
        Author author = new Author();
        author.setName("Mario");
        author.setSurname("Palladino");
        Author author1 = new Author();
        author1.setName("Nicola");
        author1.setSurname("Redente");
        authorRepository.save(author);
        authorRepository.save(author1);
        log.info("Author  saved: {}", author.getName() + author.getSurname());
        return author;
    }

    private Book initBook() {

        List<Category> categories = new ArrayList<>();
        List<Author> authors = new ArrayList<>();


        Author author1 = new Author();
        Author author2 = new Author();

        author1.setSurname("Palladino");
        author1.setName("Mario");

        author2.setSurname("Redente");
        author2.setName("Nicola");

        authors.add(author1);
        authors.add(author2);

        Category category1 = new Category();
        Category category2 = new Category();

        category1.setName("Horror");
        category2.setName("Romance");

        categories.add(category2);
        categories.add(category1);

        Book book2 = new Book();
        book2.setAuthors(authors);
        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setPublicationYear(2022);
        book.setPrice(9.50);
        book.setAuthors(authors);
        book.setCategories(categories);
        bookService.create(book);
        log.info("Book saved: {}", book.getTitle());
        return book;
    }

}
