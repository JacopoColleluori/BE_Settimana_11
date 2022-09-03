package com.epicode.project.week_11.controller;

import com.epicode.project.week_11.model.Author;
import com.epicode.project.week_11.model.Book;
import com.epicode.project.week_11.model.Category;
import com.epicode.project.week_11.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/book/")
@Tag(name="Book")
@SecurityRequirement(name="bearerAuth")
public class BookController {
    @Autowired
  private  BookService bookService;

    @GetMapping
    @Operation(summary="return all books")
    public ResponseEntity<List<Book>> getAllBooks() {
    return  ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("{id}")
    @Operation(summary="return book by id")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @PostMapping("create")
    @Operation(summary="create a book")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
       return new ResponseEntity<Book>(bookService.create(book), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    @Operation(summary="update book")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable Long id) {
      return ResponseEntity.ok(bookService.update(id, book));
    }
    @DeleteMapping("{id}")
    @Operation(summary="delete book by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<String> deleteBook(@PathVariable Long id) {
      bookService.delete(id);
      return new ResponseEntity<String>("successfully deleted",HttpStatus.ACCEPTED);
    }

    @PostMapping("getByAuthor")
  public ResponseEntity<Set<Book>> getBookByAuthor(@RequestBody Set<Author> authors) {
      return ResponseEntity.ok(bookService.getBooksByAuthor(authors));
    }
  @PostMapping("getByCategory")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Set<Book>> getBookByCategory(@RequestBody Set <Category> categories) {
    return ResponseEntity.ok(bookService.getBooksByCategory(categories));
  }
}
