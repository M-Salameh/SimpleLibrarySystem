package com.maids.LibrarySystem.Services;


import com.maids.LibrarySystem.ExecptionAndValidationHandler.ResourceNotFoundException;


import com.maids.LibrarySystem.Entities.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PatronService {
    @Autowired
    private com.maids.LibrarySystem.IRepositries.IPatronRepository IPatronRepository;


    public Patron getPatronById(Long patronId) {
        // Check if the book and patron exist
        Patron patron = IPatronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + patronId));

        return patron;
    }

    public Patron createPatron(Patron patron)
    {
        return IPatronRepository.save(patron);
    }
    @Transactional
    public Patron updatePatron(Long id, Patron patronDetails)
    {
        return IPatronRepository.findById(id)
                .map(patron ->
                {
                    patron.setAddress(patronDetails.getAddress());
                    patron.setEmail(patronDetails.getEmail());
                    patron.setName(patron.getName());
                    return patron;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + id));
    }
    public void deletePatron(Long id)
    {
        if (IPatronRepository.existsById(id))
        {
            IPatronRepository.deleteById(id);
        }
        else
        {
            throw (new ResourceNotFoundException("Patron not found with id " + id));
        }
    }
}
