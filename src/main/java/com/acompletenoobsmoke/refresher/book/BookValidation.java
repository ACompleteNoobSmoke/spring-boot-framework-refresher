package com.acompletenoobsmoke.refresher.book;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class BookValidation {

    static boolean validateBookExistence(Integer bookID, List<Book> bookList) {
         Optional<Book> optionalBook = Optional.ofNullable(bookList.stream().filter(book -> book.getBookID().equals(bookID))
                 .findFirst().orElseThrow(() -> new RuntimeException("Book Not Found")));
         return optionalBook.isPresent();
    }

    static boolean validateUserID(Integer userID, List<Book> bookList) {
        Optional<Book> optionalBook = Optional.ofNullable(bookList.stream().filter(book -> book.getPersonID().equals(userID)).findFirst()
                .orElseThrow(() -> new RuntimeException("User Not Found")));
        return optionalBook.isPresent();
    }

    static boolean validateBookAuthor(Integer bookID, String author, List<Book> bookList) {
        return author == null || bookList.stream().anyMatch(book -> book.getAuthor().equals(author));
    }

    static boolean validateBookTitle(Integer bookID, String title, List<Book> bookList) {
        return title == null || bookList.stream().anyMatch(book -> book.getTitle().equals(title));
    }
}
