package iterator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    private String title;
    private String ISBN;
    private Date date;
    private String author;
    private String publisher;
    private static List<Book> bookList = new ArrayList<>();

    // Constructor
    public Book(String title, String author, String publisher, String ISBN, Date date) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.date = date;
    }

    // Default constructor
    public Book(){
 
    }

    // Getters and setters
    public String getBookTitle() {
        return title;
    }

    public void setBookTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBookAuthor() {
        return author;
    }

    public void setBookAuthor(String author) {
        this.author = author;
    }

    public String getBookPublisher() {
        return publisher;
    }

    public void setBookPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public void add(Book book) {
        bookList.add(book);
    }
}

