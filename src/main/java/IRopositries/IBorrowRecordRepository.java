package IRopositries;

import Entities.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBorrowRecordRepository extends JpaRepository<BorrowRecord, Long>
{
    BorrowRecord findByPatron_IdAndBook_Id(Long patronId, Long bookId);
}
