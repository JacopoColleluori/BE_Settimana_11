package com.epicode.project.week_11.controller;

import com.epicode.project.week_11.model.Author;
import com.epicode.project.week_11.model.Book;
import com.epicode.project.week_11.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@Tag(name="Author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;


    @GetMapping
    @Operation(summary="all authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return  ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("{id}")
    @Operation(summary="get author by id")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }
    @PostMapping("create")
    @Operation(summary="create author ")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return new ResponseEntity<Author>(authorService.create(author), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author,@PathVariable Long id) {
        return ResponseEntity.ok(authorService.update(id, author));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return new ResponseEntity<String>("successfully deleted",HttpStatus.ACCEPTED);
    }
}
