package Controllers;


import Entities.BorrowRecord;
import IRopositries.*;
import Services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class BorrowRecordController 
{

    @Autowired
    private IBorrowRecordRepository borrowRecordRepository;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IPatronRepository patronRepository;

    @Autowired
    private BorrowService borrowService;

    // Allow a patron to borrow a book
    @PostMapping("borrow/{patronId}/book/{bookId}")
    public ResponseEntity<BorrowRecord> borrowBook(@PathVariable Long patronId, @PathVariable Long bookId)
    {
        BorrowRecord borrowRecord = borrowService.borrowBook(patronId, bookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowRecord);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId)
    {
        BorrowRecord borrowRecord = borrowService.returnBook(patronId , bookId );
        return ResponseEntity.ok(borrowRecord);
    }
}
