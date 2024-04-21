/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maid.LibraryManagementSystem.Services;

/**
 *
 * @author omarb
 */
import com.maid.LibraryManagementSystem.Entities.Book;
import com.maid.LibraryManagementSystem.Exceptions.DuplicateBookException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maid.LibraryManagementSystem.Repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    public Book addBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new DuplicateBookException("This book already exists(ISBN): "
                    + book.getIsbn());
        }
        return bookRepository.addBook(book.getTitle(), book.getAuthor(),
                book.getPublicationYear(), book.getIsbn());
    }

    public Book updateBook(Book b, Long id) {
        if (b == null) {
            throw new IllegalArgumentException("Book not null");
        }
        return bookRepository.updateBook(b.getTitle(), b.getAuthor(),
                b.getPublicationYear(), b.getIsbn(), id);
    }

    public String deleteBook(Long id) {
        Optional<Book> bookToDelete = bookRepository.findById(id);

        if (bookToDelete.isPresent()) {
            bookRepository.deleteBookById(id);
            return "Book with ID " + id + " deleted successfully.";
        } else {
            return "Book with ID " + id + " not found.";
        }
    }
}
