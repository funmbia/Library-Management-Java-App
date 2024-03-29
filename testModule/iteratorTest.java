package testModule;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.csvreader.CsvWriter;
import iterator.Book;
import iterator.BookCollection;
import iterator.Iterator;
import iterator.MaintainBook;
import iterator.MaintainDate;
import iterator.Recommendation;
import iterator.SearchIterator;
import iterator.date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.FileWriter;

public class iteratorTest {

	/* Book class JUnit tests*/

	@Test
	public void testBookConstructor() {
		// Test case to verify the constructor  
		Book book = new Book("Title", "Author", "Publisher", "ISBN", new Date(), "URL");
		assertEquals("Title", book.getBookTitle());
		assertEquals("Author", book.getBookAuthor());
		assertEquals("Publisher", book.getBookPublisher());
		assertEquals("ISBN", book.getISBN());
		assertNotNull(book.getDate());
		assertEquals("URL", book.getUrl());
	}

	@Test
	public void testSetTitle() {
		// Test case to ensure that the title getter and setter works
		Book book = new Book();
		book.setBookTitle("New Title");
		assertEquals("New Title", book.getBookTitle());
	}

	@Test
	public void testSetISBN() {
		// Test case to ensure that the ISBN getter and setter works
		Book book = new Book();
		book.setISBN("1234567890");
		assertEquals("1234567890", book.getISBN());
	}

	@Test
	public void testSetDate() {
		// Test case to ensure the setDate getter and setter works
		Date date = new Date();
		Book book = new Book();
		book.setDate(date);
		assertEquals(date, book.getDate());
	}

	@Test
	public void testSetAuthor() {
		// Test case to ensure the author getter and setter works
		Book book = new Book();
		book.setBookAuthor("New Author");
		assertEquals("New Author", book.getBookAuthor());
	}

	@Test
	public void testSetPublisher() {
		// Test case to ensure the publisher getter and setter works
		Book book = new Book();
		book.setBookPublisher("New Publisher");
		assertEquals("New Publisher", book.getBookPublisher());
	}

	@Test
	public void testSetUrl() {
		// Test case to url getter and setter works
		Book book = new Book();
		book.setUrl("http://url.com");
		assertEquals("http://url.com", book.getUrl());
	}

	@Test
	public void testAddBookToCollection() {
		// Test case to ensure that a book is added correctly using the add method
		BookCollection collection = new BookCollection();
		Book book = new Book("Title", "Author", "Publisher", "ISBN", new Date(), "URL");
		collection.addBook(book);
		assertTrue(collection.getBooks().contains(book));
	}

	@Test
	public void testRentedStatus() {
		// Test case to ensure the rental status of books is displayed properly
		Book book = new Book();
		assertFalse(book.isRented()); 
		book.setRented(true);
		assertTrue(book.isRented());
	}

	@Test
	public void testReturnPhysicalItem() {
		// Test case to ensure returning a book works properly
		Book book = new Book();
		book.setRented(true); 
		assertTrue(book.isRented());
		assertTrue(book.returnPhysicalItem()); 
		assertFalse(book.isRented()); 
	}

	/* BookCollection class JUnit tests*/

	@Test
	public void testAddBookToCollection1() {
		// Test case to ensure that a book is added correctly using the add method
		BookCollection collection = new BookCollection();
		Book book = new Book("Title", "Author", "Publisher", "ISBN", new Date(), "URL");
		collection.addBook(book);
		assertTrue(collection.getBooks().contains(book));
	}

