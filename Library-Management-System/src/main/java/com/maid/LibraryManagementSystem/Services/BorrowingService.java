/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author omarb
 */
package com.maid.LibraryManagementSystem.Services;

import com.maid.LibraryManagementSystem.Entities.Book;
import com.maid.LibraryManagementSystem.Entities.BorrowingRecord;
import com.maid.LibraryManagementSystem.Entities.Patron;
import com.maid.LibraryManagementSystem.Exceptions.BookAlreadyBorrowedException;
import com.maid.LibraryManagementSystem.Repositories.BookRepository;
import com.maid.LibraryManagementSystem.Repositories.PatronRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maid.LibraryManagementSystem.Repositories.BorrowingReturnRepository;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingReturnRepository borrowingRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PatronRepository patronRepository;

    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        BorrowingRecord existingRecord = borrowingRepository.getBorrowingRecord(bookId, patronId);
        Book book = bookRepository.getBookById(bookId);
        Patron patron = patronRepository.getPatronById(patronId);

        if (existingRecord == null) {
            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBook(book);
            borrowingRecord.setPatron(patron);
            borrowingRecord.setBorrowingDate(new Date());

            return borrowingRepository.save(borrowingRecord);
        } else {
            throw new BookAlreadyBorrowedException("This book is already borrowed and has not been returned yet.");
        }
    }

}
