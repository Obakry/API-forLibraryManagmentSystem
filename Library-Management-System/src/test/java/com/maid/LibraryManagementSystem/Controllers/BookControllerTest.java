/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maid.LibraryManagementSystem.Controllers;

/**
 *
 * @author omarb
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maid.LibraryManagementSystem.Entities.Book;
import com.maid.LibraryManagementSystem.Services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    
    @Test
    public void getAllBooksTest() throws Exception {
        Book book1 = new Book();
        Book book2 = new Book();
        
        book1.setTitle("Sample Book 1");
        book2.setTitle("Sample Book 2");

        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'title': 'Sample Book 1'}, {'title': 'Sample Book 2'}]"));
    }

    @Test
    public void getBookByIdTest() throws Exception {
        Book book = new Book();
        book.setTitle("Sample Book");

        when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'title': 'Sample Book'}"));
    }
    
    @Test
    public void addBookTest() throws Exception {
        Book book = new Book();
        book.setTitle("Sample Book");
        book.setAuthor("Sample Author");
        book.setIsbn("1234567890");
        book.setPublicationYear(2010);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String bookJson = objectMapper.writeValueAsString(book);

        when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void deleteBookByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}