package com.maid.LibraryManagementSystem.Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author omarb
 */

import com.maid.LibraryManagementSystem.Controllers.BorrowingController;
import com.maid.LibraryManagementSystem.Entities.BorrowingRecord;
import com.maid.LibraryManagementSystem.Services.BorrowingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(BorrowingController.class)
public class BorrowingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowingService borrowingService;

    @Test
    public void borrowBookTest() throws Exception {
        BorrowingRecord record = new BorrowingRecord();
        record.setID(1L);

        when(borrowingService.borrowBook(1L, 1L)).thenReturn(record);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/1/patron/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1}"));
    }
}