package com.epicode.project.week_11.repository;

import com.epicode.project.week_11.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    boolean existsByNameAndSurname(String name, String surname);
    Author getAuthorByNameAndSurname(String name, String surname);

}
