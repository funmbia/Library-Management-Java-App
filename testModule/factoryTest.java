package testModule;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import factory.*;

class factoryTest {

/* LibraryItem.java */
	private LibraryItem item;
	
	void setUpLibraryItem() {
		LibraryItem.resetIncrement();
		item = new OnlineBook();
	}
	
	@Test
	void testLibraryItemCreation() {
		setUpLibraryItem();
		assertTrue(item instanceof LibraryItem);
	}
	
	@Test
	void testLibraryItemCreation2() {
		setUpLibraryItem();
		assertFalse(item instanceof PhysicalItem);
	}
	
	@Test
	void testLibraryItemIDCreation() {
		setUpLibraryItem();
		assertEquals(item.getID(), 1);
	} 
	
	@Test
	void testLibraryItemIDCreation2() {
		setUpLibraryItem();
		assertTrue(item.getID() == 1);
	} 
	
	@Test
	void setLibraryItemTitle() {
		setUpLibraryItem();
		assertEquals(item.getTitle(), null);
	}
	
	@Test
	void setLibraryItemTitle2() {
		setUpLibraryItem();
		assertEquals(item.getTitle(), null);
	}
	
	@Test
	void testLibraryItemToString() {
		setUpLibraryItem();
		assertNotNull(item.toString());
	}
	
	@Test
	void testLibraryItemToString2() {
		setUpLibraryItem();
		assertNotNull(item.toString()); 
	}
	
	@Test
	void otherTestsForLibraryItem() {
		setUpLibraryItem();
		assertFalse(item instanceof Newsletter);
	}
	
	@Test
	void otherTestsForLibraryItem2() {
		setUpLibraryItem();
		assertFalse(item instanceof Magazine);
	}
/* LibraryItem.java */

	
	
/* PhysicalItem.java */
	private PhysicalItem item2;
	
	void setUpPhysicalItem() {
		LibraryItem.resetIncrement();
		item2 = new HardcoverBook();
	}
	
	 @Test
	 void testPhysicalItemConstructor() {
	        setUpPhysicalItem();
	        assertEquals(item2.getID(), 1);
	        assertFalse(item2.getRentable());
	        assertFalse(item2.getPurchaseable());
	        assertEquals(20, item2.getCopiesAvail());
	        assertNull(item2.getLocation());
	        assertEquals(0.0, item2.getPrice());
	        assertNull(item2.getTitle());
	        assertNull(item2.getDueDate());
	        assertTrue(item2.isAvailable());
	  }
	 
	 @Test
	 void testPhysicalItemSetCopiesAvail() {
	        setUpPhysicalItem();
	        item2.setCopiesAvail(15);
	        assertEquals(15, item2.getCopiesAvail());
	    }

	    @Test
	    void testPhysicalItemDecreaseAvailableCopies() {
	        setUpPhysicalItem();
	        item2.decreaseAvailableCopies();
	        assertEquals(19, item2.getCopiesAvail());
	    }
	    
	    @Test
	    void testPhysicalItemDecreaseAvailableCopies2() {
	        setUpPhysicalItem();
	        for (int i=20; i>0; i--) {
	        	item2.decreaseAvailableCopies();
	        }
	        assertFalse(item2.getRentable());
	    }

	    @Test
	     void testPhysicalItemIncreaseAvailableCopies() {
	        setUpPhysicalItem();
	        item2.increaseAvailableCopies();
	        assertEquals(21, item2.getCopiesAvail());
	    }

	    @Test
	     void testPhysicalItemSetLocation() {
	        setUpPhysicalItem();
	        item2.setLocation("Library");
	        assertEquals("Library", item2.getLocation());
	    }

	    @Test
	     void testPhysicalItemSetPrice() {
	        setUpPhysicalItem();
	        item2.setPrice(10.99);
	        assertEquals(10.99, item2.getPrice());
	    }

	    @Test
	     void testPhysicalItemSetRentable() {
	        setUpPhysicalItem();
	        item2.setRentable(false);
	        assertFalse(item2.getRentable());
	    }

	    @Test
	     void testPhysicalItemSetPurchaseable() {
	        setUpPhysicalItem();
	        item2.setPurchaseable(true);
	        assertTrue(item2.getPurchaseable());
	    }

	    @Test
	     void testPhysicalItemSetRentalStatus() {
	        setUpPhysicalItem();
	        item2.setRentalStatus(false);
	        assertFalse(item2.isAvailable());
	    }

	    @Test
	     void testPhysicalItemSetTitle() {
	        setUpPhysicalItem();
	        item2.setTitle("Praise");
	        assertEquals("Praise", item2.getTitle());
	    }
	    
	    @Test
	     void testPhysicalItemIsOverdue() {
	    	setUpPhysicalItem();
	        Date dueDate = new Date(System.currentTimeMillis() + 1000);
	        assertFalse(item2.isOverdue(dueDate));
	    }

	    @Test
	     void testPhysicalItemIsOverdue2() {
	    	setUpPhysicalItem();
	        Date dueDate = new Date(System.currentTimeMillis() - 1000);
	        assertTrue(item2.isOverdue(dueDate));
	    }
	    
	    @Test
	     void testPhysicalItemIsOverdue3() {
	    	setUpPhysicalItem();
	        assertFalse(item2.isOverdue(null));
	    }
/* PhysicalItem.java */
	
	
/* OnlineBook.java */
	    private OnlineBook item3;
		
		void setUpOnlineBook() {
			item3 = new OnlineBook();
		}
		
		@Test
	     void testOnlineBookSecondConstructor() {
			item3 = new OnlineBook(false, true);
			assertFalse(item3.isAvailable());
			assertTrue(item3.isSubscribed());
		}
		
		@Test
	     void testOnlineBookSetAttributes() {
	        setUpOnlineBook();
	        item3.setAttributes("Book", "Author", "Publisher", "http://example.com", "1234567890");
	        assertEquals("Book", item3.getTitle());
	        assertEquals("Author", item3.getAuthor());
	        assertEquals("Publisher", item3.getPublisher());
	        assertEquals("http://example.com", item3.getURL());
	        assertEquals("1234567890", item3.getISBN());
	    }

	    @Test
	     void testOnlineBookSetAvailable() {
	        setUpOnlineBook();
	        item3.setAvailable(true);
	        assertTrue(item3.isAvailable());
	    }

	    @Test
	     void testOnlineBookSetSubscribed() {
	        setUpOnlineBook();
	        item3.setSubscribed(true);
	        assertTrue(item3.isSubscribed());
	    }

