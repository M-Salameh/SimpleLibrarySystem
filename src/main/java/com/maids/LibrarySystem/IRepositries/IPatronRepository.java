package com.maids.LibrarySystem.IRepositries;

import com.maids.LibrarySystem.Entities.ApprovedDocuments;
import com.maids.LibrarySystem.Entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Repository interface for managing patron-related database operations.
 */
@Repository
public interface IPatronRepository extends JpaRepository<Patron, Long> {
    @Query("SELECT e FROM Patron e join e.tests t ")
    List<Patron> specialTest(@Param("test") String test);

    @Query("SELECT e FROM Patron e JOIN e.tests t WHERE lower(t) LIKE CONCAT('%', :test, '%')")
    List<Patron> specialTest2(@Param("test") String test);

    @Query("select p from Patron p where ( select count (distinct ad.documentTag) FROM ApprovedDocuments ad " +
            " WHERE ad.patron = p and ad.documentTag in :aha ) = :ahaSize ")
    List<Patron> testRevList (@Param("aha") List<String> aha , @Param("ahaSize") int ahaSize);

    @Query ("select p from Patron p join p.approvedDocuments ads " +
            " where ads.documentTag = ?2 and " +
            " p.id = ?1 " )
    Patron testSubQuery(Long id , String search);

}

