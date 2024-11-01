package com.maids.LibrarySystem.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Entity representing a Book in the Library System.
 */
@Entity
@Data
public class Book {
    /**
     * The unique identifier for the book.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The unique ISBN of the book.
     */
    @Column(unique = true)
    @NotBlank(message = "ISBN is required")
    private String isbn;

    /**
     * The title of the book.
     */
    @NotBlank(message = "Title is required")
    private String title;

    /**
     * The author of the book.
     */
    @NotBlank(message = "Author is required")
    private String author;

    /**
     * The date the book was published. Must be in the past.
     */
    @Past(message = "Publish date must be in the Past") // not future no !
    private LocalDate dateOfPublish;

    /**
     * The rent price per day for the book. Must be non-negative.
     */
    @Min(value = 0, message = "Rent price must be non-negative")
    private double rentPricePerDay;

    /**
     * Gets the unique identifier for the book.
     *
     * @return the id of the book.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the book.
     *
     * @param id the new id of the book.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the ISBN of the book.
     *
     * @return the isbn of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn the new isbn of the book.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets the title of the book.
     *
     * @return the title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the new title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author of the book.
     *
     * @return the author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the new author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the publish date of the book.
     *
     * @return the publish date of the book.
     */
    public LocalDate getDateOfPublish() {
        return dateOfPublish;
    }

    /**
     * Sets the publish date of the book.
     *
     * @param dateOfPublish the new publish date of the book.
     */
    public void setDateOfPublish(LocalDate dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    /**
     * Gets the rent price per day of the book.
     *
     * @return the rent price per day of the book.
     */
    public double getRentPricePerDay() {
        return rentPricePerDay;
    }

    /**
     * Sets the rent price per day of the book.
     *
     * @param rentPricePerDay the new rent price per day of the book.
     */
    public void setRentPricePerDay(double rentPricePerDay) {
        this.rentPricePerDay = rentPricePerDay;
    }

}