	    @Test
	    void testOnlineBookToString() {
	        setUpOnlineBook();
	        item3.setAttributes("Book", "Author", "Publisher", "http://somewhere.com", "1234567890");
	        String expected = "ONLINE BOOK SUMMARY:\n\tTitle: Book, Author: Author, Publisher: Publisher, \n\tURL: http://somewhere.com, ISBN: 1234567890";
	        assertEquals(expected, item3.toString());
	    }
	    
	    @Test
	    void testOnlineBookToString2() {
	        setUpOnlineBook();
	        item3.setAttributes("Book", null, null, null, null);
	        String expected = "ONLINE BOOK SUMMARY:\n\tTitle: Book, Author: null, Publisher: null, \n\tURL: null, ISBN: null";
	        assertEquals(expected, item3.toString());
	    }
	    
	    @Test
	    void testOtherOnlineBookFeatures() {
	        setUpOnlineBook();
	        assertTrue(item3 instanceof LibraryItem);
	    }
	    @Test
	    void testOtherOnlineBookFeatures2() {
	        setUpOnlineBook();
	        assertTrue(item3 instanceof OnlineBook);
	    }
	    @Test
	    void testOtherOnlineBookFeatures3() {
	        setUpOnlineBook();
	        assertTrue(item3.getID() > 0);
	    }
	    @Test
	    void testInheritedOnlineBookFeatures() {
	        setUpOnlineBook();
	        item3.setTitle("Yellow");
	        assertEquals(item3.getTitle(), "Yellow");
	    }	    	    
/* OnlineBook.java */
	
	
/* Newsletter.java */
	    private Newsletter item4;
		
		void setUpNewsletter() {
			LibraryItem.resetIncrement();
			item4 = new Newsletter();
		}
		
		@Test
	     void testNewsletterSecondConstructor() {
			item4 = new Newsletter(true);
			assertTrue(item4.isSubscribed());
		}
		
		@Test
	     void testNewsletterSetAttributes() {
	        setUpNewsletter();
	        item4.setAttributes("NYTimes", "NY", "http://nytimes.com");
	        assertEquals("NYTimes", item4.getTitle());
	        assertEquals("NY", item4.getOrganization());
	        assertEquals("http://nytimes.com", item4.getURL());
	    }
		
		@Test
	     void testNewsletterSetSubscribed() {
	        setUpNewsletter();
	        item4.setSubscribed(false);
	        assertFalse(item4.isSubscribed());
	        assertFalse(item4.isAvailable());
	    }
		
		@Test
	     void testNewsletterSetName() {
	        setUpNewsletter();
	        item4.setName("Wade in the Water");
	        assertEquals("Wade in the Water", item4.getName());
	    }
		
	    @Test
	    void tesNewsletterToString() {
	        setUpNewsletter();
	        item4.setAttributes("NYTimes", "NY", "http://nytimes.com");
	        String expected = "NEWSLETTER SUMMARY:\n\tTitle: NYTimes, Organization: NY, URL: http://nytimes.com";
	        assertEquals(expected, item4.toString());
	    }
	    
	    @Test
	    void tesNewsletterToString2() {
	        setUpNewsletter();
	        item4.setAttributes(null, null, null);
	        String expected = "NEWSLETTER SUMMARY:\n\tTitle: null, Organization: null, URL: null";
	        assertEquals(expected, item4.toString());
	    }
	    
	    @Test
	    void testOtherNewsletterFeatures() {
	        setUpNewsletter();
	        assertTrue(item4 instanceof LibraryItem);
	    }
	    @Test
	    void testOtherNewsletterFeatures2() {
	    	setUpNewsletter();
	        assertTrue(item4 instanceof Newsletter);
	    }
	    @Test
	    void testOtherNewsletterFeatures3() {
	    	setUpNewsletter();
	    	assertEquals(item4.getID(), 1);
	    }
	    
	    @Test
	    void testOtherNewsletterFeatures4() {
	    	setUpNewsletter();
	    	assertEquals(item4.getID(), 1);
	    }
/* Newsletter.java */
	    
	    
	    
/* HardcoverBook.java */
	    private HardcoverBook item5;
	    
	    void setUpHardCoverBook() {
	    	LibraryItem.resetIncrement();
	    	item5 = new HardcoverBook();
	    }
	    
	    @Test
	     void testHardcoverBookSDefaultConstructor() {
	    	setUpHardCoverBook();
	        assertEquals(1, item5.getID());
	        assertNull(item5.getAuthor());
	        assertNull(item5.getPublisher());
	        assertNull(item5.getISBN());
	        assertNull(item5.getTitle());
	        assertFalse(item5.isRented());
	        assertFalse(item5.getRentable());
	        assertFalse(item5.getPurchaseable());
	        assertEquals(0.0, item5.getPrice());
	        assertEquals(20, item5.getCopiesAvail());
	    }

	    @Test
	     void testHardcoverBookSetAttributes() {
	    	setUpHardCoverBook();
	        item5.setAttributes("Book Title", "Author", "Publisher", "1234567890", "Floor 2, Shelf 4", true, false, 10.99);
	        assertEquals("Book Title", item5.getTitle());
	        assertEquals("Author", item5.getAuthor());
	        assertEquals("Publisher", item5.getPublisher());
	        assertEquals("1234567890", item5.getISBN());
	        assertEquals("Floor 2, Shelf 4", item5.getLocation());
	        assertTrue(item5.getRentable());
	        assertFalse(item5.getPurchaseable());
	        assertEquals(10.99, item5.getPrice());
	    }

	    @Test
	     void testIsHardcover() {
	    	setUpHardCoverBook();
	        assertTrue(item5.isHardcover());
	    }

	    @Test
	     void testHardcoverBookSSetRented() {
	    	setUpHardCoverBook();
	        item5.setRented(true);
	        assertTrue(item5.isRented());
	    }

	    @Test
	     void testHardcoverBookSToString() {
	    	setUpHardCoverBook();
	        item5.setAttributes("Book Title", "Author", "Publisher", "1234567890", "Floor 2, Shelf 4", true, false, 10.99);
	        String expected = "HARDCOVER BOOK SUMMARY:\n\tTitle: Book Title, Author: Author, Publisher: Publisher, ISBN: 1234567890.\n\tLocation: Floor 2, Shelf 4. This book is currently rentable ; not purchaseable ; and there are 20 copies available.";
	        assertEquals(expected, item5.toString());
	    }
	    
