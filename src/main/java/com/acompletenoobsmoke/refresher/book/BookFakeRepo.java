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
        if (BookValidation.validateBookExistence(bookID, books))
            return books.stream().filter(b -> b.getBookID().equals(bookID)).findAny();
        throw new RuntimeException("Book With ID: " + bookID + " doesn't exist");
    }

    @Override
    public Optional<List<Book>> getAllBooks() {
        return Optional.of(books);
    }

    @Override
    public Optional<Book> getBookForUser(Integer userID, Integer bookID) {
        return books.stream()
                .filter(book -> book.getPersonID().equals(userID) && book.getBookID().equals(bookID))
                .findAny();
    }

    @Override
    public Optional<List<Book>> getAllBooksForUser(Integer userID) {
        return Optional.of(books.stream()
                .filter(book -> book.getPersonID().equals(userID))
                .findAny()
                .stream()
                .toList());
    }

    @Override
    public Book addBook(Book book) {
        Book newBook = new Book(bookID.getAndIncrement(), book.getTitle(), book.getAuthor());
        books.add(newBook);
        return newBook;
    }

    @Override
    public Book addBookForUser(Integer userID, Book book) {
        Book newBook = new Book(book.getBookID(), userID, book.getTitle(), book.getAuthor());
        books.add(newBook);
        return newBook;
    }

    @Override
    public Book updateBook(Integer bookID, Book book) {
        if (BookValidation.validateBookExistence(bookID, books)) {
            Book existingBook = getBookByID(bookID).get();
            String author = (BookValidation.validateBookAuthor(bookID, book.getAuthor(), books)) ? existingBook.getAuthor() :  book.getAuthor();
            String title = (BookValidation.validateBookTitle(bookID, book.getTitle(), books)) ? existingBook.getTitle() :  book.getTitle();
            Book updatedBook = new Book(bookID, existingBook.getPersonID(), title, author);
            books.remove(existingBook);
            books.add(updatedBook);
            return updatedBook;
        }
        throw new IllegalArgumentException("Update Incomplete");
    }

    @Override
    public Book updateBookForUser(Integer userID, Integer bookID, Book book) {
        if (BookValidation.validateUserID(userID, books)
                && BookValidation.validateBookExistence(bookID, books)) {
            Book exisingBook = getBookForUser(userID, bookID).get();
            String author = (BookValidation.validateBookAuthor(bookID, book.getAuthor(), books)) ? exisingBook.getAuthor() :  book.getAuthor();
            String title = (BookValidation.validateBookTitle(bookID, book.getTitle(), books)) ? exisingBook.getTitle() :  book.getTitle();
            Book updatedBook = new Book(bookID, userID, title, author);
            books.remove(exisingBook);
            return updatedBook;
        }
        throw new IllegalArgumentException("Update Incomplete");
    }

    @Override
    public void deleteBook(Integer bookID) {
        if(BookValidation.validateBookExistence(bookID, books))
            books.removeIf(book -> bookID.equals(book.getBookID()));
        throw new RuntimeException("Delete incomplete -- Book With ID: " + bookID + " doesn't exist");
    }

    @Override
    public void deleteBookForUser(Integer userID, Integer bookID) {
        if(BookValidation.validateBookExistence(bookID, books)
                && BookValidation.validateBookExistence(userID, books))
            books.removeIf(book -> bookID.equals(book.getBookID())
                 && userID.equals(book.getPersonID()));
        throw new RuntimeException("Delete incomplete");
    }
}
