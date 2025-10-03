package com.acompletenoobsmoke.refresher.book;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class BookFakeRepo implements BookDAO {

    private List<Book> books;
    private AtomicInteger bookID = new AtomicInteger(0);

    @Override
    public Optional<Book> getBookByID(Integer bookID) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Book>> getAllBooks() {
        return Optional.empty();
    }

    @Override
    public Optional<Book> getBookForUser(Integer userID, Integer bookID) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Book>> getAllBooksForUser(Integer userID) {
        return Optional.empty();
    }

    @Override
    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    @Override
    public Book addBookForUser(Integer userID, Book book) {
        books.add(book);
        return null;
    }

    @Override
    public Book updateBook(Integer bookID, Book book) {
        return null;
    }

    @Override
    public Book updateBookForUser(Integer userID, Integer bookID, Book book) {
        if (BookValidation.validateUserID(userID, books)
                && BookValidation.validateBookExistence(bookID, books)) {
            Book exisingBook = books.stream().filter(book1 -> book1.getBookID().equals(bookID)
                    && book1.getPersonID().equals(userID)).findFirst().get();
            String author = (BookValidation.validateBookAuthor(bookID, book.getAuthor(), books)) ? exisingBook.getTitle() :  exisingBook.getAuthor();
            String title = (BookValidation.validateBookTitle(bookID, book.getTitle(), books)) ? exisingBook.getTitle() :  book.getTitle();
            Book updatedBook = new Book(bookID, userID, title, author);
            books.remove(exisingBook);
            return updatedBook;
        }
        return null;
    }

    @Override
    public void deleteBook(Integer bookID) {
        if(BookValidation.validateBookExistence(bookID, books))
            books.removeIf(book -> bookID.equals(book.getBookID()));
    }

    @Override
    public void deleteBookForUser(Integer userID, Integer bookID) {
        if(BookValidation.validateBookExistence(bookID, books)
                && BookValidation.validateBookExistence(userID, books))
            books.removeIf(book -> bookID.equals(book.getBookID())
                 && userID.equals(book.getPersonID()));
    }
}
