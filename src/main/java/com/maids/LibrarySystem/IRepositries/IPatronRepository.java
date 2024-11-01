package com.maids.LibrarySystem.IRepositries;

import com.maids.LibrarySystem.Entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for managing patron-related database operations.
 */
@Repository
public interface IPatronRepository extends JpaRepository<Patron, Long> {
}

