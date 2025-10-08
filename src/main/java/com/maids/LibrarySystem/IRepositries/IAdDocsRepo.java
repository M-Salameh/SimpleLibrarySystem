package com.maids.LibrarySystem.IRepositries;

import com.maids.LibrarySystem.Entities.ApprovedDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdDocsRepo extends JpaRepository<ApprovedDocuments, Long> {

}
