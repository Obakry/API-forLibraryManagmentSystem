/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maid.LibraryManagementSystem.Services;

/**
 *
 * @author omarb
 */
import com.maid.LibraryManagementSystem.Entities.Patron;
import com.maid.LibraryManagementSystem.Exceptions.DuplicateContactException;
import com.maid.LibraryManagementSystem.Repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.getAll();
    }

    public Patron getPatronById(Long id) {
        return patronRepository.getPatronById(id);
    }

    public Patron addPatron(Patron patron) {

        if (patronRepository.existsByContactInformation(patron.getContactInformation())) {
            throw new DuplicateContactException("Contact information already exists: "
                    + patron.getContactInformation());
        }
        return patronRepository.addPatron(patron.getName(),
                patron.getContactInformation());
    }

    public Patron updatePatron(Patron patron, Long id) {

        if (patron == null) {
            throw new RuntimeException("Patron not found");
        }
        return patronRepository.updatePatron(patron.getName(),
                patron.getContactInformation(), id);
    }

    public String deletePatronById(Long id) {
        Optional<Patron> patronToDelete = patronRepository.findById(id);

        if (patronToDelete.isPresent()) {
            patronRepository.deletePatronById(id);
            return "Patron with ID " + id + " deleted successfully.";
        } else {
            return "Patron with ID " + id + " not found.";
        }
    }
}
