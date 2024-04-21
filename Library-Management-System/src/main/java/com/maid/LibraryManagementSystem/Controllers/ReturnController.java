/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maid.LibraryManagementSystem.Controllers;

/**
 *
 * @author omarb
 */
import com.maid.LibraryManagementSystem.Entities.BorrowingRecord;
import com.maid.LibraryManagementSystem.Exceptions.NoBorrowingRecordException;
import com.maid.LibraryManagementSystem.Services.ReturnService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/return")
public class ReturnController {

    @Autowired
    private ReturnService returngService;

    @PutMapping("/{bookId}/patron/{patronId}")
    public BorrowingRecord addReturnDate(@PathVariable Long bookId, @PathVariable Long patronId) {
        return returngService.addReturnDate(bookId, patronId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoBorrowingRecordException.class)
    public Map<String, String> handleNoBorrowingRecordException(
            NoBorrowingRecordException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }
}
