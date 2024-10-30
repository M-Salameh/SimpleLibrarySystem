package Controllers;


import Entities.Book;
import IRopositries.IBookRepository;
import Services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {

    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private BookService bookService;

    // Retrieve all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by its ISBN
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn)
    {
        Book book = bookService.findByIsbn(isbn);
        return  ResponseEntity.ok(book);

        /*if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }

    // Retrieve a specific book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id)
    {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    // Add a new book
    @PostMapping
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.createBook(book);
    }

    // Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {

        Book book = bookService.updateBook(id , bookDetails);
        return ResponseEntity.ok(book);
    }

    // Remove a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}

