# Library Management System:
- A Library Management System API using Spring Boot.
  
## Introduction:
This is an API to manage a library system by providing different functionalities: listing all available (books/patrons), borrowing/returning books.

## Technologies:
Spring-Boot.                      
Spring Data JPA.                        
Spring Web.                      
MySQL.

## How to Use:
```
git clone 
cd API-forLibraryManagmentSystem
mvn clean install
mvn spring-boot:run
```
Then fire you postman and Enjoy!!

## Classes & Components:
### Entities:

● Book: Includes attributes like ID, title, author, publication year, ISBN.

● Patron: Contains details like ID, name, contact information.

● Borrowing Record: Tracks the association between books and patrons, including borrowing and return dates.

### Relationships Between Entities and ER Diagram
- The Book entity has a one-to-many relationship with the Borrowing Record entity.
- The Patron entity has a one-to-many relationship with the Borrowing Record entity.
- The Borrowing Record entity has a many-to-one relationship with Book and Patron entities.

## API Endpoints:
● Implement RESTful endpoints to do the following operations:

### Book management endpoints:
● GET /api/books: Retrieve a list of all books.

● GET /api/books/{id}: Retrieve details of a specific book by ID.

● POST /api/books: Add a new book to the library.

● PUT /api/books/{id}: Update an existing book's information.

● DELETE /api/books/{id}: Remove a book from the library.

### Patron management endpoints:
● GET /api/patrons: Retrieve a list of all patrons.

● GET /api/patrons/{id}: Retrieve details of a specific patron by ID.

● POST /api/patrons: Add a new patron to the system.

● PUT /api/patrons/{id}: Update an existing patron's information.

● DELETE /api/patrons/{id}: Remove a patron from the system.

### Borrowing endpoints:
● POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to borrow a book.

● PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.

## AOP
### LoggingAspect: 
Implement a logging aspect to log method calls and execution times.
