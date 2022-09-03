package com.epicode.project.week_11.service;

import com.epicode.project.week_11.exception.CatalogueException;
import com.epicode.project.week_11.model.Author;
import com.epicode.project.week_11.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
   private  AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        Optional<Author> author= authorRepository.findById(id);
        if(author.isEmpty()){
            throw new CatalogueException("author not found");
        }
        return author.get();
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Long id, Author author) {
        if(!authorRepository.existsById(id)){
            throw new CatalogueException("author not found");
        }
       author.setId(id);
        return authorRepository.save(author);
    }

    @Override
    public void delete(Long id) {
        if(!authorRepository.existsById(id)){
            throw new CatalogueException("author not found");
        }
        authorRepository.deleteById(id);
    }

}
