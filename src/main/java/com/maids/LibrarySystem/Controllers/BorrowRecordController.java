package com.maids.LibrarySystem.Controllers;


import com.maids.LibrarySystem.Entities.BorrowRecord;
import com.maids.LibrarySystem.Services.BorrowService;
import com.maids.LibrarySystem.IRepositries.IBookRepository;
import com.maids.LibrarySystem.IRepositries.IBorrowRecordRepository;
import com.maids.LibrarySystem.IRepositries.IPatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
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

    /**
     * Allow a patron to borrow a book.
     *
     * @param patronId The ID of the patron borrowing the book.
     * @param bookId   The ID of the book to be borrowed.
     * @return ResponseEntity containing the created borrow record.
     */


    @PostMapping("borrow/{patronId}/book/{bookId}")
    public ResponseEntity<BorrowRecord> borrowBook(@PathVariable Long patronId, @PathVariable Long bookId)
    {
        BorrowRecord borrowRecord = borrowService.borrowBook(patronId, bookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowRecord);
    }

    /**
     * Allow a patron to return a book.
     *
     * @param bookId   The ID of the book being returned.
     * @param patronId The ID of the patron returning the book.
     * @return ResponseEntity containing the updated borrow record.
     */
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId)
    {
        BorrowRecord borrowRecord = borrowService.returnBook(patronId , bookId );
        return ResponseEntity.ok(borrowRecord);
    }
}