	    @Test
	     void testHardcoverBookSToString2() {
	    	setUpHardCoverBook();
	        item5.setAttributes("Book Title", "Author", "Publisher", "1234567890", "Floor 2, Shelf 4", false, true, 10.99);
	        String expected = "HARDCOVER BOOK SUMMARY:\n\tTitle: Book Title, Author: Author, Publisher: Publisher, ISBN: 1234567890.\n\tLocation: Floor 2, Shelf 4. This book is currently not rentable ; purchaseable for $10.99 ; and there are 20 copies available.";
	        assertEquals(expected, item5.toString());
	    }

	    @Test
	     void testHardcoverBookSIsOverdueWithNullDueDate() {
	    	setUpHardCoverBook();
	        assertFalse(item5.isOverdue(null));
	    }

	    @Test
	     void testHardcoverBookSIsOverdueWithFutureDueDate() {
	    	setUpHardCoverBook();
	        Date dueDate = new Date(System.currentTimeMillis() + 1000);
	        assertFalse(item5.isOverdue(dueDate));
	    }

	    @Test
	     void testHardcoverBookSIsOverdueWithPastDueDate() {
	    	setUpHardCoverBook();
	        Date dueDate = new Date(System.currentTimeMillis() - 1000);
	        assertTrue(item5.isOverdue(dueDate));
	    }
	    
	    @Test
	    void testOtherHardcoverBookFeatures() {
	    	setUpHardCoverBook();
	    	assertTrue(item5 instanceof LibraryItem);
	    	assertTrue(item5 instanceof PhysicalItem);
	    }
	    
	    @Test
	    void testOtherHardcoverBookFeatures2() {
	    	setUpHardCoverBook();
	    	assertTrue(item5 instanceof HardcoverBook);
	    }
/* HardcoverBook.java */
	    
	    
	    
/* Magazine.java */
	    private Magazine item6;
	    
	    void setUpMagazine() {
	        LibraryItem.resetIncrement();
	        item6 = new Magazine();
	    }

	    @Test
	     void testMagazineDefaultConstructor() {
	    	setUpMagazine();
	        assertEquals(1, item6.getID());
	        assertNull(item6.getTitle());
	        assertNull(item6.getPublisher());
	        assertFalse(item6.getRentable());
	        assertFalse(item6.getPurchaseable());
	        assertEquals(0.0, item6.getPrice());
	        assertEquals(20, item6.getCopiesAvail());
	    }

	    @Test
	     void testMagazineSetAttributes() {
	    	setUpMagazine();
	        item6.setAttributes("Magazine Title", "Publisher", "Floor 2, Shelf 4", true, false, 5.99);
	        assertEquals("Magazine Title", item6.getTitle());
	        assertEquals("Publisher", item6.getPublisher());
	        assertEquals("Floor 2, Shelf 4", item6.getLocation());
	        assertTrue(item6.getRentable());
	        assertFalse(item6.getPurchaseable());
	        assertEquals(5.99, item6.getPrice());
	    }

	    @Test
	     void testMagazineToString() {
	    	setUpMagazine();
	        item6.setAttributes("Magazine Title", "Publisher", "Floor 2, Shelf 4", true, false, 5.99);
	        String expected = "MAGAZINE SUMMARY:\n\tTitle: Magazine Title, Publisher: Publisher, Location: Floor 2, Shelf 4.\n\tThis magazine is currently rentable ; not purchaseable ; and there are 20 copies available.";
	        assertEquals(expected, item6.toString());
	    }
	    
	    @Test
	     void testMagazineToString2() {
	    	setUpMagazine();
	        item6.setAttributes("Magazine Title", "Publisher", "Floor 2, Shelf 4", false, true, 5.99);
	        String expected = "MAGAZINE SUMMARY:\n\tTitle: Magazine Title, Publisher: Publisher, Location: Floor 2, Shelf 4.\n\tThis magazine is currently not rentable ; purchaseable for $5.99 ; and there are 20 copies available.";
	        assertEquals(expected, item6.toString());
	    }

	    @Test
	     void testMagazineIsOverdue() {
	    	setUpMagazine();
	        assertFalse(item6.isOverdue(null));
	    }

	    @Test
	     void testMagazineIsOverdue2() {
	    	setUpMagazine();
	        Date dueDate = new Date(System.currentTimeMillis() + 1000);
	        assertFalse(item6.isOverdue(dueDate));
	    }

	    @Test
	     void testMagazineIsOverdue3() {
	    	setUpMagazine();
	        Date dueDate = new Date(System.currentTimeMillis() - 1000);
	        assertTrue(item6.isOverdue(dueDate));
	    }

	    @Test
	     void testMagazineDecreaseAvailableCopies() {
	    	setUpMagazine();
	        item6.decreaseAvailableCopies();
	        assertEquals(19, item6.getCopiesAvail());
	    }

	    @Test
	     void testMagazineIncreaseAvailableCopies() {
	    	setUpMagazine();
	        item6.increaseAvailableCopies();
	        assertEquals(21, item6.getCopiesAvail());
	    }
	    
	    @Test
	    void testOtherMagazineFeatures() {
	    	setUpMagazine();
	    	assertTrue(item6 instanceof LibraryItem);
	    	assertTrue(item6 instanceof PhysicalItem);
	    }
	    
	    @Test
	    void testOtherMagazineFeatures2() {
	    	setUpMagazine();
	    	assertTrue(item6 instanceof Magazine);
	    }
/* Magazine.java */
	    
	    
	    
/* CD.java */
	    private CD item7;
	    
	    void setUpCD() {
	        LibraryItem.resetIncrement();
	        item7 = new CD();
	    }
	    
	    @Test
	    public void testCDDefaultConstructor() {
	    	setUpCD();
	        assertEquals(1, item7.getID());
	        assertNull(item7.getTitle());
	        assertNull(item7.getLocation());
	        assertFalse(item7.getRentable());
	        assertFalse(item7.getPurchaseable());
	        assertEquals(0.0, item7.getPrice());
	        assertEquals(20, item7.getCopiesAvail());
	    }

	    @Test
	    public void testCDSetAttributes() {
	    	setUpCD();
	        item7.setAttributes("CD Title", "Floor 2, Shelf 4", true, false, 9.99);
	        assertEquals("CD Title", item7.getTitle());
	        assertEquals("Floor 2, Shelf 4", item7.getLocation());
	        assertTrue(item7.getRentable());
	        assertFalse(item7.getPurchaseable());
	        assertEquals(9.99, item7.getPrice());
	    }

