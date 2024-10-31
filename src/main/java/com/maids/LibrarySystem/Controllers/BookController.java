package com.maids.LibrarySystem.Controllers;


import com.maids.LibrarySystem.Entities.Book;
import com.maids.LibrarySystem.Services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;

import java.util.List;



@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    // Retrieve all books
    @GetMapping
   // @Cacheable(value = "All-Books")
    public List<Book> getAllBooks() {

        return bookService.findAll();
    }

    // Get a book by its ISBN
    @GetMapping("/isbn/{isbn}")
   // @Cacheable(value = "Books-by-ISBN" , key = "#isbn")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn)
    {
        Book book = bookService.findByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    // Retrieve a specific book by ID
    @GetMapping("/{id}")
    //@Cacheable(value = "Books-by-ID" , key = "#id")
    public ResponseEntity<Book> getBookById(@PathVariable Long id)
    {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    // Add a new book
    @PostMapping
    //@CacheEvict(value = "All-Books" , allEntries = true)
    public Book createBook(@Valid @RequestBody Book book)
    {
        return bookService.createBook(book);
    }

    // Update an existing book
    @PutMapping("/{id}")
    //@CacheEvict(value = {"All-Books" , "Books-by-ISBN"} , allEntries = true)
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {

        Book book = bookService.updateBook(id , bookDetails);
        return ResponseEntity.ok(book);
    }

    // Remove a book
    @DeleteMapping("/{id}")
    //@CacheEvict(value = {"All-Books" , "Books-by-ID" , "Books-by-ISBN"} , allEntries = true)
    public ResponseEntity<Void> deleteBook(@PathVariable Long id)
    {

        System.out.println("New Request in Deleting");
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}

