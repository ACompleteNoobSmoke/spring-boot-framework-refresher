package com.acompletenoobsmoke.refresher.book;

public class Book {
    private final Integer bookID;
    private final Integer personID;
    private final String title;
    private final String author;

    public Book(Integer bookID, Integer personID, String title, String author) {
        this.bookID = bookID;
        this.personID = personID;
        this.title = title;
        this.author = author;
    }

    public Book(Integer bookID, String title, String author) {
        this.bookID = bookID;
        this.personID = null;
        this.title = title;
        this.author = author;
    }

    public Integer getBookID() {
        return bookID;
    }

    public Integer getPersonID() {
        return personID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", personID=" + personID +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