	    @Test
	    public void testCDToString() {
	    	setUpCD();
	        item7.setAttributes("CD Title", "Floor 2, Shelf 4", true, false, 9.99);
	        String expected = "CD SUMMARY:\n\tTitle: CD Title, Location: Floor 2, Shelf 4.\n\tThis CD is currently rentable ; not purchaseable ; and there are 20 copies available.";
	        assertEquals(expected, item7.toString());
	    }
	    
	    @Test
	    public void testCDToString2() {
	    	setUpCD();
	        item7.setAttributes("CD Title", "Floor 2, Shelf 4", false, true, 9.99);
	        String expected = "CD SUMMARY:\n\tTitle: CD Title, Location: Floor 2, Shelf 4.\n\tThis CD is currently not rentable ; purchaseable for $9.99 ; and there are 20 copies available.";
	        assertEquals(expected, item7.toString());
	    }

	    @Test
	    public void testCDIsOverdue() {
	    	setUpCD();
	        assertFalse(item7.isOverdue(null));
	    }

	    @Test
	    public void testCDIsOverdue2() {
	    	setUpCD();
	        Date dueDate = new Date(System.currentTimeMillis() + 1000);
	        assertFalse(item7.isOverdue(dueDate));
	    }

	    @Test
	    public void testCDIsOverdue3() {
	    	setUpCD();
	        Date dueDate = new Date(System.currentTimeMillis() - 1000);
	        assertTrue(item7.isOverdue(dueDate));
	    }

	    @Test
	    public void testCDDecreaseAvailableCopies() {
	    	setUpCD();
	        item7.decreaseAvailableCopies();
	        assertEquals(19, item7.getCopiesAvail());
	    }

	    @Test
	    public void testCDIncreaseAvailableCopies() {
	    	setUpCD();
	        item7.increaseAvailableCopies();
	        assertEquals(21, item7.getCopiesAvail());
	    }
	    
	    @Test
	    void testOtherCDFeatures() {
	    	setUpCD();
	    	assertTrue(item7 instanceof LibraryItem);
	    	assertTrue(item7 instanceof LibraryItem);
	    }
	    
	    @Test
	    void testOtherCDFeatures2() {
	    	setUpCD();
	    	assertTrue(item7 instanceof CD);
	    }
/* CD.java */
	    
	
	    
/* MaintainOnlineBook.java */
	    private MaintainOnlineBook maintain;
	    private String onlinebookpath =  "src/csv files/onlinebooks.csv";
	    
	    void setUpMaintainOnlineBook() {
	    	LibraryItem.resetIncrement();
	    	maintain = new MaintainOnlineBook();
	    }
	    
	    @Test
	    void testLoadOnlineBookException() throws Exception {
	    	setUpMaintainOnlineBook();
	    	maintain.load(onlinebookpath);
	    	assertNotNull(maintain.onlinebooks.get(0));
	    }	   	  
	    
	    @Test
	    void testLoadOnlineBook() throws Exception {
	    	setUpMaintainOnlineBook();
	    	maintain.load(onlinebookpath);
	    	ArrayList<OnlineBook> books = maintain.onlinebooks;
	    	assertFalse(maintain.onlinebooks.isEmpty());
	    }
	    
	    @Test
	    void testLoadOlineBook2() throws Exception{
	    	setUpMaintainOnlineBook();
	    	maintain.load(onlinebookpath);
	    	OnlineBook book = maintain.onlinebooks.get(0);
	    	assertEquals("The Little Prince", book.getTitle());
	    	assertEquals("Antoine de Saint-Exupery", book.getAuthor());
	    	assertEquals("UBC Blogs", book.getPublisher());
	    	assertEquals(1, book.getID());
	    	assertEquals("978-3159621791", book.getISBN());
	    	assertEquals("https://blogs.ubc.ca/edcp508/files/2016/02/TheLittlePrince.pdf", book.getURL());	    	
	    }
	    
	    @Test
	    void testLoadOlineBook3() throws Exception{
	    	setUpMaintainOnlineBook();
	    	maintain.load(onlinebookpath);
	    	OnlineBook book = maintain.onlinebooks.get(2);
	    	assertEquals("Dear Martin", book.getTitle());
	    	assertEquals("Nic Stone", book.getAuthor());
	    	assertEquals("Crown Publishing Group", book.getPublisher());
	    	assertEquals(3, book.getID());
	    	assertEquals("978-1471175572", book.getISBN());
	    	assertEquals("https://iblog.dearbornschools.org/eifert/wp-content/uploads/sites/2973/2020/06/Dear-Martin-by-Nic-Stone.pdf", book.getURL());	    	
	    }
	    
	    @Test
	    void testEmptyUpdateOnlineBook() throws Exception {
	    	setUpMaintainOnlineBook();
	    	maintain.load(onlinebookpath);
	    	int initialSize = maintain.onlinebooks.size();
	    	maintain.update(onlinebookpath);
	    	assertEquals(initialSize, maintain.onlinebooks.size());
	    }
	    
	    @Test
	    void testUpdateOnlineBook() throws Exception {
	    	setUpMaintainOnlineBook();
	    	maintain.load(onlinebookpath);
	    	ArrayList<OnlineBook> books = maintain.onlinebooks;
	    	int initialSize = books.size();
	    	maintain.onlinebooks.add(new OnlineBook());
	    	maintain.update(onlinebookpath);
	    	assertTrue(maintain.onlinebooks.size() == initialSize+1);
	    }
	    
	    @Test
	    void testUpdateOlineBookUpdate1() throws Exception{
	    	setUpMaintainOnlineBook();
	    	maintain.load(onlinebookpath);
	    	maintain.onlinebooks.add(new OnlineBook());
	    	maintain.update(onlinebookpath);
	    	
	    	maintain.load(onlinebookpath);
	    	OnlineBook book = maintain.onlinebooks.get(maintain.onlinebooks.size()-1);
	    	assertEquals("", book.getTitle());
	    	assertEquals("", book.getAuthor());
	    	assertEquals("", book.getPublisher());
	    	assertTrue(book.getID() > 1);
	    	assertEquals("", book.getISBN());
	    	assertEquals("", book.getURL());		    	
	    }
	    
