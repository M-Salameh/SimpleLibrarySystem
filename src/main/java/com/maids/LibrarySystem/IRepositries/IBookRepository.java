package com.maids.LibrarySystem.IRepositries;

import com.maids.LibrarySystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository interface for managing book-related database operations.
 */
@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    /**
     * Find a book by its ISBN.
     *
     * @param isbn The ISBN of the book.
     * @return The book with the specified ISBN.
     */
    public Book findByIsbn(String isbn);

    @Query("SELECT b from Book b where b.bookAuthor.city is not null ")
    List<Book> getByCityOfAuthorTest();
}
