package iterator;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainBook {
    private List<Book> books = new ArrayList<>();
    private String path;

    public void load(String path) throws Exception {
        CsvReader reader = new CsvReader(path);
        reader.readHeaders();

        while (reader.readRecord()) {
            String title = reader.get("title");
            String ISBN = reader.get("ISBN");
            Date date = new Date(reader.get("date"));
            String author = reader.get("author");
            String publisher = reader.get("publisher");
            String url = reader.get("url"); 

            Book book = new Book(title, author, publisher, ISBN, date, url);
            book.setUrl(url); 
            books.add(book);
        }

        reader.close();
    }

    public void update(String path) throws Exception {
        CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');

        csvOutput.write("title");
        csvOutput.write("ISBN");
        csvOutput.write("date");
        csvOutput.write("author");
        csvOutput.write("publisher");
        csvOutput.write("url"); 
        csvOutput.endRecord();

        for (Book book : books) {
            csvOutput.write(book.getBookTitle());
            csvOutput.write(book.getISBN());
            csvOutput.write((book.getDate()).toString());
            csvOutput.write(book.getBookAuthor());
            csvOutput.write(book.getBookPublisher());
            csvOutput.write(book.getUrl()); 
            csvOutput.endRecord();
        }

        csvOutput.close();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
	}
}