	    @Test
	    void testUpdateOlineBookUpdate2() throws Exception{
	    	setUpMaintainOnlineBook();
	    	maintain.load(onlinebookpath);
	    	ArrayList<OnlineBook> books = maintain.onlinebooks;
	    	OnlineBook newBook = new OnlineBook();
	    	newBook.setAttributes("Sing", "Some Author", "Disney", "https:/disney.com", "98429739751278873833");
	    	books.add(newBook);
	    	maintain.update(onlinebookpath);
	    	
	    	maintain.load(onlinebookpath);
	    	OnlineBook book = maintain.onlinebooks.get(maintain.onlinebooks.size()-1);
	    	assertEquals("Sing", book.getTitle());
	    	assertEquals("Some Author", book.getAuthor());
	    	assertEquals("Disney", book.getPublisher());
	    	assertTrue(book.getID() > 1);
	    	assertEquals("98429739751278873833", book.getISBN());
	    	assertEquals("https:/disney.com", book.getURL());		    	
	    }
	    
	    @Test
	    void testLoadIOException() {
	        try {
	        	MaintainOnlineBook b = new MaintainOnlineBook();
	            b.load("nonexistent.csv");
	            fail("Expected an IOException to be thrown");
	        } catch (Exception e) {
	            assertTrue(e instanceof IOException);
	        }
	    }
	    
	    @Test
	    void testUpdateIOException() {
	        try {
	        	MaintainOnlineBook b = new MaintainOnlineBook();
	            b.load("nonexistent.csv");
	            b.update(onlinebookpath);
	            fail("Expected an IOException to be thrown");
	        } catch (Exception e) {
	            assertTrue(e instanceof IOException);
	        }
	    }
/* MaintainOnlineBook.java */
	    
	    
	    
/* MaintainNewsletter.java */
	    private MaintainNewsletter maintain2;
	    private String newsletterpath =  "src/csv files/newsletters.csv";
	    
	    void setUpMaintainNewsletter() {
	    	LibraryItem.resetIncrement();
	    	maintain2 = new MaintainNewsletter();
	    }
	    
	    @Test
	    void testLoadNewsletter4() throws Exception {
	    	setUpMaintainNewsletter();
	    	maintain2.load(newsletterpath);
	    	assertNotNull(maintain2.newsletters.get(0));
	    }	   	  
	    
	    @Test
	    void testLoadNewsletter() throws Exception {
	    	setUpMaintainNewsletter();
	    	maintain2.load(newsletterpath);
	    	assertFalse(maintain2.newsletters.isEmpty());
	    }
	    
	    @Test
	    void testLoadNewsletter2() throws Exception{
	    	setUpMaintainNewsletter();
	    	maintain2.load(newsletterpath);
	    	Newsletter letter = maintain2.newsletters.get(0);
	    	assertEquals("New York Times", letter.getTitle());
	    	assertEquals("The New York Times Company", letter.getOrganization());	    	
	    	assertEquals("https://www.nytimes.com/ca/", letter.getURL());	  
	    	assertEquals(1, letter.getID());
	    }
	    
	    @Test
	    void testLoadNewsletter3() throws Exception{
	    	setUpMaintainNewsletter();
	    	maintain2.load(newsletterpath);
	    	Newsletter letter = maintain2.newsletters.get(1);
	    	assertEquals("Yfile", letter.getTitle());
	    	assertEquals("York University", letter.getOrganization());	    	
	    	assertEquals("https://yfile.news.yorku.ca/", letter.getURL());	  
	    	assertEquals(2, letter.getID());
	    }
	    
	    @Test
	    void testEmptyUpdateNewsletter() throws Exception {
	    	setUpMaintainNewsletter();
	    	maintain2.load(newsletterpath);
	    	int initialSize = maintain2.newsletters.size();
	    	maintain2.update(newsletterpath);
	    	assertEquals(initialSize, maintain2.newsletters.size());
	    }
	    
	    @Test
	    void testUpdateNewsletter() throws Exception {
	    	setUpMaintainNewsletter();
	    	maintain2.load(newsletterpath);
	    	ArrayList<Newsletter> letters = maintain2.newsletters;
	    	int initialSize = letters.size();
	    	letters.add(new Newsletter());
	    	maintain2.update(newsletterpath);
	    	assertTrue(maintain2.newsletters.size() == initialSize+1);
	    }
	    
	    @Test
	    void testUpdateNewsletterUpdate1() throws Exception{
	    	setUpMaintainNewsletter();
	    	maintain2.load(newsletterpath);
	    	ArrayList<Newsletter> letters = maintain2.newsletters;
	    	letters.add(new Newsletter());
	    	maintain2.update(newsletterpath);
	    	
	    	maintain2.load(newsletterpath);
	    	Newsletter letter = maintain2.newsletters.get(maintain2.newsletters.size()-1);
	    	assertEquals("", letter.getTitle());
	    	assertEquals("", letter.getOrganization());	    	
	    	assertEquals("", letter.getURL());	 
	    	assertTrue(letter.getID() > 1);
	    			    	
	    }
	    
	    @Test
	    void testUpdateNewsletterUpdate2() throws Exception{
	    	setUpMaintainNewsletter();
	    	maintain2.load(newsletterpath);
	    	ArrayList<Newsletter> books = maintain2.newsletters;
	    	Newsletter newLetter = new Newsletter();
	    	newLetter.setAttributes("Sing", "Disney", "https:/disney.com");
	    	books.add(newLetter);
	    	maintain2.update(newsletterpath);
	    	
	    	maintain2.load(newsletterpath);
	    	Newsletter a = maintain2.newsletters.get(maintain2.newsletters.size()-1);
	    	assertEquals("Sing", a.getTitle());
	    	assertEquals("Disney", a.getOrganization());
	    	assertEquals("https:/disney.com", a.getURL());
	    	assertTrue(a.getID() > 1);
	    			    	
	    }
	    
	    @Test
	    void testLoadIOException2() {
	        try {
	        	MaintainNewsletter b = new MaintainNewsletter();
	            b.load("nonexistent.csv");
	            fail("Expected an IOException to be thrown");
	        } catch (Exception e) {
	            assertTrue(e instanceof IOException);
	        }
	    }
	    
	    @Test
	    void testUpdateIOException2() {
	        try {
	        	MaintainNewsletter b = new MaintainNewsletter();
	            b.load("nonexistent.csv");
	            b.update(newsletterpath);
	            fail("Expected an IOException to be thrown");
	        } catch (Exception e) {
	            assertTrue(e instanceof IOException);
	        }
	    }
/* MaintainNewsletter.java */
	    
	    
/* MaintainMagazine.java */
	    private MaintainMagazine maintain3;
	    private String magazinepath =  "src/csv files/magazines.csv";
	    
