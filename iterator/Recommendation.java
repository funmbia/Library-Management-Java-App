package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Recommendation implements Iterable<Book> {
    private List<Book> recommendedBooks = new ArrayList<>();

    public void recommendBook(Book book) {
        recommendedBooks.add(book);
    }

    @Override
    public Iterator<Book> iterator() {
        return recommendedBooks.iterator();
    }

    public List<Book> getRecommendedBooks() {
        return recommendedBooks;
	}
}
