package com.maids.LibrarySystem.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;


@Entity
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    @NotNull(message = "Patron is required")
    private Patron patron;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @NotNull(message = "Book is required")
    private Book book;

    @CurrentTimestamp
    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @CurrentTimestamp
    private LocalDate endDate;  // Optional, can be set when the book is returned

    private Double receipt; // Total cost for the borrowing period

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getReceipt() {
        return receipt;
    }

    public void setReceipt(Double receipt) {
        this.receipt = receipt;
    }
}