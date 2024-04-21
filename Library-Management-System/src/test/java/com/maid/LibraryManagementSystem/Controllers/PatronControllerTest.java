/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maid.LibraryManagementSystem.Controllers;

/**
 *
 * @author omarb
 */
import com.maid.LibraryManagementSystem.Entities.Patron;
import com.maid.LibraryManagementSystem.Exceptions.PatronNotFoundException;
import com.maid.LibraryManagementSystem.Services.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(PatronController.class)
public class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService patronService;

    private Patron patron1;
    private Patron patron2;

    @BeforeEach
    public void setup() {
        patron1 = new Patron();
        patron2 = new Patron();
        
        patron1.setName("Omar Bakry");
        patron1.setContactInformation("01234567891");
        patron2.setName("Omar Abdallah");
        patron2.setContactInformation("01554538516");
    }

    @Test
    public void getAllPatronsTest() throws Exception {
        List<Patron> patrons = Arrays.asList(patron1, patron2);

        when(patronService.getAllPatrons()).thenReturn(patrons);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(""
                        + "[{'name': 'Omar Bakry', 'contactInformation': '01234567891'}, "
                        + "{'name': 'Omar Abdallah', 'contactInformation': '01554538516'}]"));
    }

    @Test
    public void getPatronByIdTest() throws Exception {
        when(patronService.getPatronById(1L)).thenReturn(patron1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'name': 'Omar Bakry', 'contactInformation': '01234567891'}"));
    }

    @Test
    public void getPatronByIdNotFoundTest() throws Exception {
        when(patronService.getPatronById(1L)).thenThrow(new PatronNotFoundException("Patron with id 1 not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        Mockito.verify(patronService).getPatronById(1L);
    }

    @Test
    public void updatePatronTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/patrons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Omar Bakry\", \"contactInformation\": \"01234567891\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void addPatronTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/patrons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Omar Bakry\", \"contactInformation\": \"01234567891\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePatronByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/patrons/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
