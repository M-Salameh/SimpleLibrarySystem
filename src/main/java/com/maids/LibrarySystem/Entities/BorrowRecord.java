package com.maids.LibrarySystem.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;

/**
 * Entity representing a Borrow Record in the Library System.
 */
@Entity
public class BorrowRecord {

    /**
     * The unique identifier for the borrow record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The patron who borrows the book. Cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    @NotNull(message = "Patron is required")
    private Patron patron;


    /**
     * The book being borrowed. Cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @NotNull(message = "Book is required")
    private Book book;

    /**
     * The date when the borrowing starts. Must be in the present or past.
     */
    @CurrentTimestamp
    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    /**
     * The date when the borrowing ends. Can be set when the book is returned.
     */
    @CurrentTimestamp
    private LocalDate endDate;

    /**
     * The total cost for the borrowing period.
     */
    private Double receipt;

    /**
     * Gets the unique identifier for the borrow record.
     *
     * @return the id of the borrow record.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the borrow record.
     *
     * @param id the new id of the borrow record.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the patron who borrows the book.
     *
     * @return the patron of the borrow record.
     */
    public Patron getPatron() {
        return patron;
    }

    /**
     * Sets the patron who borrows the book.
     *
     * @param patron the new patron of the borrow record.
     */
    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    /**
     * Gets the book being borrowed.
     *
     * @return the book of the borrow record.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the book being borrowed.
     *
     * @param book the new book of the borrow record.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Gets the start date of the borrowing.
     *
     * @return the start date of the borrow record.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the borrowing.
     *
     * @param startDate the new start date of the borrow record.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the borrowing.
     *
     * @return the end date of the borrow record.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the borrowing.
     *
     * @param endDate the new end date of the borrow record.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the total cost for the borrowing period.
     *
     * @return the receipt of the borrow record.
     */
    public Double getReceipt() {
        return receipt;
    }

    /**
     * Sets the total cost for the borrowing period.
     *
     * @param receipt the new receipt of the borrow record.
     */
    public void setReceipt(Double receipt) {
        this.receipt = receipt;
    }
}