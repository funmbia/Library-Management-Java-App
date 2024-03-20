package iterator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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

	public List<Book> getBooks() {
		return books;
	}

	public Book getBook(int index) {
		if (index >= 0 && index < books.size()) {
			return books.get(index);
		} else {
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
	}

	public Book searchBookByName(String name) {
		for (Book book : books) {
			if (book.getBookTitle().equals(name)) {
				return book;
			}
		}
		return null;
	}

	public List<String> getRecommendedBooksStartingWith(char initialChar) {
		List<String> recommendedBooks = new ArrayList<>();
		for (Book book : books) {
			if (book.getBookTitle().charAt(0) == initialChar) {
				recommendedBooks.add(book.getBookTitle());
			}
		}
		return recommendedBooks;
	}
}
