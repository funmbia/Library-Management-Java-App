import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    private String bookTitle;
    private String ISBN;
    private Date date;
    private String bookAuthor;
    private String bookPublisher;
    private static List<Book> bookList = new ArrayList<>();

    // Constructor
    public Book(String bookTitle, String bookAuthor, String bookPublisher, String ISBN, Date date) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.ISBN = ISBN;
        this.date = date;
    }

    // Default constructor
    public Book(){
 
    }

    // Getters and setters
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
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
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
    
    public void add(Book book) {
        bookList.add(book);
    }
}

