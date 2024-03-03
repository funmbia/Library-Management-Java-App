import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookCollection implements Iterable<Book> {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Iterator<Book> iterator() {
        return books.iterator();
    }

    public Book searchBookByName(String name) {
        for (Book book : books) {
            if (book.getBookTitle().equals(name)) {
                return book;
            }
        }
        return null;
    }

}

