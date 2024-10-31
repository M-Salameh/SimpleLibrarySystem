package com.maids.LibrarySystem.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @Past(message = "Publish date must be in the Past") // not future no !
    private LocalDate dateOfPublish;

    @Min(value = 0, message = "Rent price must be non-negative")
    private double rentPricePerDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(LocalDate dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public double getRentPricePerDay() {
        return rentPricePerDay;
    }

    public void setRentPricePerDay(double rentPricePerDay) {
        this.rentPricePerDay = rentPricePerDay;
    }

}
