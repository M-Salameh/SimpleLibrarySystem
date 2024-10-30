package Services;


import ExecptionAndValidationHandler.ResourceNotFoundException;
import IRopositries.*;
import Entities.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BorrowService {

    @Autowired
    private IBorrowRecordRepository IBorrowRecordRepository;

    @Autowired
    private IBookRepository IBookRepository;

    @Autowired
    private IPatronRepository IPatronRepository;

    @Transactional
    public BorrowRecord borrowBook(Long patronId, Long bookId) {
        // Check if the book and patron exist
        Book book = IBookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));

        Patron patron = IPatronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + patronId));

        // Create a new BorrowRecord
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBook(book);
        borrowRecord.setPatron(patron);
        borrowRecord.setStartDate(LocalDate.now());

        // Save the BorrowRecord
        return IBorrowRecordRepository.save(borrowRecord);
    }

    @Transactional
    public BorrowRecord returnBook(Long patronId, Long bookId) {
        // Check if the book and patron exist
        Book book = IBookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));

        Patron patron = IPatronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + patronId));


        BorrowRecord borrowRecord = IBorrowRecordRepository.findByPatron_IdAndBook_Id(patronId , bookId);
        if(borrowRecord == null)
        {
            throw (new ResourceNotFoundException("Patron : " + patron.getName() + " has not borrowed book : " + book.getTitle()));
        }

        borrowRecord.setEndDate(LocalDate.now());
        long daysBorrowed = java.time.temporal.ChronoUnit.DAYS.between(borrowRecord.getStartDate(), borrowRecord.getEndDate());
        double rentalPricePerDay = book.getRentPricePerDay();
        double totalCost = daysBorrowed * rentalPricePerDay;
        borrowRecord.setReceipt(totalCost);
        return IBorrowRecordRepository.save(borrowRecord);
    }
}