	    void setUpMaintainMagazine() {
	    	LibraryItem.resetIncrement();
	    	maintain3 = new MaintainMagazine();
	    }
	    
	    @Test
	    void testLoadMagazineException() throws Exception {
	    	setUpMaintainMagazine();
	    	maintain3.load(magazinepath);
	    	assertNotNull(maintain3.magazines.get(0));
	    }	   	  
	    
	    @Test
	    void testLoadMagazine() throws Exception {
	    	setUpMaintainMagazine();
	    	maintain3.load(magazinepath);
	    	assertFalse(maintain3.magazines.isEmpty());
	    }
	    
	    @Test
	    void testLoadMagazine2() throws Exception{
	    	setUpMaintainMagazine();
	    	maintain3.load(magazinepath);
	    	Magazine a = maintain3.magazines.get(0);
	    	assertEquals("New York Times Magazine", a.getTitle());
	    	assertEquals("The New York Times Company", a.getPublisher());
	    	assertEquals("1st floor; Shelf A1", a.getLocation());	
	    	assertEquals(false, a.getRentable());	
	    	assertEquals(true, a.getPurchaseable());    	
	    	assertEquals(8.85, a.getPrice());
	    	assertEquals(20, a.getCopiesAvail());
	    }
	    
	    @Test
	    void testLoadMagazine3() throws Exception{
	    	setUpMaintainMagazine();
	    	maintain3.load(magazinepath);
	    	Magazine a = maintain3.magazines.get(0);
	    	assertEquals("MAGAZINE SUMMARY:\n"
	    			+ "	Title: New York Times Magazine, Publisher: The New York Times Company, Location: 1st floor; Shelf A1.\n"
	    			+ "	This magazine is currently not rentable ; purchaseable for $8.85 ; and there are 20 copies available.", a.toString());
	    }
	    
	    @Test
	    void testEmptyUpdateMagazine() throws Exception {
	    	setUpMaintainMagazine();
	    	maintain3.load(magazinepath);
	    	int initialSize = maintain3.magazines.size();
	    	maintain3.update(magazinepath);
	    	assertEquals(initialSize, maintain3.magazines.size());
	    }
	    
	    @Test
	    void testUpdateMagazine() throws Exception {
	    	setUpMaintainMagazine();
	    	maintain3.load(magazinepath);
	    	ArrayList<Magazine> a = maintain3.magazines;
	    	int initialSize = a.size();
	    	a.add(new Magazine());
	    	maintain3.update(magazinepath);
	    	assertTrue(maintain3.magazines.size() == initialSize+1);
	    }
	    
	    @Test
	    void testUpdateMagazineUpdate1() throws Exception{
	    	setUpMaintainMagazine();
	    	maintain3.load(magazinepath);
	    	ArrayList<Magazine> a = maintain3.magazines;
	    	a.add(new Magazine());
	    	maintain3.update(magazinepath);
	    	
	    	maintain3.load(magazinepath);
	    	Magazine b = maintain3.magazines.get(maintain3.magazines.size()-1);
	    	assertEquals("", b.getTitle());
	    	assertEquals("", b.getPublisher());
	    	assertEquals("", b.getLocation());	
	    	assertEquals(false, b.getRentable());	
	    	assertEquals(false, b.getPurchaseable());    	
	    	assertEquals(0.0, b.getPrice());
	    	assertEquals(20, b.getCopiesAvail());	    			    	
	    }
	    
	    @Test
	    void testUpdateMagazineUpdate2() throws Exception{
	    	setUpMaintainMagazine();
	    	maintain3.load(magazinepath);
	    	ArrayList<Magazine> zines = maintain3.magazines;
	    	Magazine a = new Magazine();
	    	a.setAttributes("Sing", "Disney", "", false, false, 0);
	    	zines.add(a);
	    	maintain3.update(magazinepath);
	    	
	    	maintain3.load(magazinepath);
	    	Magazine b = maintain3.magazines.get(maintain3.magazines.size()-1);
	    	assertEquals("Sing", b.getTitle());
	    	assertEquals("Disney", b.getPublisher());
	    	assertEquals("", b.getLocation());	
	    	assertEquals(false, b.getRentable());	
	    	assertEquals(false, b.getPurchaseable());    	
	    	assertEquals(0, b.getPrice());
	    	assertEquals(20, b.getCopiesAvail());
	    	assertTrue(a.getID() > 1);
	    			    	
	    }
	    
	    @Test
	    void testLoadIOException3() {
	        try {
	        	MaintainMagazine b = new MaintainMagazine();
	            b.load("nonexistent.csv");
	            fail("Expected an IOException to be thrown");
	        } catch (Exception e) {
	            assertTrue(e instanceof IOException);
	        }
	    }
	    
	    @Test
	    void testUpdateIOException3() {
	        try {
	        	MaintainMagazine b = new MaintainMagazine();
	            b.load("nonexistent.csv");
	            b.update(magazinepath);
	            fail("Expected an IOException to be thrown");
	        } catch (Exception e) {
	            assertTrue(e instanceof IOException);
	        }
	    }
/* MaintainMagazine.java */    
	   
	   
	    
/* MaintainHardcoverBook.java */
	    private MaintainHardcoverBook maintain4;
	    private String hardcoverbookpath =  "src/csv files/hardcoverbooks.csv";
	    
	    void setUpMaintainHardcoverBook() {
	    	LibraryItem.resetIncrement();
	    	maintain4 = new MaintainHardcoverBook();
	    }
	    
	    @Test
	    void testLoadHardcover() throws Exception {
	    	setUpMaintainHardcoverBook();
	    	maintain4.load(hardcoverbookpath);
	    	assertNotNull(maintain4.hardcoverbooks.get(0));
	    }	   	  
	    
	    @Test
	    void testLoadHardcover4() throws Exception {
	    	setUpMaintainHardcoverBook();
	    	maintain4.load(hardcoverbookpath);
	    	assertFalse(maintain4.hardcoverbooks.isEmpty());
	    }
	    
