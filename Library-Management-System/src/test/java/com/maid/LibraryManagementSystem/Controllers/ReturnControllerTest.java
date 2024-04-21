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
import com.maid.LibraryManagementSystem.Services.ReturnService;
import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyLong;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReturnController.class)
public class ReturnControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReturnService returnService;
    @Test
    public void addReturnDateTest() throws Exception {
        BorrowingRecord record = new BorrowingRecord();
        record.setID(1L);
        record.setReturnDate(new Date());

        when(returnService.addReturnDate(anyLong(), anyLong())).thenReturn(record);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/return/1/patron/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