	public void testIteratorReturnsAllBooks() {
		// Test case to ensure that all the books in the collection are returned by iterator
		BookCollection collection = new BookCollection();
		Book book1 = new Book("Title1", "Author1", "Publisher1", "ISBN1", new Date(), "URL1");
		Book book2 = new Book("Title2", "Author2", "Publisher2", "ISBN2", new Date(), "URL2");
		collection.addBook(book1);
		collection.addBook(book2);
		Iterator<Book> iterator = (Iterator<Book>) collection.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(book1, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(book2, iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testGetBookByIndex() {
		// Test case to ensure the retrieval of a book by their index number is occurring properly
		BookCollection collection = new BookCollection();
		Book book1 = new Book("Title1", "Author1", "Publisher1", "ISBN1", new Date(), "URL1");
		Book book2 = new Book("Title2", "Author2", "Publisher2", "ISBN2", new Date(), "URL2");
		collection.addBook(book1);
		collection.addBook(book2);
		assertEquals(book1, collection.getBook(0));
		assertEquals(book2, collection.getBook(1));
	}

	@Test
	public void testSearchBookByName() {
		// Test case to ensure that books can by searched by their name
		BookCollection collection = new BookCollection();
		Book book1 = new Book("The Hunger Games", "Author1", "Publisher1", "ISBN1", new Date(), "URL1");
		Book book2 = new Book("The Little Prince", "Author2", "Publisher2", "ISBN2", new Date(), "URL2");
		collection.addBook(book1);
		collection.addBook(book2);
		assertEquals(book1, collection.searchBookByName("The Hunger Games"));
		assertEquals(book2, collection.searchBookByName("The Little Prince"));
		assertNull(collection.searchBookByName("Non-existent Book"));
	}

	@Test
	public void testGetRecommendedBooksStartingWith() {
		// Test case to ensure that recommended books, starting with the same letter, are showing up
		BookCollection collection = new BookCollection();
		Book book1 = new Book("The Hunger Games", "Author1", "Publisher1", "ISBN1", new Date(), "URL1");
		Book book2 = new Book("The Little Prince", "Author2", "Publisher2", "ISBN2", new Date(), "URL2");
		Book book3 = new Book("Cool Book", "Author3", "Publisher3", "ISBN3", new Date(), "URL3");
		collection.addBook(book1);
		collection.addBook(book2);
		collection.addBook(book3);
		List<String> recommendedBooks = collection.getRecommendedBooksStartingWith('T');
		assertEquals(2, recommendedBooks.size());
		assertTrue(recommendedBooks.contains("The Hunger Games"));
		assertTrue(recommendedBooks.contains("The Little Prince"));
	}

	@Test
	public void testGetBooks() {
		// Test case to ensure getBooks returns the correct list of books
		BookCollection collection = new BookCollection();
		Book book1 = new Book("Title1", "Author1", "Publisher1", "ISBN1", new Date(), "URL1");
		Book book2 = new Book("Title2", "Author2", "Publisher2", "ISBN2", new Date(), "URL2");
		collection.addBook(book1);
		collection.addBook(book2);
		List<Book> books = collection.getBooks();
		assertEquals(2, books.size());
		assertTrue(books.contains(book1));
		assertTrue(books.contains(book2));
	}

	@Test
	public void testAddingBooksToCollection() {
		// Test case to ensure adding books to the collection works properly
		BookCollection collection = new BookCollection();
		Book book1 = new Book("Title1", "Author1", "Publisher1", "ISBN1", new Date(), "URL1");
		Book book2 = new Book("Title2", "Author2", "Publisher2", "ISBN2", new Date(), "URL2");
		collection.addBook(book1);
		collection.addBook(book2);
		assertEquals(2, collection.getBooks().size());
	}

	@Test
	public void testIteratorReturnsNullWhenEmpty() {
		// Test case to ensure the iterator returns null when the collection is empty
		BookCollection collection = new BookCollection();
		assertFalse(collection.iterator().hasNext());
	}

	@Test
	public void testSearchNonExistentBookReturnsNull() {
		// Test case to ensure if searching for a non-existent book returns null
		BookCollection collection = new BookCollection();
		assertNull(collection.searchBookByName("Non-existent Book"));
	}

	@Test
	public void testRecommendedBooksIsEmptyInitially() {
		// Test case to ensure getting recommended books is initially empty
		BookCollection collection = new BookCollection();
		assertTrue(collection.getRecommendedBooksStartingWith('A').isEmpty());
	}

	/* date class JUnit tests*/

	@Test
	public void testDateConstructor() {
		// Test case to ensure constructor works properly
		date date = new date(2024, 3, 28);
		assertNotNull(date);
		assertEquals(2024, date.getYear());
		assertEquals(3, date.getMonth());
		assertEquals(28, date.getDay());
	}

	@Test
	public void testGetYear() {
		// Test case to ensure the year getter works
		date date = new date(2024, 3, 18);
		assertEquals(2024, date.getYear());
	}

	@Test
	public void testSetYear() {
		// Test case to ensure the year setter works
		date date = new date(2024, 3, 18);
		date.setYear(2024);
		assertEquals(2024, date.getYear());
	}

	@Test
	public void testGetMonth() {
		// Test case to ensure the month getter works
		date date = new date(2024, 3, 18);
		assertEquals(3, date.getMonth());
	}

	@Test
	public void testSetMonth() {
		// Test case to ensure the month setter works
		date date = new date(2024, 3, 18);
		date.setMonth(3);
		assertEquals(3, date.getMonth());
	}

	@Test
	public void testGetDay() {
		// Test case to ensure the day getter works
		date date = new date(2024, 3, 18);
		assertEquals(18, date.getDay());
	}

	@Test
	public void testSetDay() {
		// Test case to ensure the day setter works
		date date = new date(2024, 3, 18);
		date.setDay(18);
		assertEquals(18, date.getDay());
	}

	@Test
	public void testInvalidYear() {
		// Test case to ensure setting an invalid year throws IllegalArgumentException
		date date = new date(2024, 3, 18);
		try {
			date.setYear(-2024);
			fail("Expected IllegalArgumentException was not thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Year must be positive", e.getMessage());
		}
	}

	@Test
	public void testInvalidMonth() {
		// Test case to ensure setting an invalid month throws IllegalArgumentException
		date date = new date(2024, 3, 18);
		try {
			date.setMonth(0);
			fail("Expected IllegalArgumentException was not thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Month must be between 1 and 12", e.getMessage());
		}
	}

	@Test
	public void testInvalidDay() {
		// Test case to ensure setting an invalid day throws IllegalArgumentException
		date date = new date(2022, 3, 15);
		try {
			date.setDay(0); 
			fail("Expected IllegalArgumentException was not thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Day must be between 1 and 31", e.getMessage());
		}
	}

	/* Recommendation JUnit tests*/

	private Recommendation recommendation;

	@Before
	public void setUp() {
		recommendation = new Recommendation();
	}

	@Test
	public void testRecommendBook() {
		// Test ensures system can recommend a book and have it appear in the list of recommended books
		Book book = new Book("Test Book", "Test Author", "Test Publisher", "1234567890", null, "https://test.com");
		recommendation.recommendBook(book);
		assertTrue(recommendation.getRecommendedBooks().contains(book));
	}

	@Test
	public void testIteratorHasNext() {
		// Test ensures that the iterator has a next element when no books are recommended
		assertFalse(recommendation.iterator().hasNext());
	}

	@Test
	public void testIteratorNext() {
		// Test ensures that the system can get the next book from the iterator after recommending a book
		Book book = new Book("Test Book", "Test Author", "Test Publisher", "1234567890", null, "https://test.com");
		recommendation.recommendBook(book);
		assertEquals(book, recommendation.iterator().next());
	}

	@Test
	public void testGetRecommendedBooks() {
		// Test ensures that the list of recommended books is initially empty
		assertTrue(recommendation.getRecommendedBooks().isEmpty());
	}

	@Test
	public void testIteratorWithMultipleBooks() {
		// Test ensures the iterator works properly with multiple books
		Book book1 = new Book("Book 1", "Author 1", "Publisher 1", "ISBN 1", null, "URL 1");
		Book book2 = new Book("Book 2", "Author 2", "Publisher 2", "ISBN 2", null, "URL 2");
		recommendation.recommendBook(book1);
		recommendation.recommendBook(book2);
		java.util.Iterator<iterator.Book> iterator = recommendation.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(book1, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(book2, iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testGetRecommendedBooksAfterRecommendation() {
		// Test ensures the recommended book appears in the list after recommendation
		Book book = new Book("Test Book", "Test Author", "Test Publisher", "1234567890", null, "https://test.com");
		recommendation.recommendBook(book);
		assertTrue(recommendation.getRecommendedBooks().contains(book));
	}

	@Test
	public void testRecommendBooksWithoutRecommendation() {
		// Test ensures the list of recommended books remains empty with no recommendations
		assertTrue(recommendation.getRecommendedBooks().isEmpty());
	}

	@Test
	public void testGetRecommendedBooksAfterClear() {
		// Test ensures the list of recommended books becomes empty after removing recommendations
		Book book = new Book("Test Book", "Test Author", "Test Publisher", "1234567890", null, "https://test.com");
		recommendation.recommendBook(book);
		recommendation.getRecommendedBooks().clear();
		assertTrue(recommendation.getRecommendedBooks().isEmpty());
	}

	@Test
	public void testRecommendBook1() {
		// Test ensures that a single recommended book appears in the list of recommended books
		Book book1 = new Book("Book 1", "Author 1", "Publisher 1", "ISBN 1", null, "URL 1");
		recommendation.recommendBook(book1);
		List<Book> recommendedBooks = recommendation.getRecommendedBooks();
		assertEquals(1, recommendedBooks.size());
		assertTrue(recommendedBooks.contains(book1));
	}

	@Test
	public void testIteratorRemove1() {
		// Test ensures that the system can remove a book from the list of recommended books using iterator
		Book book1 = new Book("Book 1", "Author 1", "Publisher 1", "ISBN 1", null, "URL 1");
		Book book2 = new Book("Book 2", "Author 2", "Publisher 2", "ISBN 2", null, "URL 2");
		recommendation.recommendBook(book1);
		recommendation.recommendBook(book2);
		java.util.Iterator<iterator.Book> iterator = recommendation.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(book1, iterator.next());
		iterator.remove(); 
		List<Book> recommendedBooks = recommendation.getRecommendedBooks();
		assertEquals(1, recommendedBooks.size());
		assertFalse(recommendedBooks.contains(book1));
		assertTrue(recommendedBooks.contains(book2));
	}

	@Test(expected = IllegalStateException.class)
	public void testIteratorRemove() {
		// Test ensures the system can remove an element from the iterator without calling to next() before
		recommendation.iterator().remove();
	}

	/* SearchIterator class JUnit tests*/

	private SearchIterator searchIterator;
	private BookCollection bookCollection;

	@Before
	public void setUp1() {
		bookCollection = new BookCollection();
		searchIterator = new SearchIterator(bookCollection);
	}

	@Test
	public void testNextReturnsNextBook() {
		// Test ensures that next() returns the next book in the collection
		Book book = new Book("Title", "Author", "Publisher", "ISBN", null, "URL");
		bookCollection.addBook(book);
		BookCollection copyOfBookCollection = new BookCollection();
		copyOfBookCollection.getBooks().addAll(bookCollection.getBooks());
		SearchIterator searchIterator = new SearchIterator(copyOfBookCollection);
		assertEquals(book, searchIterator.next());
	}


	@Test
	public void testHasNextReturnsTrueWhenNextBookAvailable() {
		// Test ensures that hasNext() returns true when there is a next book available
		Book book = new Book("Title", "Author", "Publisher", "ISBN", null, "URL");
		bookCollection.addBook(book);
		assertTrue(searchIterator.hasNext());
	}

	@Test
	public void testHasNextReturnsFalseWhenNoNextBookAvailable() {
		// Test ensures that hasNext() returns false when there is no next book available
		assertFalse(searchIterator.hasNext());
	}

	@Test
	public void testNextReturnsNullWhenNoNextBookAvailable() {
		// Test ensures that next() returns null when there is no next book available
		assertNull(searchIterator.next());
	}

	@Test
	public void testNextReturnsNullAfterLastBook() {
		// Test ensures that next() returns null after iterating through all books
		Book book = new Book("Title", "Author", "Publisher", "ISBN", null, "URL");
		bookCollection.addBook(book);
		BookCollection copyOfBookCollection = new BookCollection();
		copyOfBookCollection.getBooks().addAll(bookCollection.getBooks());
		SearchIterator searchIterator = new SearchIterator(copyOfBookCollection);
		searchIterator.next(); 
		assertNull(searchIterator.next()); 
	}


	@Test
	public void testHasNextReturnsFalseAfterLastBook() {
		// Test ensures that hasNext() returns false after iterating through all books
		Book book = new Book("Title", "Author", "Publisher", "ISBN", null, "URL");
		bookCollection.addBook(book);
		BookCollection copyOfBookCollection = new BookCollection();
		copyOfBookCollection.getBooks().addAll(bookCollection.getBooks());
		SearchIterator searchIterator = new SearchIterator(copyOfBookCollection);

		while (searchIterator.hasNext()) {
			searchIterator.next();
		}

		assertFalse(searchIterator.hasNext());
	}

	@Test
	public void testIteratorIteration() {
		// Test ensures that the iterator iterates through all books in the collection
		Book book1 = new Book("Title1", "Author1", "Publisher1", "ISBN1", null, "URL1");
		Book book2 = new Book("Title2", "Author2", "Publisher2", "ISBN2", null, "URL2");
		bookCollection.addBook(book1);
		bookCollection.addBook(book2);
		SearchIterator searchIterator = new SearchIterator(bookCollection);
		assertTrue(searchIterator.hasNext());
		assertEquals(book1, searchIterator.next());
		assertTrue(searchIterator.hasNext());
		assertEquals(book2, searchIterator.next());
		assertFalse(searchIterator.hasNext());
	}

	@Test
	public void testIteratorWithEmptyCollection() {
		// Test ensures that the iterator works properly with an empty collection
		assertFalse(searchIterator.hasNext());
		assertNull(searchIterator.next());
	}

	@Test
	public void testNextReturnsFirstBookWhenCalledMultipleTimes() {
		// Test ensures that calling next() multiple times returns the same first book
		Book book1 = new Book("Title1", "Author1", "Publisher1", "ISBN1", null, "URL1");
		Book book2 = new Book("Title2", "Author2", "Publisher2", "ISBN2", null, "URL2");
		bookCollection.addBook(book1);
		bookCollection.addBook(book2);
		SearchIterator searchIterator = new SearchIterator(bookCollection);
		Book firstBook = searchIterator.next();
		assertEquals(book1.getBookTitle(), firstBook.getBookTitle());
		assertEquals(book1.getBookAuthor(), firstBook.getBookAuthor());
		assertEquals(book1.getBookPublisher(), firstBook.getBookPublisher());
		assertEquals(book1.getISBN(), firstBook.getISBN());
		assertEquals(book1.getDate(), firstBook.getDate());
		assertEquals(book1.getUrl(), firstBook.getUrl());
	}


	@Test
	public void testIteratorRemainsAtFirstBookWhenHasNextCalledMultipleTimes() {
		// Test ensures that calling hasNext() multiple times without calling next() doesn't alter anything
		Book book1 = new Book("Title1", "Author1", "Publisher1", "ISBN1", null, "URL1");
		Book book2 = new Book("Title2", "Author2", "Publisher2", "ISBN2", null, "URL2");
		bookCollection.addBook(book1);
		bookCollection.addBook(book2);
		SearchIterator searchIterator = new SearchIterator(bookCollection);
		assertTrue(searchIterator.hasNext());
		assertTrue(searchIterator.hasNext());
		assertTrue(searchIterator.hasNext());
		assertEquals(book1, searchIterator.next());
	}


	/* MaintainBook class JUnit tests*/

	private MaintainBook maintainBook;
	private String filePath = "Library-Management-Java-App-main/csv files/onlinebooks.csv";

	@Before
	public void setUp2() {
		maintainBook = new MaintainBook();
	}

	@Test
	public void testAddBook() {
		// Test ensures the system can add a single book properly
		Book book = new Book("Title", "Author", "Publisher", "ISBN", new Date(), "URL");
		maintainBook.addBook(book);
		assertEquals(1, maintainBook.getBooks().size());
	}

	@Test
	public void testLoadCSVFile() throws Exception {
		// Test ensures data can be loaded from a CSV
		maintainBook.load(filePath);
		assertTrue(maintainBook.getBooks().size() > 0);
	}

	@Test
	public void testUpdateCSVFile() throws Exception {
		// Test ensures data can be updated into a CSV
		maintainBook.addBook(new Book("Title", "Author", "Publisher", "ISBN", new Date(), "URL"));
		maintainBook.update(filePath);
		assertTrue(maintainBook.getBooks().size() > 0);
	}

	@Test
	public void testAddAndLoadBook() throws Exception {
		// Test ensures the system can add a book, followed by, loading it from a CSV file
		Book book = new Book("Title", "Author", "Publisher", "ISBN", new Date(), "URL");
		maintainBook.addBook(book);
		maintainBook.update(filePath);
		maintainBook.load(filePath);
		assertTrue(maintainBook.getBooks().contains(book));
	}

	@Test
	public void testAddAndUpdateBook() throws Exception {
		// Test ensures that the system can add a book and then update it in the CSV file
		Book book = new Book("Title", "Author", "Publisher", "ISBN", new Date(), "URL");
		maintainBook.addBook(book);
		maintainBook.update(filePath);
		maintainBook.load(filePath);
		Book updatedBook = maintainBook.getBooks().get(0);
		assertEquals(book, updatedBook);
	}

	@Test
	public void testLoadExistingFile() throws Exception {
		// Test ensures the system can load from an existing file
		int initialSize = maintainBook.getBooks().size();
		maintainBook.load(filePath);
		List<Book> books = maintainBook.getBooks();
		assertTrue(books.size() > initialSize);
	}

	@Test
	public void testLoadAndAddBook() throws Exception {
		// Test ensures that the system can load from a CSV and then add a new book
		maintainBook.load(filePath);
		int initialSize = maintainBook.getBooks().size();
		String title = "New Book";
		String ISBN = "1234567890";
		Date date = new Date();
		String author = "John Doe";
		String publisher = "Publisher";
		String url = "https://test.com";
		Book newBook = new Book(title, author, publisher, ISBN, date, url);
		maintainBook.addBook(newBook);
		List<Book> books = maintainBook.getBooks();
		assertEquals(initialSize + 1, books.size());
		assertTrue(books.contains(newBook));
	}


	@Test(expected = Exception.class)
	public void testInvalidDateFormat() throws Exception {
		// Test ensures if the system can or can't load from a CSV file with an invalid date format
		maintainBook.load("Library-Management-Java-App-main/csv files/invalid.csv");
	}

	@Test(expected = Exception.class)
	public void testInvalidCSVData() throws Exception {
		// Test ensures if the system can or can't load from a CSV file with invalid data
		maintainBook.load("Library-Management-Java-App-main/csv files/invalid.csv");
	}

	@Test(expected = Exception.class)
	public void testEmptyCSVFile() throws Exception {
		// Test ensures if the system can or can't load from an empty CSV
		maintainBook.load("empty.csv");
	}

	/* MaintainDate class JUnit tests*/

	private MaintainDate maintainDate;
	private String filePath2 = "Library-Management-Java-App-main/csv files/date.csv";

	@Before
	public void setUp3() {
		maintainDate = new MaintainDate();
	}

	@Test
	public void testAddDate() {
		// Test ensures the system can add a single date
		Date date = new Date();
		maintainDate.add(date);
		assertEquals(1, maintainDate.getDates().size());
	}

	@Test
	public void testLoadCSVFile1() throws Exception {
		// Test ensures the system can load data from a CSV 
		maintainDate.load(filePath2);
		assertTrue(maintainDate.getDates().size() > 0);
	}

	@Test
	public void testUpdateCSVFile1() throws Exception {
		// Test ensures the system can update a CSV
		maintainDate.add(new Date());
		maintainDate.update(filePath2);
		assertTrue(maintainDate.getDates().size() > 0);
	}

	@Test
	public void testAddAndLoadDate() throws Exception {
		// Test ensures the system can add a date and then load it from a CSV 
		Date date = new Date();
		maintainDate.add(date);
		maintainDate.update(filePath2);
		maintainDate.load(filePath2);
		assertTrue(maintainDate.getDates().contains(date));
	}

	@Test
	public void testAddAndUpdateDate() throws Exception {
		// Test ensures the system can add a date and then update it in a CSV 
		Date date = new Date();
		maintainDate.add(date);
		maintainDate.update(filePath2);
		maintainDate.load(filePath2);
		Date updatedDate = maintainDate.getDates().get(0);
		assertEquals(date, updatedDate);
	}

	@Test
	public void testLoadExistingFile1() throws Exception {
		// Test ensures the system can load from an existing file
		int initialSize = maintainDate.getDates().size();
		maintainDate.load(filePath2);
		assertTrue(maintainDate.getDates().size() > initialSize);
	}

	@Test(expected = FileNotFoundException.class)
	public void testLoadNonExistentFile() throws Exception {
		// Test ensures the system can load from a non-existent file
		maintainDate.load("Library-Management-Java-App-main/csv files/nonexistent.csv");
	}

	@Test(expected = Exception.class)
	public void testEmptyCSVFile1() throws Exception {
		// Test ensures that the system can load from an empty CSV file
		maintainDate.load("empty.csv");
	}

	@Test
	public void testAddFutureDate() {
		// Test ensures the retrieval of the list of dates and checks if it contains the future date
		Date futureDate = new Date(System.currentTimeMillis() + 10000);
		maintainDate.add(futureDate);
		ArrayList<Date> dates = maintainDate.getDates();
		assertTrue("List of dates should contain the future date", dates.contains(futureDate));
	}

	@Test
	public void testLoadCSVFileWithMultipleDates() throws Exception {
		// Test ensures that the loaded dates list contains the expected additional dates
		File csvFile = new File(filePath2);
		CsvWriter writer = new CsvWriter(new FileWriter(csvFile), ',');
		writer.write("date");
		writer.endRecord();
		writer.write("2024-03-30");
		writer.endRecord();
		writer.write("2024-03-31");
		writer.endRecord();
		writer.close();

		int initialSize = maintainDate.getDates().size();
		maintainDate.load(filePath2);
		ArrayList<Date> loadedDates = maintainDate.getDates();
		assertEquals("Loaded dates list should contain two additional dates", initialSize + 2, loadedDates.size());
	}

}