	    @Test
	    void testLoadHardcover2() throws Exception{
	    	setUpMaintainHardcoverBook();
	    	maintain4.load(hardcoverbookpath);
	    	HardcoverBook a = maintain4.hardcoverbooks.get(0);
	    	assertEquals("Design Patterns", a.getTitle());
	    	assertEquals("Addison Wesley", a.getPublisher());
	    	assertEquals("978-0201633610", a.getISBN());
	    	assertEquals("Floor 1; Shelf A1", a.getLocation());	
	    	assertEquals(true, a.getRentable());	
	    	assertEquals(false, a.getPurchaseable());    	
	    	assertEquals(56.50, a.getPrice());
	    	assertEquals(20, a.getCopiesAvail());
	    }
	    
	    @Test
	    void testLoadHardcover3() throws Exception{
	    	setUpMaintainHardcoverBook();
	    	maintain4.load(magazinepath);
	    	HardcoverBook a = maintain4.hardcoverbooks.get(0);
	    	assertEquals("HARDCOVER BOOK SUMMARY:\n"
	    			+ "	Title: New York Times Magazine, Author: , Publisher: The New York Times Company, ISBN: .\n"
	    			+ "	Location: 1st floor; Shelf A1. This book is currently not rentable ; purchaseable for $8.85 ; and there are 20 copies available.", a.toString());
	    }
	    
	    @Test
	    void testEmptyUpdateHardcover() throws Exception {
	    	setUpMaintainHardcoverBook();
	    	maintain4.load(hardcoverbookpath);
	    	int initialSize = maintain4.hardcoverbooks.size();
	    	maintain4.update(hardcoverbookpath);
	    	assertEquals(initialSize, maintain4.hardcoverbooks.size());
	    }
	    
	    @Test
	    void testUpdateHardcover() throws Exception {
	    	setUpMaintainHardcoverBook();
	    	maintain4.load(hardcoverbookpath);
	    	ArrayList<HardcoverBook> a = maintain4.hardcoverbooks;
	    	int initialSize = a.size();
	    	a.add(new HardcoverBook());
	    	maintain4.update(hardcoverbookpath);
	    	assertTrue(maintain4.hardcoverbooks.size() == initialSize+1);
	    }
	    
	    @Test
	    void testUpdateHardcoverUpdate1() throws Exception{
	    	setUpMaintainHardcoverBook();
	    	maintain4.load(hardcoverbookpath);
	    	ArrayList<HardcoverBook> a = maintain4.hardcoverbooks;
	    	a.add(new HardcoverBook());
	    	maintain4.update(hardcoverbookpath);
	    	
	    	maintain4.load(hardcoverbookpath);
	    	HardcoverBook b = maintain4.hardcoverbooks.get(maintain4.hardcoverbooks.size()-1);
	    	assertEquals("", b.getTitle());
	    	assertEquals("", b.getAuthor());
	    	assertEquals("", b.getPublisher());
	    	assertEquals("", b.getISBN());
	    	assertEquals("", b.getLocation());	
	    	assertEquals(false, b.getRentable());	
	    	assertEquals(false, b.getPurchaseable());    	
	    	assertEquals(0.0, b.getPrice());
	    	assertEquals(20, b.getCopiesAvail());	    			    	
	    }
	    
	    @Test
	    void testUpdateHardcoverUpdate2() throws Exception{
	    	setUpMaintainHardcoverBook();
	    	maintain4.load(hardcoverbookpath);
	    	ArrayList<HardcoverBook> books = maintain4.hardcoverbooks;
	    	HardcoverBook a = new HardcoverBook();
	    	a.setAttributes("Sing", "Disney", "Disney", "", "", false, false, 0);
	    	books.add(a);
	    	maintain4.update(hardcoverbookpath);
	    	
	    	maintain4.load(hardcoverbookpath);
	    	HardcoverBook b = maintain4.hardcoverbooks.get(maintain4.hardcoverbooks.size()-1);
	    	assertEquals("Sing", b.getTitle());
	    	assertEquals("Disney", b.getAuthor());
	    	assertEquals("Disney", b.getPublisher());
	    	assertEquals("", b.getLocation());	
	    	assertEquals("", b.getISBN());
	    	assertEquals(false, b.getRentable());	
	    	assertEquals(false, b.getPurchaseable());    	
	    	assertEquals(0, b.getPrice());
	    	assertEquals(20, b.getCopiesAvail());
	    	assertTrue(a.getID() > 1);
	    			    	
	    }
	    
	    @Test
	    void testLoadIOException4() {
	        try {
	        	MaintainHardcoverBook b = new MaintainHardcoverBook();
	            b.load("nonexistent.csv");
	            fail("Expected an IOException to be thrown");
	        } catch (Exception e) {
	            assertTrue(e instanceof IOException);
	        }
	    }
	    
	    @Test
	    void testUpdateIOException4() {
	        try {
	        	MaintainHardcoverBook b = new MaintainHardcoverBook();
	            b.load("nonexistent.csv");
	            b.update(hardcoverbookpath);
	            fail("Expected an IOException to be thrown");
	        } catch (Exception e) {
	            assertTrue(e instanceof IOException);
	        }
	    }
/* MaintainHardcoverBook.java */
	    
	    
/* MaintainCD.java */
	    private MaintainCD maintain5;
	    private String cdpath =  "src/csv files/CDs.csv";
	    
	    void setUpMaintainCD() {
	    	LibraryItem.resetIncrement();
	    	maintain5 = new MaintainCD();
	    }
	    
	    @Test
	    void testLoadCD() throws Exception {
	    	setUpMaintainCD();
	    	maintain5.load(cdpath);
	    	assertNotNull(maintain5.cds.get(0));
	    }	   	  
	    
	    @Test
	    void testLoadCD4() throws Exception {
	    	setUpMaintainCD();
	    	maintain5.load(cdpath);
	    	assertFalse(maintain5.cds.isEmpty());
	    }
	    
	    @Test
	    void testLoadCD2() throws Exception{
	    	setUpMaintainCD();
	    	maintain5.load(cdpath);
	    	CD a = maintain5.cds.get(0);
	    	assertEquals("The Lion King", a.getTitle());
	    	assertEquals("1st Floor; Shelf D6", a.getLocation());	
	    	assertEquals(true, a.getRentable());	
	    	assertEquals(false, a.getPurchaseable());    	
	    	assertEquals(15.00, a.getPrice());
	    	assertEquals(20, a.getCopiesAvail());
	    }
	    
