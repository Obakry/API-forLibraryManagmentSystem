/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maid.LibraryManagementSystem.Services;

/**
 *
 * @author omarb
 */

import com.maid.LibraryManagementSystem.Entities.BorrowingRecord;
import com.maid.LibraryManagementSystem.Exceptions.NoBorrowingRecordException;
import com.maid.LibraryManagementSystem.Repositories.BookRepository;
import com.maid.LibraryManagementSystem.Repositories.PatronRepository;
import com.maid.LibraryManagementSystem.Repositories.BorrowingReturnRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnService {

    @Autowired
    private BorrowingReturnRepository returnRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PatronRepository patronRepository;

    public BorrowingRecord addReturnDate(Long bookId, Long patronId) {

        Optional<BorrowingRecord> optionalBorrowingRecord
                = Optional.ofNullable(returnRepository.getBorrowingRecord(bookId, patronId));

        if (optionalBorrowingRecord.isPresent()) {
            BorrowingRecord borrowingRecord = optionalBorrowingRecord.get();
            borrowingRecord.setReturnDate(new Date());

            return returnRepository.save(borrowingRecord);
        } else {
            throw new NoBorrowingRecordException("No borrowing record found for the given book/patron");
        }
    }

}
