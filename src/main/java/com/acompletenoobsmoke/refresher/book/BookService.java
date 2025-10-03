package com.acompletenoobsmoke.refresher.book;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book getBookByID(Integer bookID) {
        return bookDAO.getBookByID(bookID).isPresent()
                ? bookDAO.getBookByID(bookID).get()
                : null;
    }

    public Book getBookByPersonIDandBookID(Integer personID, Integer bookID) {
        return bookDAO.getBookForUser(personID, bookID).isPresent()
                ? bookDAO.getBookForUser(personID, bookID).get()
                : null;
    }

    public Book addBook(Book book) {
        return bookDAO.addBook(book);
    }

    public Book addBookForUser(Integer userID, Book book) {
        return bookDAO.addBookForUser(userID, book);
    }

    public Book updateBook(Integer bookID, Book book) {
        return bookDAO.updateBook(bookID, book);
    }

    public Book updateBookForUser(Integer userID, Integer bookID, Book book) {
        return bookDAO.updateBookForUser(userID, bookID, book);
    }

    public void deleteBook(Integer bookID) {
        bookDAO.deleteBook(bookID);
    }

    public void deleteBookForUser(Integer userID, Integer bookID) {
        bookDAO.deleteBookForUser(userID, bookID);
    }
}
