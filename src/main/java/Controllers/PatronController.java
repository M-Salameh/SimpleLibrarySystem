package Controllers;


import Entities.Patron;
import IRopositries.IPatronRepository;
import Services.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private IPatronRepository patronRepository;
    @Autowired
    private PatronService patronService;

    // Retrieve all patrons
    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    // Retrieve a specific patron by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {

        Patron patron = patronService.getPatronById(id);

        return ResponseEntity.ok(patron);

    }

    // Add a new patron
    @PostMapping
    public ResponseEntity<Patron> createPatron(@Valid @RequestBody Patron patron) {
        Patron patron1 = patronService.createPatron(patron);
        return ResponseEntity.ok(patron1);
    }

    // Update an existing patron
    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @Valid @RequestBody Patron patronDetails)
    {
        Patron patron = patronService.updatePatron(id , patronDetails);
        return ResponseEntity.ok(patron);
    }

    // Remove a patron
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);

        return ResponseEntity.noContent().build();
    }
}
