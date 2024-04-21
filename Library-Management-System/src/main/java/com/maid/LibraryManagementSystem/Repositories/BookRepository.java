/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maid.LibraryManagementSystem.Repositories;

/**
 *
 * @author omarb
 */

import org.springframework.data.jpa.repository.JpaRepository;
import com.maid.LibraryManagementSystem.Entities.Book;
import com.maid.LibraryManagementSystem.Exceptions.BookNotFoundException;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public boolean existsByIsbn(String isbn);

    @Query("SELECT b FROM Book b")
    List<Book> getAllBooks();

    default Book getBookById(Long id) {
        return findById(id).orElseThrow(()
                -> new BookNotFoundException("Book with id " + id + " not found"));
    }

    @Modifying
    @Transactional
    default Book addBook(String title, String author, int publicationYear, String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(publicationYear);
        book.setIsbn(isbn);
        return save(book);
    }

    @Modifying
    @Transactional
    default Book updateBook(String title, String author, int publicationYear, String isbn, Long id) {
        Book bookToUpdate = getBookById(id);
        bookToUpdate.setTitle(title);
        bookToUpdate.setAuthor(author);
        bookToUpdate.setPublicationYear(publicationYear);
        bookToUpdate.setIsbn(isbn);
        return save(bookToUpdate);
    }

    @Modifying
    @Transactional
    void deleteBookById(Long id);
}
