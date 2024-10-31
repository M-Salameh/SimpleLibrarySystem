package com.maids.LibrarySystem.Services;


import com.maids.LibrarySystem.ExecptionAndValidationHandler.ResourceAlreadyExistsException;
import com.maids.LibrarySystem.ExecptionAndValidationHandler.ResourceNotFoundException;


import com.maids.LibrarySystem.Entities.Book;
import com.maids.LibrarySystem.IRepositries.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private com.maids.LibrarySystem.IRepositries.IBookRepository IBookRepository;

    public Book findByIsbn(String isbn) {
        // Check if the book and patron exist
        Book book = IBookRepository.findByIsbn(isbn);
        if(book == null)
        {
            throw (new ResourceNotFoundException("Book not found with isbn " + isbn));
        }

        return book;
    }

    public Book getBookById(Long id)
    {
        Book book = IBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        return book;
    }

    @Transactional
    public Book createBook(Book book)
    {
        Book _book = IBookRepository.findByIsbn(book.getIsbn());
        if (_book == null)
        {
            return IBookRepository.save(book);
        }
        else
        {
            throw (new ResourceAlreadyExistsException("Book with Same ISBN found !"));
        }
    }
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Book book = IBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setDateOfPublish(bookDetails.getDateOfPublish());
        book.setRentPricePerDay(book.getRentPricePerDay());

        return IBookRepository.save(book);

    }

    @Transactional
    public void deleteBookByISBN(String isbn) {
        Book book = IBookRepository.findByIsbn(isbn);
        if (book == null)
        {
            throw (new ResourceNotFoundException("Book not found with isbn " + isbn));
        }
        IBookRepository.delete(book);
    }
    @Transactional
    public void deleteBookById(Long id) {
        Book book = IBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        IBookRepository.delete(book);
    }

    public List<Book> findAll()
    {
        return IBookRepository.findAll();
    }
}