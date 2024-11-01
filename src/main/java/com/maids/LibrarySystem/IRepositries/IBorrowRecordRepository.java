package com.maids.LibrarySystem.IRepositries;

import com.maids.LibrarySystem.Entities.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing borrow record-related database operations.
 */
@Repository
public interface IBorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    /**
     * Find a borrow record by the patron's ID and the book's ID.
     *
     * @param patronId The ID of the patron.
     * @param bookId   The ID of the book.
     * @return The borrow record with the specified patron ID and book ID.
     */
    public BorrowRecord findByPatron_IdAndBook_Id(Long patronId, Long bookId);
}