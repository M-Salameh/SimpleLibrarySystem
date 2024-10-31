package com.maids.LibrarySystem.Controllers;

import Controllers.BookController;
import Entities.Book;
import IRopositries.IBookRepository;
import Services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private IBookRepository bookRepository;

    @Mock
    private BookService bookService;

    private Book book;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId(1L);
        book.setIsbn("1234567890");
        book.setTitle("Test Book");
        book.setDateOfPublish(LocalDate.of(2023,1,1));
        book.setAuthor("Me");
        book.setRentPricePerDay(10);

    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = Arrays.asList(book);
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookController.getAllBooks();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Book");
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookByIsbn() {
        when(bookService.findByIsbn("1234567890")).thenReturn(book);

        ResponseEntity<Book> response = bookController.getBookByIsbn("1234567890");

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(book);
        verify(bookService, times(1)).findByIsbn("1234567890");
    }

    @Test
    public void testGetBookById() {
        when(bookService.getBookById(1L)).thenReturn(book);

        ResponseEntity<Book> response = bookController.getBookById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(book);
        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    public void testCreateBook() {
        when(bookService.createBook(book)).thenReturn(book);

        Book result = bookController.createBook(book);

        assertThat(result).isEqualTo(book);
        verify(bookService, times(1)).createBook(book);
    }

    @Test
    public void testUpdateBook() {
        when(bookService.updateBook(1L, book)).thenReturn(book);

        ResponseEntity<Book> response = bookController.updateBook(1L, book);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(book);
        verify(bookService, times(1)).updateBook(1L, book);
    }

    @Test
    public void testDeleteBook() {
        doNothing().when(bookService).deleteBookById(1L);

        ResponseEntity<Void> response = bookController.deleteBook(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(bookService, times(1)).deleteBookById(1L);
    }
}