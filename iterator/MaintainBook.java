import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainBook {
    public ArrayList<Book> books = new ArrayList<Book>();
    public String path;

    public void load(String path) throws Exception {
        CsvReader reader = new CsvReader(path);
        reader.readHeaders(); 

        while (reader.readRecord()) {
        	Book books = new Book();
            String bookTitle = reader.get("bookTitle");
            String ISBN = reader.get("ISBN");
            Date date = new Date(reader.get("date"));
            String bookAuthor = reader.get("bookAuthor");
            String bookPublisher = reader.get("bookPublisher");

            Book book = new Book(bookTitle, bookAuthor, bookPublisher, ISBN, date);
            books.add(book);
        }

        reader.close();
    }

    public void update(String path) throws Exception {
        CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');

        csvOutput.write("bookTitle");
        csvOutput.write("ISBN");
        csvOutput.write("date");
        csvOutput.write("bookAuthor");
        csvOutput.write("bookPublisher");
        csvOutput.endRecord();

        for (Book book : books) {
            csvOutput.write(book.getBookTitle());
            csvOutput.write(book.getISBN());
            csvOutput.write((book.getDate()).toString());
            csvOutput.write(book.getBookAuthor());
            csvOutput.write(book.getBookPublisher());
            csvOutput.endRecord();
        }

        csvOutput.close();
        System.out.println("CSV file has updated.");
    }
}
