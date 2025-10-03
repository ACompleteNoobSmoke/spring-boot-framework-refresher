package com.acompletenoobsmoke.refresher.book;

import java.util.List;
import java.util.Optional;

public interface BookDAO {

    Optional<Book> getBookByID(Integer bookID);
    Optional<List<Book>> getAllBooks();
    Optional<Book> getBookForUser(Integer userID, Integer bookID);
    Optional<List<Book>> getAllBooksForUser(Integer userID);
    Book addBook(Book book);
    Book addBookForUser(Integer userID, Book book);
    Book updateBook(Integer bookID, Book book);
    Book updateBookForUser(Integer userID, Integer bookID, Book book);
    void deleteBook(Integer bookID);
    void deleteBookForUser(Integer userID, Integer bookID);

}
