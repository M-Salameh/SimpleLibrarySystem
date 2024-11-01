package com.maids.LibrarySystem.Services;


import com.maids.LibrarySystem.ExecptionAndValidationHandler.ResourceNotFoundException;


import com.maids.LibrarySystem.Entities.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service for managing patron-related operations.
 */
@Service
public class PatronService {

    @Autowired
    private com.maids.LibrarySystem.IRepositries.IPatronRepository IPatronRepository;

    /**
     * Retrieve a patron by its ID.
     *
     * @param patronId The ID of the patron to retrieve.
     * @return The patron with the specified ID.
     * @throws ResourceNotFoundException if the patron is not found.
     */
    public Patron getPatronById(Long patronId) {
        Patron patron = IPatronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + patronId));
        return patron;
    }

    /**
     * Create a new patron.
     *
     * @param patron The patron to create.
     * @return The created patron.
     */
    public Patron createPatron(Patron patron) {
        return IPatronRepository.save(patron);
    }

    /**
     * Update an existing patron.
     *
     * @param id            The ID of the patron to update.
     * @param patronDetails The new details for the patron.
     * @return The updated patron.
     * @throws ResourceNotFoundException if the patron is not found.
     */
    @Transactional
    public Patron updatePatron(Long id, Patron patronDetails) {
        return IPatronRepository.findById(id)
                .map(patron -> {
                    patron.setAddress(patronDetails.getAddress());
                    patron.setEmail(patronDetails.getEmail());
                    patron.setName(patron.getName());
                    return patron;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + id));
    }

    /**
     * Delete a patron by its ID.
     *
     * @param id The ID of the patron to delete.
     * @throws ResourceNotFoundException if the patron is not found.
     */
    public void deletePatron(Long id) {
        if (IPatronRepository.existsById(id)) {
            IPatronRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Patron not found with id " + id);
        }
    }
}
