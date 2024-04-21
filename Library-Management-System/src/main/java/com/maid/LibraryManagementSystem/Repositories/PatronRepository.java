/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maid.LibraryManagementSystem.Repositories;

/**
 *
 * @author omarb
 */
import com.maid.LibraryManagementSystem.Entities.Patron;
import com.maid.LibraryManagementSystem.Exceptions.PatronNotFoundException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

    boolean existsByContactInformation(String contactInformation);

    @Transactional
    @Query("SELECT p FROM Patron p")
    List<Patron> getAll();

    @Transactional(readOnly = true)
    default Patron getPatronById(Long id) {
        return findById(id).orElseThrow(()
                -> new PatronNotFoundException("Patron with id " + id + " not found"));
    }

    @Modifying
    @Transactional
    default Patron addPatron(String name, String contactInformation) {
        Patron patron = new Patron();
        patron.setName(name);
        patron.setContactInformation(contactInformation);
        return save(patron);
    }

    @Modifying
    @Transactional
    default Patron updatePatron(String name, String contact, Long id) {
        Patron patronToUpdate = getPatronById(id);
        patronToUpdate.setName(name);
        patronToUpdate.setContactInformation(contact);
        return save(patronToUpdate);
    }

    @Modifying
    @Transactional
    void deletePatronById(Long id);
}
