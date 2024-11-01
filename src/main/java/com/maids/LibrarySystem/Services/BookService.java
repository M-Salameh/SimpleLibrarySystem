package com.maids.LibrarySystem.Services;


import com.maids.LibrarySystem.ExecptionAndValidationHandler.ResourceAlreadyExistsException;
import com.maids.LibrarySystem.ExecptionAndValidationHandler.ResourceNotFoundException;


import com.maids.LibrarySystem.Entities.Book;
import com.maids.LibrarySystem.IRepositries.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Service for managing book-related operations.
 */
@Service
public class BookService {

    @Autowired
    private IBookRepository IBookRepository;

    /**
     * Find a book by its ISBN.
     *
     * @param isbn The ISBN of the book.
     * @return The book with the specified ISBN.
     * @throws ResourceNotFoundException if the book is not found.
     */
    public Book findByIsbn(String isbn) {
        Book book = IBookRepository.findByIsbn(isbn);
        if (book == null) {
            throw new ResourceNotFoundException("Book not found with isbn " + isbn);
        }
        return book;
    }

    /**
     * Get a book by its ID.
     *
     * @param id The ID of the book.
     * @return The book with the specified ID.
     * @throws ResourceNotFoundException if the book is not found.
     */
    public Book getBookById(Long id) {
        Book book = IBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        return book;
    }

    /**
     * Create a new book.
     *
     * @param book The book to create.
     * @return The created book.
     * @throws ResourceAlreadyExistsException if a book with the same ISBN already exists.
     */
    @Transactional
    public Book createBook(Book book) {
        Book _book = IBookRepository.findByIsbn(book.getIsbn());
        if (_book == null) {
            return IBookRepository.save(book);
        } else {
            throw new ResourceAlreadyExistsException("Book with Same ISBN found !");
        }
    }

    /**
     * Update an existing book.
     *
     * @param id           The ID of the book to update.
     * @param bookDetails The new details for the book.
     * @return The updated book.
     * @throws ResourceNotFoundException if the book is not found.
     */
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Book book = IBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setDateOfPublish(bookDetails.getDateOfPublish());
        book.setRentPricePerDay(bookDetails.getRentPricePerDay());
        return IBookRepository.save(book);
    }

    /**
     * Delete a book by its ISBN.
     *
     * @param isbn The ISBN of the book to delete.
     * @throws ResourceNotFoundException if the book is not found.
     */
    @Transactional
    public void deleteBookByISBN(String isbn) {
        Book book = IBookRepository.findByIsbn(isbn);
        if (book == null) {
            throw new ResourceNotFoundException("Book not found with isbn " + isbn);
        }
        IBookRepository.delete(book);
    }

    /**
     * Delete a book by its ID.
     *
     * @param id The ID of the book to delete.
     * @throws ResourceNotFoundException if the book is not found.
     */
    @Transactional
    public void deleteBookById(Long id) {
        Book book = IBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        IBookRepository.delete(book);
    }

    /**
     * Retrieve all books.
     *
     * @return List of all books.
     */
    public List<Book> findAll() {
        return IBookRepository.findAll();
    }
}
