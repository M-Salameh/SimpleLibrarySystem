package com.maids.LibrarySystem.Controllers;


import com.maids.LibrarySystem.Entities.Patron;
import com.maids.LibrarySystem.IRepositries.IPatronRepository;
import com.maids.LibrarySystem.Services.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private IPatronRepository patronRepository;
    @Autowired
    private PatronService patronService;

    /**
     * Retrieve all patrons.
     *
     * @return List of all patrons.
     */
    @GetMapping
    //@Cacheable(value = "All-Patrons")
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    /**
     * Retrieve a specific patron by ID.
     *
     * @param id The ID of the patron to retrieve.
     * @return ResponseEntity containing the patron if found, or not found status.
     */
    @GetMapping("/{id}")
   // @Cacheable(value = "Patrons-by-ID" , key = "#id")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {

        Patron patron = patronService.getPatronById(id);

        return ResponseEntity.ok(patron);

    }

    /**
     * Add a new patron.
     *
     * @param patron The patron to add.
     * @return ResponseEntity containing the created patron.
     */
    @PostMapping
    //@CacheEvict(value = "All-Patrons")
    public ResponseEntity<Patron> createPatron(@Valid @RequestBody Patron patron) {
        Patron patron1 = patronService.createPatron(patron);
        return ResponseEntity.ok(patron1);
    }

    /**
     * Update an existing patron.
     *
     * @param id The ID of the patron to update.
     * @param patronDetails The new details for the patron.
     * @return ResponseEntity containing the updated patron.
     */
    @PutMapping("/{id}")
   // @CacheEvict(value = "All-Patrons")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @Valid @RequestBody Patron patronDetails)
    {
        Patron patron = patronService.updatePatron(id , patronDetails);
        return ResponseEntity.ok(patron);
    }

    /**
     * Remove a patron.
     *
     * @param id The ID of the patron to remove.
     * @return ResponseEntity with no content status.
     */
    @DeleteMapping("/{id}")
    //@CacheEvict(value = {"All-Patrons"  , "Patrons-by-ID"} , allEntries = true)
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);

        return ResponseEntity.noContent().build();
    }
}
