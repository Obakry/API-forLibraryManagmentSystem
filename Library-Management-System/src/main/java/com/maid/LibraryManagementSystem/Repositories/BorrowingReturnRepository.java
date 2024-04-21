/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author omarb
 */
package com.maid.LibraryManagementSystem.Repositories;

import com.maid.LibraryManagementSystem.Entities.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BorrowingReturnRepository extends JpaRepository<BorrowingRecord, Long> {

    @Transactional
    @Query("SELECT br FROM BorrowingRecord br "
            + "WHERE br.book.id = ?1 "
            + "AND br.patron.id = ?2 "
            + "AND br.returnDate IS NULL")
    BorrowingRecord getBorrowingRecord(Long bookId, Long patronId);
}
