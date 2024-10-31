package com.maids.LibrarySystem.IRepositries;

import com.maids.LibrarySystem.Entities.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBorrowRecordRepository extends JpaRepository<BorrowRecord, Long>
{
    public BorrowRecord findByPatron_IdAndBook_Id(Long patronId, Long bookId);
}
