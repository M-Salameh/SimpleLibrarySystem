package com.maids.LibrarySystem.IRepositries;

import com.maids.LibrarySystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    public Book findByIsbn(String isbn);
}