	    @Test
	    void testLoadCD3() throws Exception{
	    	setUpMaintainCD();
	    	maintain5.load(cdpath);
	    	CD a = maintain5.cds.get(0);
	    	assertEquals("CD SUMMARY:\n"
	    			+ "	Title: The Lion King, Location: 1st Floor; Shelf D6.\n"
	    			+ "	This CD is currently rentable ; not purchaseable ; and there are 20 copies available.", a.toString());
	    }
	    
	    @Test
	    void testEmptyUpdateCD() throws Exception {
	    	setUpMaintainCD();
	    	maintain5.load(cdpath);
	    	int initialSize = maintain5.cds.size();
	    	maintain5.update(cdpath);
	    	assertEquals(initialSize, maintain5.cds.size());
	    }
	    
	    @Test
	    void testUpdateCD() throws Exception {
	    	setUpMaintainCD();
	    	maintain5.load(cdpath);
	    	ArrayList<CD> a = maintain5.cds;
	    	int initialSize = a.size();
	    	a.add(new CD());
	    	maintain5.update(cdpath);
	    	assertTrue(maintain5.cds.size() == initialSize+1);
	    }
	    
	    @Test
	    void testUpdateCDUpdate1() throws Exception{
	    	setUpMaintainCD();
	    	maintain5.load(cdpath);
	    	maintain5.cds.add(new CD());
	    	maintain5.update(cdpath);
	    	
	    	maintain5.load(cdpath);
	    	CD b = maintain5.cds.get(maintain5.cds.size()-1);
	    	assertEquals("", b.getTitle());
	    	assertEquals("", b.getLocation());	
	    	assertEquals(false, b.getRentable());	
	    	assertEquals(false, b.getPurchaseable());    	
	    	assertEquals(0.0, b.getPrice());
	    	assertEquals(20, b.getCopiesAvail());	    			    	
	    }
	    
	    @Test
	    void testUpdateCDUpdate2() throws Exception{
	    	setUpMaintainCD();
	    	maintain5.load(cdpath);
	    	ArrayList<CD> cds = maintain5.cds;
	    	CD a = new CD();
	    	a.setAttributes("Sing", "Floor 1, Shelf 1", false, false, 0);
	    	cds.add(a);
	    	maintain5.update(cdpath);
	    	
	    	maintain5.load(cdpath);
	    	CD b = maintain5.cds.get(maintain5.cds.size()-1);
	    	assertEquals("Sing", b.getTitle());
	    	assertEquals("Floor 1, Shelf 1", b.getLocation());	
	    	assertEquals(false, b.getRentable());	
	    	assertEquals(false, b.getPurchaseable());    	
	    	assertEquals(0, b.getPrice());
	    	assertEquals(20, b.getCopiesAvail());
	    	assertTrue(a.getID() > 1);
	    			    	
	    }
	    
	    @Test
	    void testLoadIOException5() {
	        try {
	        	MaintainCD b = new MaintainCD();
	            b.load("nonexistent.csv");
	            fail("Expected an IOException to be thrown");
	        } catch (Exception e) {
	            assertTrue(e instanceof IOException);
	        }
	    }
	    
	    @Test
	    void testUpdateIOException5() {
	        try {
	        	MaintainCD b = new MaintainCD();
	            b.load("nonexistent.csv");
	            b.update(cdpath);
	            fail("Expected an IOException to be thrown");
	        } catch (Exception e) {
	            assertTrue(e instanceof IOException);
	        }
	    }
/* MaintainCD.java */
	    
/* GenerateLibraryItemFactory.java */
	    @Test
	    void testCreateNewsletter() {
	        GenerateLibraryItemFactory factory = new GenerateLibraryItemFactory();
	        LibraryItem item = factory.createLibraryItem("newsletter");
	        assertTrue(item instanceof Newsletter);
	    }

	    @Test
	    void testCreateOnlineBook() {
	        GenerateLibraryItemFactory factory = new GenerateLibraryItemFactory();
	        LibraryItem item = factory.createLibraryItem("onlinebook");
	        assertTrue(item instanceof OnlineBook);
	    }

	    @Test
	    void testCreateHardcoverBook() {
	        GenerateLibraryItemFactory factory = new GenerateLibraryItemFactory();
	        LibraryItem item = factory.createLibraryItem("hardcoverbook");
	        assertTrue(item instanceof HardcoverBook);
	    }

	    @Test
	    void testCreateMagazine() {
	        GenerateLibraryItemFactory factory = new GenerateLibraryItemFactory();
	        LibraryItem item = factory.createLibraryItem("magazine");
	        assertTrue(item instanceof Magazine);
	    }

	    @Test
	    void testCreateCD() {
	        GenerateLibraryItemFactory factory = new GenerateLibraryItemFactory();
	        LibraryItem item = factory.createLibraryItem("cd");
	        assertTrue(item instanceof CD);
	    }

	    @Test
	    void testCreateUnknownItem() {
	        GenerateLibraryItemFactory factory = new GenerateLibraryItemFactory();
	        LibraryItem item = factory.createLibraryItem("unknown");
	        assertNull(item);
	    }

	    @Test
	    void testCreateNewsletterCaseInsensitive() {
	        GenerateLibraryItemFactory factory = new GenerateLibraryItemFactory();
	        LibraryItem item = factory.createLibraryItem("nEwSlEtTeR");
	        assertTrue(item instanceof Newsletter);
	    }

	    @Test
	    void testCreateOnlineBookCaseInsensitive() {
	        GenerateLibraryItemFactory factory = new GenerateLibraryItemFactory();
	        LibraryItem item = factory.createLibraryItem("oNLinEbOoK");
	        assertTrue(item instanceof OnlineBook);
	    }

	    @Test
	    void testCreateHardcoverBookCaseInsensitive() {
	        GenerateLibraryItemFactory factory = new GenerateLibraryItemFactory();
	        LibraryItem item = factory.createLibraryItem("hArDcOvErBoOk");
	        assertTrue(item instanceof HardcoverBook);
	    }

	    @Test
	    void testCreateMagazineCaseInsensitive() {
	        GenerateLibraryItemFactory factory = new GenerateLibraryItemFactory();
	        LibraryItem item = factory.createLibraryItem("mAgAzInE");
	        assertTrue(item instanceof Magazine);
	    }

	    @Test
	    void testCreateCDCaseInsensitive() {
	        GenerateLibraryItemFactory factory = new GenerateLibraryItemFactory();
	        LibraryItem item = factory.createLibraryItem("cD");
	        assertTrue(item instanceof CD);
	    }
/* GenerateLibraryItemFactory.java */
}
