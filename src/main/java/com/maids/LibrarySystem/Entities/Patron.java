package com.maids.LibrarySystem.Entities;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * Entity representing a Patron in the Library System.
 */
@Entity
public class Patron {

    /**
     * The unique identifier for the patron.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the patron. Cannot be blank.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * The email of the patron. Must be valid and unique.
     */
    @Column(unique = true)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "patron")
    private List<ApprovedDocuments> approvedDocuments;

    public List<ApprovedDocuments> getApprovedDocuments() {
        return approvedDocuments;
    }

    public void setApprovedDocuments(List<ApprovedDocuments> approvedDocuments) {
        this.approvedDocuments = approvedDocuments;
    }

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "ENTITY_ID",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(
                    ConstraintMode.NO_CONSTRAINT)))
    private List<String> tests;

    @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    private Date creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * The address of the patron. Cannot be blank.
     */
    @NotBlank(message = "Address is required")
    private String address;

    // Getters and Setters

    /**
     * Gets the unique identifier for the patron.
     *
     * @return the id of the patron.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the patron.
     *
     * @param id the new id of the patron.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the patron.
     *
     * @return the name of the patron.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the patron.
     *
     * @param name the new name of the patron.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the patron.
     *
     * @return the email of the patron.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the patron.
     *
     * @param email the new email of the patron.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the address of the patron.
     *
     * @return the address of the patron.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the patron.
     *
     * @param address the new address of the patron.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getTests() {
        return tests;
    }

    public void setTests(List<String> tests) {
        this.tests = tests;
    }
}