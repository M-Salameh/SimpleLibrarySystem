package com.maids.LibrarySystem.Controllers;

import Controllers.BorrowRecordController;
import Entities.Book;
import Entities.BorrowRecord;
import Entities.Patron;
import IRopositries.IBorrowRecordRepository;
import IRopositries.IBookRepository;
import IRopositries.IPatronRepository;
import Services.BorrowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BorrowRecordControllerTest {

    @InjectMocks
    private BorrowRecordController borrowRecordController;

    @Mock
    private IBorrowRecordRepository borrowRecordRepository;

    @Mock
    private IBookRepository bookRepository;

    @Mock
    private IPatronRepository patronRepository;

    @Mock
    private BorrowService borrowService;

    private BorrowRecord borrowRecord;
    private Patron patron;
    private Book book;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        patron = new Patron();
        patron.setId(1L);
        patron.setName("John Doe");
        patron.setEmail("patronTest@gmail.com");
        patron.setAddress("NY , USA");

        book = new Book();
        book.setId(1L);
        book.setIsbn("1234567890");
        book.setTitle("Test Book");
        book.setDateOfPublish(LocalDate.of(2023,1,1));
        book.setAuthor("Me");
        book.setRentPricePerDay(10);

        borrowRecord = new BorrowRecord();
        borrowRecord.setId(1L);
        borrowRecord.setBook(book);
        borrowRecord.setPatron(patron);

        // Set other properties as needed
    }

    @Test
    public void testBorrowBook() {
        when(borrowService.borrowBook(1L, 1L)).thenReturn(borrowRecord);

        ResponseEntity<BorrowRecord> response = borrowRecordController.borrowBook(1L, 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(borrowRecord);
        verify(borrowService, times(1)).borrowBook(1L, 1L);
    }

    @Test
    public void testReturnBook() {
        when(borrowService.returnBook(1L, 1L)).thenReturn(borrowRecord);

        ResponseEntity<BorrowRecord> response = borrowRecordController.returnBook(1L, 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(borrowRecord);
        verify(borrowService, times(1)).returnBook(1L, 1L);
    }
}